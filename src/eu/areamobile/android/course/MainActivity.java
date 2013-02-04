package eu.areamobile.android.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

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
//		mInput=(InputFragment)mgr.findFragmentById(R.id.InputFragment);
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

}
