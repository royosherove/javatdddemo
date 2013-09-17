package com.aout.kata.unittests;

import com.aout.kata.StringCalculator;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.*;


public class StringCalculatorTests {

    private StringCalculator makeCalc() {
        return new StringCalculator();
    }

    private void assertAdding(String numbers, int expected) {
        StringCalculator sc = makeCalc();

        int result = sc.add(numbers);

        assertEquals(expected, result);
    }



    @Test
    public void parse_emptyString_returnsZero() {
        StringCalculator stringCalculator = makeCalc();

        int result = stringCalculator.parse("");

        assertEquals(0,result);
    }

    @Test
    public void parse_singleNumber_returnsThatNumber() {
        StringCalculator stringCalculator = makeCalc();

        int result = stringCalculator.parse("1");

        assertEquals(1,result);
    }

    @Test
    public void parse_singleNumber_returnsThatNumber2() {
        StringCalculator stringCalculator = makeCalc();

        int result = stringCalculator.parse("2");

        assertEquals(2,result);
    }




    @Test
    public void subtract_twoSimpleNumbers_subtractsThem() {
        StringCalculator stringCalculator = makeCalc();

        int result = stringCalculator.subtract(1, 2);

        assertEquals(1, result);
    }













    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void add_negative_throws2() {
        StringCalculator calc = makeCalc();

        thrown.expect(IllegalArgumentException.class);
        calc.add("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_negative_throws1() {
        makeCalc().add("-1");
    }


    @Test
    public void add_negative_throws0() {
        StringCalculator stringCalculator = makeCalc();
        try {
            stringCalculator.add("-1");
        } catch (Throwable e) {
            //all good
            return;
        }
        assertFalse(true);

    }
    @Test
    public void add_multipleNumbers_returnstheSum(){
        assertAdding("1,2", 3);
    }

    @Test
    public void add_singleNumber_returnsThatNumber(){
        assertAdding("1", 1);
    }



    @Test
    public void add_emptyString_returnsZero(){
        assertAdding("",0);
    }
}
