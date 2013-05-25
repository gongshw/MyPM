package gongshw.android.mypm.service;

import gongshw.android.mypm.UpdateTask;

public interface PmService {
    /**
     * @param cityName 要请求信息的城市名
     * @param callBack 对请求的回调函数。接口UpdateTask包含三个需要实现的方法,分别定义了请求成功、失败和终止时的回调方法。
     */
	public void getCityByName(String cityName,UpdateTask callBack);
}
