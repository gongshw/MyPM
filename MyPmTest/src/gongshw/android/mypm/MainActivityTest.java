package gongshw.android.mypm;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.View;
import greendroid.widget.QuickActionWidget;

import java.lang.reflect.Field;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class gongshw.android.mypm.MainActivityTest \
 * gongshw.android.mypm.tests/android.test.InstrumentationTestRunner
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Instrumentation mInstrument;
    private MainActivity mainActivity;
    private View menuItem;

    public MainActivityTest() {
        super("gongshw.android.mypm", MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        mInstrument = getInstrumentation();
        mainActivity = getActivity();
        menuItem = mainActivity.getActionBar().getItem(1).getItemView();
    }

    /**
     * 这个测试用例可以测试菜单按钮(导航条最右边那个按钮)对点击的相应。
     * 当这个按钮被点击,应当弹出一个菜单显示更多的操作。
     * 这个菜单是个QuickActionWidget。通过反射得到这个对象的引用并检测其是否弹出。
     */
    @UiThreadTest
    public void testShowMenuWidget() throws NoSuchFieldException, IllegalAccessException {
		menuItem.requestFocus();
        menuItem.performClick();
        Field field = MainActivity.class.getDeclaredField("mGrid");
        field.setAccessible(true);
        assertTrue(((QuickActionWidget)field.get(mainActivity)).isShowing());
    }


}
