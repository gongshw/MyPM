package gongshw.android.mypm;

import greendroid.app.GDListActivity;
import greendroid.widget.ItemAdapter;
import greendroid.widget.item.Item;
import greendroid.widget.item.SeparatorItem;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class SettingsActivity extends GDListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		((TextView) getActionBar().findViewById(R.id.gd_action_bar_title))
		.setText(R.string.app_name);
		List<Item> items = new ArrayList<Item>();
		items.add(new SeparatorItem("不知道设置什么"));
		final ItemAdapter adapter = new ItemAdapter(this, items);
		setListAdapter(adapter);
	}
}
