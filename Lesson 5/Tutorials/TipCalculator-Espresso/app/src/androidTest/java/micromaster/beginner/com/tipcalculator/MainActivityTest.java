package micromaster.beginner.com.tipcalculator;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
@SdkSuppress(minSdkVersion = 15)
public class MainActivityTest {

    private static final String BILL_AMOUNT = "100";

    @Rule
    public IntentsTestRule<MainActivity> mIntentsRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void tipTotalAmount15percent() {
        onView(withId(R.id.input_billAmount)).perform(typeText(BILL_AMOUNT), closeSoftKeyboard());

        onView(withText("15% Tip")).perform(click());

        String expectedText = "115.0";
        onView(withId(R.id.totalAmount)).check(matches(withText(expectedText)));

        // Testing Intent
        onView(withId(R.id.viewTotalButton)).perform(click());

        intended(allOf(
            hasComponent(hasShortClassName(".TotalActivity")),
            toPackage("micromaster.beginner.com.tipcalculator"),
            hasExtra("totalExtra", "115.0")));
    }

}
