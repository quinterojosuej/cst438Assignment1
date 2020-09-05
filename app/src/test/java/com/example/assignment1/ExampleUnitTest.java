package com.example.assignment1;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

//    The following are the tests for the intent

//    The following is needed to make sure the intent can be made
//    Same goes for the before
    public MainActivity mainActivity;
    public ToPass toPass;

    @Before
    public void before(){
        mainActivity = new MainActivity();
        toPass = new ToPass();
    }

//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }

//    Here is the actual intent test, checks for the intent
//    to not be null, or non existent since we know the starting and ending points

    @Test
    public void test_intent(){
        Intent intent = toPass.BasicToPassIntent(mainActivity);

        assertNotNull(intent);

    }

}