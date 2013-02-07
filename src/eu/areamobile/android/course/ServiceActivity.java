package eu.areamobile.android.course;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import eu.areamobile.android.course.SampleService.MyBinder;

public class ServiceActivity extends FragmentActivity 
implements OnClickListener,
ServiceConnection{

	public static final String REPLY_PARAM = "REPLY";
	private final static String TAG = ServiceActivity.class.getSimpleName();
	
	private class LocalBroadcastReceiver extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d("RECEIVER", "Roger!");
			
		}
		
	}
	public final static String BROADCAST_ACTION= "eu.areamobile.course.LOG_CONSOLE";
	private final static IntentFilter FILTER = new IntentFilter(BROADCAST_ACTION);
	
	private MyBinder mBinder;
	private LocalBroadcastReceiver mReceiver;
	private LocalBroadcastManager mManager;
	private Button mDosum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_layout);
		findViewById(R.id.btn_start_action).setOnClickListener(this);
		findViewById(R.id.btn_disconnect).setOnClickListener(this);
		mDosum =(Button)findViewById(R.id.btn_do_sum);
		mDosum.setEnabled(false);
		mDosum.setOnClickListener(this);
		
		mManager=LocalBroadcastManager.getInstance(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start_action:
//			START A SERVICE ACTION
//			final Intent intent = new Intent(this,SampleService.class);
//			startService(intent);
			
//			BIND TO A SERVICE
//			connect();
			
//			START THE INTENT SERVICE
//			final Intent intent = new Intent(this,SampleIntentService.class);
//			startService(intent);
			break;
		case R.id.btn_disconnect:
			disconnect();
			break;
		case R.id.btn_do_sum:
			logResult();
			break;
		default:
			break;
		}
	}
	
	private void startServiceWithReplyAddress(){
		final Intent intent = new Intent(this,SampleIntentService.class);
//		intent.putExtra(REPLY_PARAM, BROADCAST_ACTION);
		
		final Intent reply = new Intent(BROADCAST_ACTION);
		PendingIntent pending = PendingIntent.getBroadcast(this, 0, reply, PendingIntent.FLAG_UPDATE_CURRENT);
		PendingIntent pending2 = PendingIntent.getActivity(this, 0, new Intent(this,MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
		intent.putExtra(REPLY_PARAM, pending2);
	}
	@Override
	protected void onResume() {
		super.onResume();
		mReceiver = new LocalBroadcastReceiver();
//		Intent lastMessage =registerReceiver(mReceiver, FILTER);
//		mManager.registerReceiver(mReceiver, FILTER);
		Intent last =registerReceiver(mReceiver, FILTER);
		if(last!=null){
			Log.d(TAG, "MAN I KNOW!!!!!");
		}
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
// 		BINDING
//		unbindService(this);
		
//		unregisterReceiver(mReceiver);
		// LOCAL BROADCAST MANAGER
//		mManager.unregisterReceiver(mReceiver);
		unregisterReceiver(mReceiver);
		mReceiver=null;
	}
	
	
	
	
	
	// BINDING TO A SERVICE
	private void connect(){
		Intent intent = new Intent(this,SampleService.class);
		bindService(intent, this, Service.BIND_AUTO_CREATE);
	}

	private void disconnect(){
		unbindService(this);
	}
	
	private void logResult(){
		Log.d(TAG, "Il risultato è"+mBinder.somma(40, 20));
	}
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		MyBinder b = (MyBinder)service;
		mDosum.setEnabled(true);
		this.mBinder=b;
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		mDosum.setEnabled(false);
		this.mBinder=null;
		
	}
}
