package com.example.tiptime
import org.junit.Test
import org.junit.Assert.*
class TipCalculatorTest {
 @Test fun calculate_20_percent_tip(){assertEquals(2.0,calculateTip(10.0,20.0),0.0001)}
 @Test fun rounding(){assertEquals(3.0,calculateTip(11.0,20.0,true),0.0001)}
 @Test fun zero(){assertEquals(0.0,calculateTip(0.0,20.0),0.0)}
 @Test fun invalid(){assertEquals(0.0,calculateTip(-10.0),0.0);assertEquals(0.0,calculateTip(Double.NaN),0.0)}
}
