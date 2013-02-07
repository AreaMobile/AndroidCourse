package eu.areamobile.android.course;

import java.io.IOException;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.JsonReader;
import android.util.Log;
import eu.areamobile.android.net.Http;
import eu.areamobile.android.net.Http.ResponseStream;
import eu.areamobile.android.net.HttpException;

public class SampleConnecting {

	private static final String BASE_URL=
			"https://areadroid-areamobile.rhcloud.com/course/comments";
	
	public static void postInBackground(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				postJsons();
			}
		}).start();
	}
	public static void downloadInBackground(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				downloadJsons();
			}
		}).start();
	}
	
	public static void postJsons(){
		ResponseStream res=null;
		try{
			
			JSONObject obj = new JSONObject();
			obj.put("user", "Androidino");
			obj.put("title", "Ping");
			obj.put("body", "First message of modern times");
			obj.put("date", System.currentTimeMillis());
			
			res = Http.post(BASE_URL)
					.header("Content-Type","application/json;charset=utf8")
					.body(obj.toString())
					.execute();
			if(res.statusClass==2){
				Log.d("SAMPLE", "Gotit!!!");
				/*
				 * { status : ok,
				 *   id : dsafafas
				 *   }
				 * */
				
				Log.d("SAMPLE",res.asString());
			}else{
				Log.d("SAMPLE","Dammit!!!");
			}
		}catch(HttpException e){
			Log.d("SAMPLE", "WRONG HTTP!",e);
		} catch (JSONException e) {
			Log.d("SAMPLE", "WRONG JSON!");
		}finally{
			if(res!=null){
				try {
					res.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
	public static void downloadJsons(){
		
		ResponseStream res=null;
		try {
			res = Http.get(BASE_URL).execute();
			if(res.statusCode==200){
				String body =res.asString();
				Reader r=res.asReader();
				
				
				Log.d("SAMPLE", body);
			}else{
				Log.d("SAMPLE", "not ok "+res.statusCode);
			}
			
		} catch (HttpException e) {
			Log.d("SAMPLE", "failed ",e);
		} finally{
			if(res!=null){
				try {
					res.close();
				} catch (IOException e1) {
				}
			}
		}
		
	}
}
