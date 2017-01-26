package com.talan.coin.ethereum.api;

import com.talan.coin.ethereum.transaction.Transaction;
import lombok.val;
import org.junit.Test;

import java.util.LinkedHashMap;

import static com.google.common.collect.ImmutableList.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class APIManagerTest {
    private EthereumTemplate ethereumTemplate = mock(EthereumTemplate.class);
    private APIManager api = new APIManager(ethereumTemplate);

    @Test
    public void should_get_ethereum_version() {
        when(ethereumTemplate.exchange("web3_clientVersion", of("")))
                .thenReturn("{\"result\":\"Geth/v1.5.4-stable-b70acf3c/windows/go1.7.3\"}");

        assertThat(api.getW3Version()).isEqualTo("Geth/v1.5.4-stable-b70acf3c/windows/go1.7.3");
    }

    @Test
    public void should_get_node_info() {
        when(ethereumTemplate.exchange("admin_nodeInfo", of("")))
                .thenReturn("{\"result\":{\"id\":ID,\"name\":NAME,\"enode\":ENODE,\"ip\":IP,\"ports\":PORTS,\"protocols\":PROTOCOLS}}");

        val mapResult = api.getNodeInfo();

        assertThat(mapResult).isExactlyInstanceOf(LinkedHashMap.class);
        assertThat(mapResult).containsKeys("id", "name", "enode", "ip", "ports", "protocols");
    }

    @Test
    public void should_get_enode() {
        when(ethereumTemplate.exchange("admin_nodeInfo", of("")))
                .thenReturn("{\"result\":{\"id\":ID,\"enode\":ENODE}}");

        assertThat(api.getEnode()).isEqualTo("ENODE");
    }

    @Test
    public void should_return_created_account() {
        when(ethereumTemplate.exchange("personal_newAccount", of("password")))
                .thenReturn("{\"result\":\"some_weird_hex_value\"}");

        assertThat(api.createAccount("password")).isEqualTo("some_weird_hex_value");
    }

    @Test
    public void should_retrieve_account_list() {
        when(ethereumTemplate.exchange("personal_listAccounts", of("")))
                .thenReturn("{\"result\":[\"some_weird_hex_value\", \"some_other_weird_hex_value\"]}");

        assertThat(api.retrieveAccountList()).isEqualTo(of(
                "some_weird_hex_value", "some_other_weird_hex_value"
        ));
    }

    @Test
    public void should_retrieve_coin_base() {
        when(ethereumTemplate.exchange("eth_coinbase", of("")))
                .thenReturn("{\"result\":\"some_weird_hex_value\"}");

        assertThat(api.retrieveCoinBase()).isEqualTo("some_weird_hex_value");
    }

    @Test
    public void should_get_balance_1a055690d9db80000() {
        when(ethereumTemplate.exchange("eth_getBalance", of("account", "latest")))
                .thenReturn("{\"result\":1a055690d9db80000}");

        assertThat(api.getBalance("account")).isEqualTo(30);
    }

    @Test
    public void should_start_mining() {
        when(ethereumTemplate.exchange("miner_start", 2))
                .thenReturn("{\"result\":true}");

        assertThat(api.startMining(2)).isTrue();
    }

    @Test
    public void should_stop_mining() {
        when(ethereumTemplate.exchange("miner_stop", of("")))
                .thenReturn("{\"result\":true}");

        assertThat(api.stopMining()).isTrue();
    }

    @Test
    public void should_invoke_ethereum_template_to_send_a_transaction() {
        // given
        Transaction transaction = new Transaction("from", "to", 10);

        // when
        api.sendTransaction("from", "to", 10, "WAT");

        verify(ethereumTemplate, times(1))
                .exchange("personal_sendTransaction", of(transaction.json(), "WAT"));
    }
}
