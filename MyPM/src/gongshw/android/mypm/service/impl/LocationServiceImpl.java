package gongshw.android.mypm.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import gongshw.android.mypm.service.LocationService;

public class LocationServiceImpl implements LocationService {

	private static final String DEFAULT_CITY = "南京";
	private Context context;
	private LocationManager locationManager;
	private LocationListener locationListener;
	public boolean providerEnable;
	private String provider;

	@Override
	public String getCurrentCity() {
		locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		setNullListener();
		setProvider(LocationManager.GPS_PROVIDER);
		if (!checkProvider())
			setProvider(LocationManager.NETWORK_PROVIDER);
		checkProvider();
		Location loc  = locationManager.getLastKnownLocation(provider);
		return getCityByLocation(loc);
	}

	private void setNullListener() {
		locationListener = new LocationListener() {
			public void onLocationChanged(Location arg0) {
			}

			public void onProviderDisabled(String arg0) {
			}

			public void onProviderEnabled(String arg0) {
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {

			}
		};
	}

	private boolean checkProvider() {
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null)
			return providerEnable = true;
		return providerEnable = false;
	}

	private void setProvider(String provider) {
		locationManager.requestLocationUpdates(provider, 1000, 0,
				locationListener);
		this.provider = provider;
	}

	@Override
	public String getCityByLocation(Location location) {
		Geocoder geocoder = new Geocoder(context, Locale.getDefault());
		List<Address> addrs;
		try {
			addrs = geocoder.getFromLocation(location.getLatitude(),
					location.getLongitude(), 1);
		} catch (IOException e) {
			e.printStackTrace();
			return DEFAULT_CITY;
		}
		if (addrs.size()>0) {
			Address add = addrs.get(0);
			return add.getLocality().replace("市", "");
		} else {
			return DEFAULT_CITY;
		}
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
