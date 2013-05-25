package gongshw.android.mypm;

import java.util.ArrayList;
import java.util.List;

import greendroid.app.GDListActivity;
import greendroid.widget.ItemAdapter;
import greendroid.widget.item.Item;
import greendroid.widget.item.SeparatorItem;
import greendroid.widget.item.SubtitleItem;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class InfoActivity extends GDListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		((TextView) getActionBar().findViewById(R.id.gd_action_bar_title))
		.setText(R.string.app_name);
		List<Item> items = new ArrayList<Item>();
		items.add(new SeparatorItem("作者"));
		items.add(new MyItem("我叫","龚世伟"));
		items.add(new MyItem("学号","101250034"));
		items.add(new SeparatorItem("api"));
		items.add(new SubtitleItem("pm25.in","提供城市污染信息"));
		items.add(new SeparatorItem("框架"));
		items.add(new SubtitleItem("afinal","android框架，发送http请求，实现界面的依赖注入"));
		items.add(new SubtitleItem("google guice","依赖注入框架"));
		items.add(new SeparatorItem("库"));
		items.add(new SubtitleItem("greendroid","android前端库"));
		items.add(new SubtitleItem("google gson","json解析"));
		final ItemAdapter adapter = new ItemAdapter(this, items);
		setListAdapter(adapter);
	}
}
