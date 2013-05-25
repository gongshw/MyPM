package gongshw.android.mypm.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CityItem implements Iterable<PollutionItem>{
	
	private List<PollutionItem> items = new ArrayList<PollutionItem>();
	
	private String id;
	
	private String name;
	
	private String updateTime;
	
	private int aqiValue;
	
	private String quality; 
	
	public CityItem(PollutionFeedback pollutionFeedback){
		
		setName(pollutionFeedback.getArea());
		setUpdateTime(pollutionFeedback.getTime_point());
		setAqiValue(pollutionFeedback.getAqi());
		setQuality(pollutionFeedback.getQuality());
		
		for(Field field:PollutionFeedback.class.getDeclaredFields()){
			try {
				PollutionItem item = new PollutionItem();
				String fieldName= PollutionItem.getItemName(field.getName()); 
				item.setName(fieldName);
				field.setAccessible(true);
				Object value = field.get(pollutionFeedback);
				if (value!=null&&fieldName!=null) {
					item.setValue(value.toString());
					items.add(item);
				} 
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public Iterator<PollutionItem> iterator() {
		return items.iterator();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getAqiValue() {
		return aqiValue;
	}

	public void setAqiValue(int aqiValue) {
		this.aqiValue = aqiValue;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}
	
}
