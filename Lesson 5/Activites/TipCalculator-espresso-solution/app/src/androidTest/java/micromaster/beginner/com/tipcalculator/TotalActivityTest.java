package micromaster.beginner.com.tipcalculator;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TotalActivityTest {

    @Rule
    public ActivityTestRule<TotalActivity> totalActivityRule = new ActivityTestRule<>(TotalActivity.class, true, false);

    @Test
    public void extrasAndExit() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, TotalActivity.class);
        intent.putExtra("totalExtra", "115.0");

        totalActivityRule.launchActivity(intent);

        /* Your activity is initialized and ready to go. */
        onView(withId(R.id.totalTextView)).check(matches(withText("The total is: 115.0")));

        onView(withId(R.id.buttonOk)).perform(click());

        assertTrue(totalActivityRule.getActivity().isFinishing());
    }

}