package eu.areamobile.android.course;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class InputFragment extends Fragment implements OnClickListener{

	private final static String TAG = InputFragment.class.getSimpleName(); 
	private EditText mInput;
	private OnNewInputListener mListener;
	
	
	public static interface OnNewInputListener{
		public void onNewInput(String inputText);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof OnNewInputListener){
			mListener=(OnNewInputListener)activity;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		final View v = inflater.inflate(R.layout.input, container);
		mInput=(EditText)v.findViewById(R.id.input_text);
		v.findViewById(R.id.btn_submit).setOnClickListener(this);
		return v;
	}

	public void setListener(OnNewInputListener listener){
		mListener=listener;
	}
	
	@Override
	public void onClick(View button) {
		String text=mInput.getText().toString();
		if(TextUtils.isEmpty(text)){
			
		}else{
			if(mListener!=null){
				mListener.onNewInput(text);
			}
		}
		Log.i(TAG, text);
	}
}
