package gongshw.android.mypm;

import gongshw.android.mypm.model.CityItem;

/**
 * 这个接口定义个对请求PM值这个异步过程的回调处理。
 * onUpdate(CityItem item)将会在请求成功的情况下被调用,参数是城市污染信息。
 * onErr(String msg)将会在请求失败时被调用,参数是失败信息。
 * onFinal()将会在请求完成时被调用,不论这次请求是成功还是失败。
 */
public interface UpdateTask {
	void onUpdate(CityItem item);
	void onErr(String msg);
	void onFinal();
}
