package com.hello;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

public class GreetingTest {

    @Test
    public void buildNullGreeting() {
        Greeting greeting = new Greeting.Builder().build();
        assertNull(greeting.getId());
        assertNull(greeting.getContent());
    }

    @Test
    public void buildGreeting() {
        Greeting greeting = new Greeting.Builder()
                .withId(1L)
                .withContent("Hello!")
                .build();

        assertEquals(new Long(1), greeting.getId());
        assertEquals("Hello!", greeting.getContent());
    }
}
