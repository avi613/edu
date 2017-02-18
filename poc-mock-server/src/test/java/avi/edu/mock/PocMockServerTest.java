package avi.edu.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PocMockServerTest {
    private ClientAndProxy proxy;
    private ClientAndServer mockServer;

    private URL baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void startProxy() throws MalformedURLException {
        baseUrl = new URL("http://localhost:1080/poc");

        mockServer = startClientAndServer(1080);
        proxy = startClientAndProxy(1090);
    }

    @After
    public void stopProxy() {
        proxy.stop();
        mockServer.stop();
    }

    @Test
    public void some_test() {
        mockServer
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/poc/hello")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody("{message: 'Hello Mock Server'}")
                );

        mockServer
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/poc/wat")
                )
                .respond(
                        response()
                                .withStatusCode(500)
                                .withBody("{error: 'don\'t you be cute with me!'}")
                );

        ResponseEntity responseHello = restTemplate.getForEntity(baseUrl + "/hello", String.class);
        assertThat(responseHello.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseHello.getBody()).isEqualTo("{message: 'Hello Mock Server'}");
        System.out.println("RESPONSE FROM MOCK SERVER: " + responseHello);

        ResponseEntity responseWAT = restTemplate.getForEntity(baseUrl + "/wat", String.class);
        assertThat(responseWAT.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseWAT.getBody()).isEqualTo("{error: 'don\'t you be cute with me!'}");
        System.out.println("RESPONSE FROM MOCK SERVER: " + responseWAT);
    }
}
