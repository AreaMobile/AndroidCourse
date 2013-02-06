package eu.areamobile.android.course;

import java.util.Date;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AsyncActivity extends FragmentActivity implements OnClickListener{
	final static String TAG= "CONSOLE";
	private TextView mConsole;
	private TextView mConsole2;
	private SlowTask mTask;
	private boolean mStarted;
	private final static int MSG_FIRST_CONSOLE= 1;
	private final static int MSG_SECOND_CONSOLE= 2;
	
	private MyHandler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_layout);
		findViewById(R.id.btn_start_action).setOnClickListener(this);
		mConsole = (TextView)findViewById(R.id.console_output);
		mConsole2 = (TextView)findViewById(R.id.console_output2);
		mHandler = new MyHandler(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(mStarted){
			mTask=startAsyncTaskControlled(2000);
		}
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		mTask.cancel(true);
		mTask =null;
		mStarted =true;
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
	@Override
	public void onClick(View v) {
		//slowOperation();
		//slooooooow(20000);
		startAsyncTask(20000);
		mTask =startAsyncTaskControlled(20000);
	}
	
	
	
	private void startAsyncTask(int time){
		SlowTask task = new SlowTask(this);
		task.execute(time);
	}
	
	private SlowTask startAsyncTaskControlled(int time){
		SlowTask task = new SlowTask(this);
		task.execute(time);
		return task;
	}
	private void pubblishMessage(int which,String text){
		Message m = mHandler.obtainMessage();
		m.what=which;
		m.obj=text;
		mHandler.sendMessage(m);
	}
	
	private void delayed(){
		mHandler.sendMessageDelayed(mHandler.obtainMessage(4), 2000);
		mHandler.sendMessageAtTime(mHandler.obtainMessage(4), new Date(2013, 1, 1).getTime());
	}
	private void pubblishMessage(Runnable r){
		mHandler.post(r);
	}
	
	void append(String text){
		mConsole.append(text+'\n');
	}
	
	private static class MyHandler extends Handler{
		
		private final AsyncActivity activity;
		
		MyHandler(AsyncActivity activity){
			super();
			this.activity=activity;
		}
		
		
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_FIRST_CONSOLE:
				activity.mConsole.append((String)msg.obj);
				break;
			case MSG_SECOND_CONSOLE:
				activity.mConsole2.append((String)msg.obj);
				break;
			default:
				super.handleMessage(msg);
			}
			
		}
	}
	
	private void slowOperation(){
		Log.d(TAG, "LAUNCHING...");
		append("LAUNCHING...");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Log.d(TAG,"STARTING...");
				Runnable action = new Runnable() {
					
					@Override
					public void run() {
						append("STARTING...");
					}
				};
				pubblishMessage(action);
				AsyncActivity.this.runOnUiThread(action);
				//append("STARTING...");
				slooooooow(2000);
				Log.d(TAG, "DONE!!!!");
				
				AsyncActivity.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						append("DONE!");
					}
				});
			}

			
			
		}).start();
		Log.d(TAG, "LAUNCHED!!!");
		append("LAUNCHED!");
	}
	
	private void slooooooow(int howmuch) {
		try {
			Thread.sleep(howmuch);
		} catch (InterruptedException e) {
	}
}

}
