

package eu.areamobile.android.course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OutputFragment extends Fragment{
	
	private TextView mOutput;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		final View v = inflater.inflate(R.layout.output, container);
		mOutput=(TextView)v;
		return v;
	}
	
	public void setOutputText(String text){
		mOutput.setText(text);
	}
}
