package com.talan.coin.ethereum.api;

import com.talan.coin.ethereum.transaction.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static com.jayway.jsonpath.JsonPath.read;
import static java.lang.Integer.decode;

@Component
public class APIManager {
    private EthereumTemplate ethereumTemplate;

    private BigInteger ETHER_BASE = new BigInteger("1000000000000000000", 10);

    public APIManager(EthereumTemplate ethereumTemplate) {
        this.ethereumTemplate = ethereumTemplate;
    }

    public String getW3Version() {
        return stringResult(ethereumTemplate.exchange("web3_clientVersion", of("")));
    }

    public LinkedHashMap<String, String> getNodeInfo() {
        return mapResult(ethereumTemplate.exchange("admin_nodeInfo", of("")));
    }

    public String getEnode() {
        return mapResult(ethereumTemplate.exchange("admin_nodeInfo", of(""))).get("enode");
    }

    public String createAccount(String password) {
        return stringResult(ethereumTemplate.exchange("personal_newAccount", of(password)));
    }

    public List<String> retrieveAccountList() {
        return listResult(ethereumTemplate.exchange("personal_listAccounts", of("")));
    }

    public String retrieveCoinBase() {
        return stringResult(ethereumTemplate.exchange("eth_coinbase", of("")));
    }

    public Integer getBalance(String account) {
        System.out.println("getting balance for account: " + account);
        return hexToEther(ethereumTemplate.exchange("eth_getBalance", of(account, "latest")));
    }

    public boolean startMining(Integer numberOfWorkers) {
        return read(ethereumTemplate.exchange("miner_start", numberOfWorkers), "$.result");
    }

    public boolean stopMining() {
        return read(ethereumTemplate.exchange("miner_stop", of("")), "$.result");
    }

    /*
     * ROAD MAP:
     * 1. Unlock Account
     * 2. getTransaction
     *
     */

    private boolean unlockAccount(String account, String password) {
        return read(ethereumTemplate.exchange("personal_unlockAccount", of(account, password)), "$.result");
    }

    public String sendTransaction(String from, String to, Integer amount, String fromPassword) {
//        unlockAccount(from, fromPassword);
        return stringResult(ethereumTemplate.rawPost("personal_sendTransaction",
                of(new Transaction(from, to, amount).json(), "\"" + fromPassword + "\"")));
    }

    // TODO: implement
    public LinkedHashMap<String, String> getTransaction(String transactionHash) {
        return null;
    }

    /* TODO: UTILS - refactor to a delegate? */

    private String stringResult(String json) {
        return read(json, "$.result");
    }

    private List<String> listResult(String json) {
        return read(json, "$.result");
    }

    // TODO: refactor to return object NodeInfo, Transaction
    private LinkedHashMap<String, String> mapResult(String json) {
        return read(json, "$.result");
    }

    /**
     * G. help us!
     *
     * This algorithm removes the 0x prefix in the balance returned by the server
     *
     * @param json
     * @return
     */
    private Integer hexToEther(String json) {
        System.out.println("from hexToEther, raw json is: " + json);
        String result = read(json, "$.result");
        System.out.println("from hexToEther, result is: " + result);
        return new BigInteger(result.replaceAll("0x", ""), 16).divide(ETHER_BASE).intValue();
    }

    // TODO: remove me?
    private Integer integerResult(String json) {
        return decode(read(json, "$.result"));
    }
}
