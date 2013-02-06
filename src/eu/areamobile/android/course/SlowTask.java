package eu.areamobile.android.course;

import java.lang.ref.WeakReference;

import android.os.AsyncTask;
import android.util.Log;

public class SlowTask extends AsyncTask<Integer, Void, String>{
	
	private WeakReference<AsyncActivity> memorySafer;
	private AsyncActivity activity;
	SlowTask(AsyncActivity a) {
		activity =a;
		memorySafer = new WeakReference<AsyncActivity>(a);
		
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d(AsyncActivity.TAG,"Async task is about to start");
		
	}
	
	@Override
	protected String doInBackground(Integer... params) {
		Log.d(AsyncActivity.TAG,"Async task background");
		final AsyncActivity a=memorySafer.get();
		if(a==null){
			
		}else{
			//a.append("");
		}
		// Operazione costosa
		// per esempio scarico json
		try {
			Thread.sleep(params[0]);
		} catch (InterruptedException e) {
			
		}
		return "OK ASYNC-TASK";
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.d(AsyncActivity.TAG,"Async task finished");
		activity.append(result);
		final AsyncActivity as = memorySafer.get();
		if(as!=null){
			as.append(result);
		}
	}
	
}
