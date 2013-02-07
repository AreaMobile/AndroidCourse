package eu.areamobile.android.course;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class SampleService extends Service{

	private final static String TAG= SampleService.class.getSimpleName();
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "CREATED!!!");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "COMMAND RECEIVED");
//		doAsyncAction(intent,startId);
		doAction(intent, startId);
		return Service.START_REDELIVER_INTENT;
		//return super.onStartCommand(intent, flags, startId);
	}
	
	void doAction(Intent intent,int startId){
		// SOME WORK

		Log.d(TAG, "BEGINNING ACTION "+startId);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
//		stopSelf();
		Log.d(TAG, "FINISHED ACTION "+startId);
		stopSelf(startId);
	}
	
	public void doAsyncAction(final Intent intent,final int startId){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				doAction(intent,startId);
			}
		}).start();
		
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "DESTORYED");
	}
	
	
	
	
	public int sommaDueNumenri(int a,int b){
		return a+b;
	}
	public class MyBinder extends Binder{
		public SampleService getService(){
			return SampleService.this;
		}
		
		public int somma(int a, int b){
			return SampleService.this.sommaDueNumenri(a, b);
		}
	}
	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
	
	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
	}
}
