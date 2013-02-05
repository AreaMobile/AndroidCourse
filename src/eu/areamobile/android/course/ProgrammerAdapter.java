package eu.areamobile.android.course;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ProgrammerAdapter extends BaseAdapter{
	
	private final List<Programmatore> mList;
	private final LayoutInflater mInflater;
	public ProgrammerAdapter(List<Programmatore>list,Context context) {
		mList=list;
		mInflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}
	
	public Programmatore getItem(int position) {
		return mList.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		final View v;
		final ViewHolder holder;
		if(convertView==null){
			v= mInflater.inflate(R.layout.programmatore_row, parent,false);
			holder= new ViewHolder();
			holder.nome= (TextView)v.findViewById(R.id.row_name);
			holder.lang = (TextView)v.findViewById(R.id.row_language);
			holder.check= (CheckBox)v.findViewById(R.id.check_row);
			holder.check.setOnCheckedChangeListener(null);
			v.setTag(holder);
		}else{
			v=convertView;
			holder= (ViewHolder)v.getTag();
		}
		
		final Programmatore p = getItem(position);
		holder.check.setChecked(p.getChecked());
		holder.nome.setText(p.getName());
		holder.lang.setText(p.getLanguage());
		return v;
	}

	private static class ViewHolder{
		public TextView nome;
		public TextView lang;
		public CheckBox check;
	}
}
