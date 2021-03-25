package com.yunfu.help.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.yunfu.help.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {

	private Context context;
	@Override
	public void onReceive(Context context, Intent intent) {
		this.context=context;
		try {
			Bundle bundle = intent.getExtras();
//			LogUtils.e("[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle,1));

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				LogUtils.e ("[MyReceiver] 接收Registration Id : " + regId);
				//send the Registration Id to your server...

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {

			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

				//播放语音
				printBundle(bundle,1);

			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				Map<String,Object> valueMap=printBundle(intent.getExtras(),2);
				if(valueMap==null){
					return;
				}

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				LogUtils.e( "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
			}
		} catch (Exception e){

		}

	}

	// 打印所有的 intent extra 数据
	private Map<String, Object> printBundle(Bundle bundle,final int type) {
		LogUtils.e("+++++++++++++++"+type);
		Map<String, Object> valueMap=new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {

			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {


				valueMap.put("notificationId",bundle.getInt(key));
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));

			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){

			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					LogUtils.e( "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						if(myKey.equals("workOrderNo")){
							valueMap.put("workOrderNo",json.optString(myKey));
						}

						sb.append("\nkey:" + key + ", value: [" + myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					LogUtils.e("Get message extra JSON error!");
				}

			}else{
				sb.append("\nkey:" + key + ", value:" + bundle.get(key));
			}
		}
		LogUtils.e(sb.toString());
		return valueMap;
	}
	
}
