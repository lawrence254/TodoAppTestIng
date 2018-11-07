package com.lawrence254.todoist;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
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
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class TodoListActivityTest {

    public String etText= "FabTest";

    @Rule
    public ActivityTestRule<TodoListActivity> atRule = new ActivityTestRule<>(TodoListActivity.class);


    @Test
    public void launchToast_inNextActivity(){
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, MainActivity.class);
        intent.putExtra("todo", etText);

        onView(withId(R.id.etTodo)).perform(clearText(),typeText(etText));
        onView(withId(R.id.fab)).perform(scrollTo(),click());
        atRule.launchActivity(intent);
        onView(allOf(withId(android.support.design.R.id.snackbar_text),withText("FabTest"))).check(matches(isDisplayed()));
    }
}