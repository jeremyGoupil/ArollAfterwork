package fr.arolla.afterwork.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class fr.arolla.afterwork.activity.FirstActivityTest \
 * fr.arolla.afterwork.activity.tests/android.test.InstrumentationTestRunner
 */
public class FirstActivityTest extends ActivityInstrumentationTestCase2<FirstActivity> {

    public FirstActivityTest() {
        super("fr.arolla.afterwork.activity", FirstActivity.class);
    }


    public void testNominalCase() throws Exception {
        FirstActivity firstActivity = getActivity();
        TextView view = (TextView) firstActivity.findViewById(R.id.textView);
        assertTrue(view.getText().equals("Hello World, FirstActivity"));
    }
}
