package gongshw.android.mypm.service.impl;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.util.Log;
import gongshw.android.mypm.UpdateTask;
import gongshw.android.mypm.model.CityItem;
import gongshw.android.mypm.model.PollutionFeedback;
import gongshw.android.mypm.service.PmService;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

public class PmServiveImpl implements PmService {
	
	private static final String APP_KEY = "edZ8e8jDbWuuA5VAKqLP";
	private static final String HOST = "http://pm25.in/api/querys/aqi_details.json";
	private static final String PARAM_CITY = "city";
	private static final String PARAM_TOKEN = "token";

	@Override
	public void getCityByName(String cityName,final UpdateTask callBack) {
		
		FinalHttp fh = new FinalHttp();
		fh.get(getApiUrl(cityName), new AjaxCallBack<String>(){
			@Override
		    public void onSuccess(String t) {
				if (callBack!=null) {
					Gson gson = new Gson();
					try{
					PollutionFeedback pfs[] = gson.fromJson(t, PollutionFeedback[].class);
					PollutionFeedback cityfb = pfs[pfs.length-1];
					CityItem ci = new CityItem(cityfb);
					ci.setName(cityfb.getArea());
					callBack.onUpdate(ci);
					} catch(JsonSyntaxException e){
						Map<String, String> map = gson.fromJson(t, new TypeToken<Map<String, String>>(){}.getType());
						String errStr = map.get("error");
						callBack.onErr(errStr!=null?errStr:"未知错误");
					}
					callBack.onFinal();
				}
			}
			
			@Override
			public void onFailure(Throwable tr,String msg){
				Log.v("dbg", msg, tr);
				callBack.onErr(msg);
				callBack.onFinal();
			}
			
			
			
		});
	}
	
	private String getApiUrl(String cityName){
		StringBuffer sb = new StringBuffer();
		sb.append(HOST);
		sb.append('?');
		sb.append(PARAM_CITY);
		sb.append('=');
		sb.append(cityName);
		sb.append('&');
		sb.append(PARAM_TOKEN);
		sb.append('=');
		sb.append(APP_KEY);
		return sb.toString();
	}

}
