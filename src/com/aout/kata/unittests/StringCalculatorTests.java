package com.aout.kata.unittests;

import com.aout.kata.StringCalculator;
import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.*;


public class StringCalculatorTests {

    @Test
    public void add_emptyString_returnsZero(){
        StringCalculator sc = new StringCalculator();

        int result = sc.add("");

        assertEquals(0, result);
    }
}
