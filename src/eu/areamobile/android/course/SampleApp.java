package eu.areamobile.android.course;

import android.app.Application;

public class SampleApp extends Application{
	private static SampleApp me;
	
	private String mGlobalName;
	
	public void setName(String m){
		mGlobalName=m;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		me=this;
	}
	
	public SampleApp get(){
		return me;
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	
	
	
}
