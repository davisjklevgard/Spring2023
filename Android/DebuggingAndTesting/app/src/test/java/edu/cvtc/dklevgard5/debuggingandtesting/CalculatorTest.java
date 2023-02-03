package edu.cvtc.dklevgard5.debuggingandtesting;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    @Test
    public void addTwoNumbers() {
        double result = mCalculator.addition(1d, 1d);
        assertThat(result, is(equalTo(2d)));
    }

    @Test
    public void addTwoNumbersNegative() {
        double resultAdd = mCalculator.addition(-1d, 2d);
        assertThat(resultAdd, is(equalTo(1d)));
    }

    @Test
    public void addTwoNumbersFloats() {
        double resultAdd = mCalculator.addition(1.111f, 1.111d);
        assertThat(resultAdd, is(closeTo(2.222, 0.01)));
    }

    @Test
    public void subTwoNumbers() {
        double resultSub = mCalculator.subtraction(3d, 3d);
        assertThat(resultSub, is(equalTo(0d)));
    }
    @Test
    public void subWorksWithNegativeResult() {
        double resultSub = mCalculator.subtraction(10d, 17d);
        assertThat(resultSub, is(equalTo(-7d)));
    }
    @Test
    public void mulTwoNumbers() {
        double resultMul = mCalculator.multiplication(26d, 2d);
        assertThat(resultMul, is(equalTo(52d)));
    }
    @Test
    public void divTwoNumbers() {
        double resultDiv = mCalculator.division(32d,2d);
        assertThat(resultDiv, is(equalTo(16d)));
    }
    @Test
    public void divByZeroThrows() throws IllegalArgumentException{
        double resultDiv = mCalculator.division(32d,0d);
        assertThat(resultDiv, is(equalTo(Double.POSITIVE_INFINITY)));
    }
}
