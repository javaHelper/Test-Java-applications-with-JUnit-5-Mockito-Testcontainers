package com.appsdeveloperblog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodOrderedByIndexTest {
    StringBuffer completed = new StringBuffer();

    @AfterEach
    void afterEach(){
        System.out.println("The state of object instance is "+ completed);
    }

    @Order(1)
    @Test
    void testA(){
        System.out.println("Running test A");
        completed.append(1);
    }

    @Order(2)
    @Test
    void testB(){
        System.out.println("Running test B");
        completed.append(2);
    }

    @Order(3)
    @Test
    void testC(){
        System.out.println("Running test C");
        completed.append(3);
    }

    @Order(4)
    @Test
    void testD(){
        System.out.println("Running test D");
        completed.append(4);
    }
}