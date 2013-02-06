package eu.areamobile.android.course;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity 
   implements InputFragment.OnNewInputListener{
	private final static int REQUEST_PREVIEW=1;
	private OutputFragment mOtput;
//	private InputFragment mInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FragmentManager mgr = getSupportFragmentManager();
		mOtput=(OutputFragment)mgr.findFragmentById(R.id.OutputFragment);
//		InputFragment f= (InputFragment)mgr.findFragmentById(R.id.InputFragment);
//		f.setOnNewInputListener(this);
//		mInput=(InputFragment)mgr.findFragmentById(R.id.InputFragment);
		
		
		findViewById(R.id.btn_sample_list).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Intent intent =new Intent(MainActivity.this,SampleList.class);
				startActivity(intent);
			}
		});
		
		findViewById(R.id.btn_sample_async).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Intent intent =new Intent(MainActivity.this,AsyncActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onNewInput(String inputText) {
		showPreview(inputText);
	}
	
	private void showPreview(String text){
		final Intent message = new Intent(this,OutputActivity.class);
		message.putExtra(OutputActivity.EXTRA_MESSAGE, text);
		startActivityForResult(message, REQUEST_PREVIEW);
	}
	
	private void showPreviewExplicit(String text){
		final Intent message= new Intent("");
		message.addCategory(Intent.CATEGORY_DEFAULT);
		message.putExtra(OutputActivity.EXTRA_MESSAGE, text);
		try{
			startActivityForResult(message, REQUEST_PREVIEW);
		}catch(ActivityNotFoundException e){
			
		}
	}
	
	@SuppressWarnings("unused")
	private void showTextInNewActivity(String text){
		final Intent message = new Intent(this,OutputActivity.class);
		message.putExtra(OutputActivity.EXTRA_MESSAGE, text);
		startActivity(message);
	}
	
	private void setTextInline(String inputText){
		mOtput.setOutputText("Hello "+inputText+"!!");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==REQUEST_PREVIEW){
			if(resultCode==RESULT_OK){
				setTextInline(data.getStringExtra(OutputActivity.EXTRA_MESSAGE));
			}else if(resultCode==RESULT_CANCELED){
				
			}
		}
	}
	
	private void sampleAppAccess(){
		SampleApp app=(SampleApp)getApplication();
		app.setName("");
	}

}
