package avi.edu.checkout;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutStepsDefinition {
    private Map<String, Integer> prices = new HashMap();
    private Checkout checkout = new Checkout();

    @Given("^the price of a \"(.*?)\" is (\\d+)c$")
    public void the_price_of_a_is_c(String name, int price) throws Throwable {
        registerPrices(name, price);
    }

    @When("^I checkout (\\d+) \"(.*?)\"$")
    public void i_checkout(int itemCount, String itemName) throws Throwable {
        checkout.add(itemCount, prices.get(itemName));
    }

    @Then("^the total price should be (\\d+)c$")
    public void the_total_price_should_be_c(int total) throws Throwable {
        assertThat(checkout.total()).isEqualTo(total);
    }

    private void registerPrices(String name, int price) {
        prices.put(name, price);
    }
}
