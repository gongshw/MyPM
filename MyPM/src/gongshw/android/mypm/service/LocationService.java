package gongshw.android.mypm.service;

import android.content.Context;
import android.location.Location;


public interface LocationService {
	String getCurentCity();
	String getCityByLocation(Location loc);
	public void setContext(Context context);
}
