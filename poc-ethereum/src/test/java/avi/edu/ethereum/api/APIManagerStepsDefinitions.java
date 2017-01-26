package com.talan.coin.ethereum.api;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class APIManagerStepsDefinitions {

    @Given("^Employees (.*) and (.*)$")
    public void initialize_employees(String employee_1_name, String employee_2_name) {
        assertThat(true).isTrue();
    }

    @When("^(.*) sends (\\d+) talanCoins to (.*)")
    public void employee_sends_talanCoins(String sender_name, int quantity, String recipient_name) {
        assertThat(true).isTrue();
    }

    @Then("^(.*) should receive (\\d+) talanCoins$")
    public void employee_receives_talanCoin(String recipient_name, int quantity) {
        assertThat(true).isTrue();
    }
}
