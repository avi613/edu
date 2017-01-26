package com.talan.coin.ethereum.api;

import lombok.Data;

@Data
public class EmployeeDSL {
    private String name;
    private String accountHash;
    private Integer balance = 0;
}
