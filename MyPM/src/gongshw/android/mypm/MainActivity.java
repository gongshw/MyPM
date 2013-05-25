package gongshw.android.mypm;

import gongshw.android.mypm.factory.ServiceFactory;
import gongshw.android.mypm.model.CityItem;
import gongshw.android.mypm.model.PollutionItem;
import gongshw.android.mypm.service.LocationService;
import gongshw.android.mypm.service.PmService;
import greendroid.app.GDListActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.ItemAdapter;
import greendroid.widget.LoaderActionBarItem;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionGrid;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;
import greendroid.widget.item.DrawableItem;
import greendroid.widget.item.Item;
import greendroid.widget.item.SeparatorItem;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends GDListActivity {

	private QuickActionWidget mGrid;
	LoaderActionBarItem refreshItem;

	private PmService pmService = ServiceFactory.getInstance().getPmService();
	private LocationService locationService = ServiceFactory.getInstance()
			.getLocationService(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		prepareQuickActionGrid();
		getActionBar().setType(greendroid.widget.ActionBar.Type.Empty);
		((TextView) getActionBar().findViewById(R.id.gd_action_bar_title))
				.setText(R.string.app_name);
		getActionBar().setTitle(getString(R.string.app_name));
		refreshItem = (LoaderActionBarItem) addActionBarItem(Type.Refresh,
				R.id.action_bar_refresh);
		addActionBarItem(Type.List, R.id.action_bar_more);
		refresh_data();
	}

	private void setList(CityItem item) {
		List<Item> items = new ArrayList<Item>();
		items.add(new MyItem(getString(R.string.data_update_at), item
				.getUpdateTime()));
		items.add(new DrawableItem(
				getString(R.string.you_are_in) + item.getName(),
				com.cyrilmottier.android.greendroid.R.drawable.gd_action_bar_locate));
		items.add(new DrawableItem(
				getString(R.string.genneral_qulity)+ item.getQuality(),
				com.cyrilmottier.android.greendroid.R.drawable.gd_action_bar_star));
		items.add(new MyItem(getString(R.string.api_index), ""
				+ item.getAqiValue()));
		items.add(new SeparatorItem(getString(R.string.unit_hint)));

		for (PollutionItem pItem : item) {
			items.add(new MyItem(pItem.getName(), pItem.getValue()));
		}

		final ItemAdapter adapter = new ItemAdapter(this, items);
		setListAdapter(adapter);
	}

	@Override
	public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {

		switch (item.getItemId()) {
		case R.id.action_bar_more:
			onShowGrid(item.getItemView());
			break;
		case R.id.action_bar_refresh:
			refresh_data();
			break;
		default:
			return super.onHandleActionBarItemClick(item, position);
		}

		return true;
	}

	public void onShowGrid(View v) {
		mGrid.show(v);
	}

	private void prepareQuickActionGrid() {
		mGrid = new QuickActionGrid(this);
		mGrid.addQuickAction(new MyQuickAction(this,
				R.drawable.gd_action_bar_info, R.string.info));
		mGrid.addQuickAction(new MyQuickAction(this,
				R.drawable.gd_action_bar_help, R.string.help));
		mGrid.addQuickAction(new MyQuickAction(this,
				R.drawable.gd_action_bar_settings, R.string.setting));
		mGrid.setOnQuickActionClickListener(mActionListener);
	}

	private OnQuickActionClickListener mActionListener = new OnQuickActionClickListener() {
		public void onQuickActionClicked(QuickActionWidget widget, int position) {
			Intent intent = new Intent();
			switch (position) {
			case 0:
				intent.setClass(MainActivity.this, InfoActivity.class);
				break;
			case 1:
				intent.setClass(MainActivity.this, InfoActivity.class);
				break;
			case 2:
				intent.setClass(MainActivity.this, SettingsActivity.class);
				break;
			default:
				break;
			}
			startActivity(intent);
		}
	};

	private static class MyQuickAction extends QuickAction {

		private static final ColorFilter BLACK_CF = new LightingColorFilter(
				Color.BLACK, Color.BLACK);

		public MyQuickAction(Context ctx, int drawableId, int titleId) {
			super(ctx, buildDrawable(ctx, drawableId), titleId);
		}

		private static Drawable buildDrawable(Context ctx, int drawableId) {
			Drawable d = ctx.getResources().getDrawable(drawableId);
			d.setColorFilter(BLACK_CF);
			return d;
		}

	}

	private void refresh_data() {
		refreshItem.setLoading(true);
		String currentCity = locationService.getCurentCity();
		pmService.getCityByName(currentCity, new UpdateTask() {
			@Override
			public void onUpdate(CityItem item) {
				setList(item);
			}

			@Override
			public void onErr(String msg) {
				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void onFinal() {
				refreshItem.setLoading(false);
			}
		});
	}

}
