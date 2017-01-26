package com.talan.coin.ethereum.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static com.jayway.jsonassert.JsonAssert.with;
import static com.jayway.jsonpath.JsonPath.read;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthereumTemplateIT {
    @Autowired
    private EthereumTemplate template;

    private final String HEX_PATTERN = "0x[A-Fa-f0-9]+";

    @Test
    public void should_get_eth_version() {
        with(template.exchange("web3_clientVersion", of("")))
                .assertThat("result", equalTo("Geth/v1.5.4-stable-b70acf3c/windows/go1.7.3"));
    }

    @Test
    public void should_get_node_info() {
        // NB: these params are defined at the start of the block chain in initiate_node.bat
        with(template.exchange("admin_nodeInfo", of("")))
                .assertNotNull("result.enode")
                .assertNotNull("result.protocols.eth.genesis")
                .assertEquals("result.protocols.eth.network", 7536);
    }

    @Test
    public void should_create_an_account() {
        // when
        String account = read(template.exchange("personal_newAccount", of("Talan2017")), "$.result");

        // then
        assertThat(account).matches(HEX_PATTERN);
    }

    @Test
    public void should_retrieve_an_account_list() {
        // given
        template.exchange("personal_newAccount", of("Talan2017"));
        template.exchange("personal_newAccount", of("Talan2022"));

        // when
        List<String> accounts = read(template.exchange("personal_listAccounts", of("")), "$.result");

        // then
        assertThat(accounts).isNotEmpty();
        accounts.stream()
                .forEach(account -> account.matches(HEX_PATTERN));
    }

    @Test
    /**
     * IMPORTANT: the account creation in this test is just in case this test is ran first to make sure there is at
     * least one account created (the coinbase).
     * However, if this test is not the first test ran, then, this account creation is useless.
     */
    public void should_retrieve_coin_base() {
        // given
        template.exchange("personal_newAccount", of("Talan2017"));

        // when
        String coinbase = read(template.exchange("eth_coinbase", of("")), "$.result");

        // then
        coinbase.matches(HEX_PATTERN);
    }

    @Test
    public void should_get_balance_of_account() {
        // given
        String account = read(template.exchange("personal_newAccount", of("Talan2017")), "$.result");

        // when
        String balance = read(template.exchange("eth_getBalance", of(account, "latest")), "$.result");

        // then
        assertThat(balance).matches(HEX_PATTERN);
    }
}
