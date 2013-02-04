package eu.areamobile.android.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class OutputActivity extends FragmentActivity{
	public final static String EXTRA_MESSAGE = "extra_message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output_activity);
		Intent startMessage =getIntent();
		final String text =startMessage.getStringExtra(EXTRA_MESSAGE);
		setOutputText(text);
		
		findViewById(R.id.btn_ok).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent result = new Intent();
				result.putExtra(EXTRA_MESSAGE, text);
//				setResult(RESULT_CANCELED);
				setResult(RESULT_OK,result);
				finish();
			};
		});
	}

	private void setOutputText(String text){
		final OutputFragment out=(OutputFragment)getSupportFragmentManager()
				.findFragmentById(R.id.OutputFragment2);
		
		out.setOutputText(text);
	}
}
