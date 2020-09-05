package com.example.assignment1;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

//    For the intent test.
    @Test
    public void intentTest(){
        Context app = InstrumentationRegistry.getInstrumentation().getTargetContext();
        int test_id = ToPass.ToPassIntent(app, 2).getIntExtra(ToPass.TO_PASS_ID, -1);

        assertEquals(2, test_id);
    }
}