package com.lawrence254.todoist;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsAnything.anything;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MainActivityTest {

    public String etText= "FabTest";

    @Rule
    public ActivityTestRule<MainActivity> atRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void viewsAvailable(){
//        onView(withId(R.id.tvTodo)).perform(closeSoftKeyboard());
        onView(withId(R.id.etTodo)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }
    @Test
    public void enterText(){
        onView(withId(R.id.etTodo)).perform(clearText(),typeText("Todo1"));
    }
    @Test
    public void addItemtoList(){
        onView(withId(R.id.etTodo)).perform(clearText(),typeText(etText));
        onView(withId(R.id.fab)).perform(ViewActions.closeSoftKeyboard(),click());
        onView(withId(R.id.lvTodos)).check(matches(isDisplayed()));
//        onData(anything()).inAdapterView(withId(R.id.lvTodos)).atPosition(0).perform(click());
    }
    @Test
    public void itemExists(){
        onView(withId(R.id.etTodo)).perform(clearText(),typeText(etText));
        onView(withId(R.id.fab)).perform(ViewActions.closeSoftKeyboard(),click());
        onData(anything()).inAdapterView(withId(R.id.lvTodos)).atPosition(0).check(matches(isDisplayed()));
    }
    @Test
    public void checkItem(){
        onView(withId(R.id.etTodo)).perform(clearText(),typeText(etText));
        onView(withId(R.id.fab)).perform(ViewActions.closeSoftKeyboard(),click());
        onData(anything()).inAdapterView(withId(R.id.lvTodos)).atPosition(0).perform(click());
    }
}