package com.company.src;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Calculator class for different types of expressions
 */
public class Testing {
    @Test
    public void calculateTest1() {
        String str = "19-981/(3*2)";
        Calculator expression = new Calculator(str);
        assertTrue(expression.calculate());
        assertEquals("-144.5",expression.getAnswer().toString());
    }

    @Test
    public void calculateTest2() {
        String str = "(915-194)/(15-14+4)";
        Calculator expression = new Calculator(str);
        assertTrue(expression.calculate());
        assertEquals("144.2",expression.getAnswer().toString());
    }

    @Test
    public void calculateTest3() {
        String str = "49+100*((310-3)";
        Calculator expression = new Calculator(str);
        assertFalse(expression.calculate());
    }

    @Test
    public void calculateTest4() {
        String str = "4+99+*(75-3)";
        Calculator expression = new Calculator(str);
        assertFalse(expression.calculate());
    }

    @Test
    public void calculateTest5() {
        String str = "15+9)/(76-3";
        Calculator expression = new Calculator(str);
        assertFalse(expression.calculate());
    }


    @Test
    public void calculateTest6() {
        String str = "44*a+17b*(18-3)";
        Calculator expression = new Calculator(str);
        assertFalse(expression.calculate());
    }

    @Test
    public void calculateTest7() {
        String str = "364 56279";
        Calculator expression = new Calculator(str);
        assertFalse(expression.calculate());
    }

    @Test
    public void calculateTest8() {
        String str = "+-+/*";
        Calculator expression = new Calculator(str);
        assertFalse(expression.calculate());
    }
}
