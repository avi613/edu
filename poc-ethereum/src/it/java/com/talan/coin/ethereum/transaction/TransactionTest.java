package com.talan.coin.ethereum.transaction;

import org.junit.Test;

import static com.jayway.jsonassert.JsonAssert.with;
import static java.lang.Integer.toHexString;

public class TransactionTest {
    @Test
    public void should_convert_transaction_to_json() {
        Transaction transaction = new Transaction("from_name", "to_name", 200);
        with(transaction.json())
                .assertEquals("from", "from_name")
                .assertEquals("to", "to_name")
                .assertEquals("value", "0x2417851639229258349412352");
    }
}
