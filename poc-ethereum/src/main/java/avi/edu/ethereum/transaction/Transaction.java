package com.talan.coin.ethereum.transaction;

import lombok.Value;

import java.math.BigInteger;

@Value
public class Transaction {
    private String from;
    private String to;
    private Integer amount;

    private BigInteger ETHER_BASE = new BigInteger("1000000000000000000", 16);

    public String json() {
        return "{\"from\":\"" + from + "\",\"to\":\"" + to + "\",\"value\":\"" + hash(amount) + "\"}";
    }

    private String hash(Integer amount) {
        return /*"0x" + */new BigInteger(amount.toString(), 16).multiply(ETHER_BASE).toString();
    }
}
