package com.talan.coin.ethereum.api;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APIManagerIT /*extends IntegrationTest*/ {
    @Autowired
    private APIManager apiManager;

    private final String HEX_PATTERN = "0x[A-Fa-f0-9]+";

    /**
     * IMPORTANT: the account creation in this test is just in case this test is ran first to make sure there is at
     * least one account created (the coinbase).
     * However, if this test is not the first test ran, then, this account creation is useless.
     */
    @Test
    public void should_mine() throws InterruptedException {
        // given
        apiManager.createAccount("Talan2017");
        String coinbase = apiManager.retrieveCoinBase();

        System.out.println("retrieved coinbase: " + coinbase);

        // when
        assertThat(apiManager.startMining(2)).isTrue();

        while (apiManager.getBalance(coinbase) < 20) {
            System.out.println("Mining... current balance: " + apiManager.getBalance(coinbase));
            System.out.println("sleeping 5 secs");
            sleep(5000);
        }

        assertThat(apiManager.stopMining()).isTrue();
    }

    @Test
    public void should_send_a_transaction() throws InterruptedException {
        // given
        String account = apiManager.createAccount("Talan2017");
        String to = apiManager.createAccount("Talan7102");

        String coinbase = apiManager.retrieveCoinBase();

        apiManager.startMining(2);

        while (apiManager.getBalance(coinbase) < 20) {
            System.out.println("Mining... current balance: " + apiManager.getBalance(coinbase));
            System.out.println("sleeping 5 secs");
            sleep(5000);
        }

        String transactionHash = apiManager.sendTransaction(
                coinbase,
                to,
                5,
                "Talan2017");

        System.out.println("TRANSACTION HASH: " + transactionHash);

        assertThat(transactionHash).matches(HEX_PATTERN);

//        apiManager.getTransaction(transactionHash);

        while (apiManager.getBalance(to).equals(0)) {
            System.out.println("waiting for transaction to be mined");
            System.out.println("to balance is: " + apiManager.getBalance(to));
            Thread.sleep(5000);
        }

        apiManager.stopMining();

        assertThat(apiManager.getBalance(to)).isEqualTo(5);
    }
}
