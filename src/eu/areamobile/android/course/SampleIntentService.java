package eu.areamobile.android.course;

import android.app.IntentService;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class SampleIntentService extends IntentService{

	private final static String TAG=SampleIntentService.class.getSimpleName();
	public SampleIntentService() {
		super(TAG);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "HELLO...I LOVE YOU...");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "... MY ONLY FIREND!");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "LET ME JUMP...IN YOUR GAME...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		Log.d(TAG, "THIS IS THE END...");
//		sendBroadcast(new Intent(ServiceActivity.BROADCAST_ACTION));
		
//		sendStickyBroadcast(new Intent(intent.getStringExtra(ServiceActivity.REPLY_PARAM)));
		
//		SEND BROADCASTS LOCALLY
//		LocalBroadcastManager
//				.getInstance(this)
//				.sendBroadcast(new Intent(ServiceActivity.BROADCAST_ACTION));
		
		final PendingIntent reply=intent.getParcelableExtra(ServiceActivity.REPLY_PARAM);
		try {
			reply.send();
		} catch (CanceledException e) {
					}
	}

}
