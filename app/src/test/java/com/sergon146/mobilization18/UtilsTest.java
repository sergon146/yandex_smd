package com.sergon146.mobilization18;

import com.sergon146.mobilization18.util.CurrencyUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {
    @Test
    public void testCurrencyRound() {
        assertEquals(CurrencyUtils.round(13.333, 2), 13.33, 0.0001);
    }
}
