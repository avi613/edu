package avi.edu.calc;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParserTest {
    private InputParser parser;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        parser = new InputParser();
    }

    @Test
    public void it_should_throw_an_exception_when_number_of_addies_exceed_2() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("number of addies must not exceed 2");
        parser.parse("1,2,3");
    }

    @Test(expected = NumberFormatException.class)
    public void it_should_throw_an_exception_when_addies_are_not_integers() {
        parser.parse("1.5,2.3");
    }

    @Test(expected = NumberFormatException.class)
    public void it_should_raise_an_exception_if_input_is_not_a_list_of_numbers() {
        parser.parse("any string");
    }

    @Test
    public void it_should_return_an_array_of_int() {
        assertThat(parser.parse("1,2")).isEqualTo(new int[] {1, 2});
    }
}
