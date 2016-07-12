package avi.edu.calc.parser;

import avi.edu.calc.operation.Operation;
import avi.edu.calc.operator.Addition;
import avi.edu.calc.operator.Division;
import avi.edu.calc.operator.Multiplication;
import avi.edu.calc.operator.Subtraction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParserTest {
    private InputParser parser = new InputParser();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void it_should_accept_operations_with_2_operands_or_less() {
        assertThat(parser.isNumberOfOperandsValid("5-3")).isTrue();
    }

    @Test
    public void it_should_reject_operations_with_more_then_2_operands() {
        assertThat(parser.isNumberOfOperandsValid("1+2+3")).isFalse();
    }

    @Test
    public void it_should_throw_an_exception_if_no_operator_is_found() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("no operator found");
        parser.parseOperator("1a2");
    }

    @Test(expected = NumberFormatException.class)
    public void it_should_throw_an_exception_when_operands_are_not_integers() {
        parser.parseOperands("any+string");
    }

    @Test
    public void it_should_return_operands_as_an_array() {
        assertThat(parser.parseOperands("1+2")).isEqualTo(new int[]{1, 2});
        assertThat(parser.parseOperands("4*5")).isEqualTo(new int[]{4, 5});
        assertThat(parser.parseOperands("7-8")).isEqualTo(new int[]{7, 8});
        assertThat(parser.parseOperands("10/11")).isEqualTo(new int[]{10, 11});
    }

    @Test
    public void it_should_return_an_addition() {
        assertThat(parser.parseOperator("1+2")).isExactlyInstanceOf(Addition.class);
    }

    @Test
    public void it_should_return_a_multiplication() {
        assertThat(parser.parseOperator("2*5")).isExactlyInstanceOf(Multiplication.class);
    }

    @Test
    public void it_should_return_a_subtraction() {
        assertThat(parser.parseOperator("2-5")).isExactlyInstanceOf(Subtraction.class);
    }

    @Test
    public void it_should_return_a_division() {
        assertThat(parser.parseOperator("2/5")).isExactlyInstanceOf(Division.class);
    }

    @Test
    public void it_should_return_an_addition_operation() {
        Operation expected = new Operation(new int[]{1, 2}, new Addition());
        Operation actual = parser.parse("1+2");

        assertThat(actual).isEqualToComparingOnlyGivenFields(expected, "operands");
        assertThat(actual.getOperator()).isExactlyInstanceOf(expected.getOperator().getClass());
    }

    @Test
    public void it_should_return_a_multiplication_operation() {
        Operation expected = new Operation(new int[]{5, 2}, new Multiplication());
        Operation actual = parser.parse("5*2");

        assertThat(actual).isEqualToComparingOnlyGivenFields(expected, "operands");
        assertThat(actual.getOperator()).isExactlyInstanceOf(expected.getOperator().getClass());
    }

    @Test
    public void it_should_return_a_subtraction_operation() {
        Operation expected = new Operation(new int[]{5, 2}, new Subtraction());
        Operation actual = parser.parse("5-2");

        assertThat(actual).isEqualToComparingOnlyGivenFields(expected, "operands");
        assertThat(actual.getOperator()).isExactlyInstanceOf(expected.getOperator().getClass());
    }

    @Test
    public void it_should_return_a_division_operation() {
        Operation expected = new Operation(new int[]{5, 2}, new Division());
        Operation actual = parser.parse("5/2");

        assertThat(actual).isEqualToComparingOnlyGivenFields(expected, "operands");
        assertThat(actual.getOperator()).isExactlyInstanceOf(expected.getOperator().getClass());
    }
}
