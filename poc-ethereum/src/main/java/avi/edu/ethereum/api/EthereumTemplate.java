package com.talan.coin.ethereum.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EthereumTemplate {
    private String host;
    private String port;
    private RestTemplate restTemplate;

    public EthereumTemplate(String host, String port, RestTemplate restTemplate) {
        this.host = host;
        this.port = port;
        this.restTemplate = restTemplate;
    }

    public String exchange(String method, List<String> parameters) {
        return restTemplate.postForObject(
                "http://" + host + ":" + port,
                "{\"jsonrpc\":\"2.0\",\"method\":\"" + method + "\",\"params\":[" + format(parameters) + "],\"id\":1}",
                String.class);
    }

    public String exchange(String method, Integer numbers) {
        return restTemplate.postForObject(
                "http://" + host + ":" + port,
                "{\"jsonrpc\":\"2.0\",\"method\":\"" + method + "\",\"params\":[" + numbers + "],\"id\":1}",
                String.class);
    }

    public String rawPost(String method, List<String> parameters) {
        System.out.println("RAW POST: {\"jsonrpc\":\"2.0\",\"method\":\"" + method + "\",\"params\":[" + rawParams(parameters) + "],\"id\":1}");
        return restTemplate.postForObject(
                "http://" + host + ":" + port,
                "{\"jsonrpc\":\"2.0\",\"method\":\"" + method + "\",\"params\":[" + rawParams(parameters) + "],\"id\":1}",
                String.class);
    }

    private String format(List<String> parameters) {
        return "\"" + parameters.stream().collect(Collectors.joining("\",\"")) + "\"";
    }

    private String rawParams(List<String> parameters) {
        return parameters.stream().collect(Collectors.joining(","));
    }
}
