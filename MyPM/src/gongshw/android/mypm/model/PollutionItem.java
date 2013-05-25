package gongshw.android.mypm.model;

import java.util.HashMap;
import java.util.Map;

public class PollutionItem {
	
	private static Map<String, String> _FIELD_MAP = new HashMap<String, String>();
	
	static{
		//_FIELD_MAP.put("aqi", "空气质量指数");
		//_FIELD_MAP.put("area","城市名称");
		//_FIELD_MAP.put("position_name","监测点名称");
		//_FIELD_MAP.put("station_code", "监测点编码");
		_FIELD_MAP.put("so2", "二氧化硫1小时平均");
		_FIELD_MAP.put("so2_24h", "二氧化硫24小时滑动平均");
		_FIELD_MAP.put("no2", "二氧化氮1小时平均");
		_FIELD_MAP.put("no2_24h", "二氧化氮24小时滑动平均");
		_FIELD_MAP.put("pm10", "颗粒物（粒径小于等于10μm）1小时平均");
		_FIELD_MAP.put("pm10_24h", "颗粒物（粒径小于等于10μm）24小时滑动平均");
		_FIELD_MAP.put("co", "一氧化碳1小时平均");
		_FIELD_MAP.put("co_24h", "一氧化碳24小时滑动平均");
		_FIELD_MAP.put("o3", "臭氧1小时平均");
		_FIELD_MAP.put("o3_24h", "臭氧24小时滑动平均");
		_FIELD_MAP.put("o3_8h", "臭氧8小时滑动平均");
		_FIELD_MAP.put("o3_8h_24h", "臭氧8小时滑动平均的24小时均值");
		_FIELD_MAP.put("pm2_5", "颗粒物（粒径小于等于2.5μm）1小时平均");
		_FIELD_MAP.put("pm2_5_24h", "颗粒物（粒径小于等于2.5μm）24小时滑动平均");
		_FIELD_MAP.put("primary_pollutant", "首要污染物");
		//_FIELD_MAP.put("quality", "空气质量指数类别，有“优、良、轻度污染、中度污染、重度污染、严重污染”6类");
		//_FIELD_MAP.put("time_point", "数据发布的时间");
	}
	
	public static String getItemName(String key){
		return _FIELD_MAP.get(key);
	}
	
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
