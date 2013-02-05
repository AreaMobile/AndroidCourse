package eu.areamobile.android.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SampleAdapter extends BaseAdapter{

	private final String[] mDataSet;
	private final LayoutInflater mInflater;
	public SampleAdapter(String[] data,Context context){
		mDataSet=data;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public String getItem(int position) {
		return mDataSet[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getCount() {
		return mDataSet.length;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final View v;
		if(convertView==null){
			v=mInflater.inflate(R.layout.list_row, parent,false);
		}else{
			v=convertView;
		}
		TextView tv = (TextView)v;
		tv.setText(getItem(position));
		return v;
	}

}
