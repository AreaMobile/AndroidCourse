package eu.areamobile.android.course;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import eu.areamobile.android.course.data.TodoApi;

public class TodoAdapter extends CursorAdapter{

	private LayoutInflater mInflater;
	private boolean mFoundIndexes;
	private int titleIndex;
	private int dueDateIndex;
	private int doneIndex;
	
	public TodoAdapter(Context context, Cursor c) {
		super(context, c,TodoAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		mInflater = LayoutInflater.from(context);
		mFoundIndexes = false;
	}

	@Override
	public void bindView(View row, Context context, Cursor cursor) {
		TextView tv = (TextView)row.getTag();
		
		if(!mFoundIndexes){
			titleIndex = cursor.getColumnIndex(TodoApi.Todo.Fields.TITLE);
			dueDateIndex = cursor.getColumnIndex(TodoApi.Todo.Fields.DUE_DATE);
			doneIndex = cursor.getColumnIndex(TodoApi.Todo.Fields.DONE);
			mFoundIndexes=true;
		}
		String title = cursor.getString(titleIndex);
		int date = cursor.getInt(dueDateIndex);
		int done = cursor.getInt(doneIndex);
		
		tv.setText(String.format("%s:  %d %s", title,date,done==1?"done":"todo"));
	}
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup listView) {
		View view =mInflater.inflate(R.layout.todo_row, listView,false);
		View tv = view.findViewById(R.id.row_todo_text);
		view.setTag(tv);
		return view;
	}

}
