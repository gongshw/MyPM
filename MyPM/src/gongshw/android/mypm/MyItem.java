package gongshw.android.mypm;

import android.content.Context;
import android.view.ViewGroup;

import greendroid.widget.item.SubtitleItem;
import greendroid.widget.itemview.ItemView;

public class MyItem extends SubtitleItem {
	
	public MyItem(String title,String subTitle){
		super(title,subTitle);
	}
	
	@Override
    public ItemView newView(Context context, ViewGroup parent) {
        return createCellFromXml(context, R.layout.my_item, parent);
    }
}
