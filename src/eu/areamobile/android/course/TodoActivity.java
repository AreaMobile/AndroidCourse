package eu.areamobile.android.course;

import java.util.Random;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import eu.areamobile.android.course.data.TodoApi;

public class TodoActivity extends FragmentActivity implements OnClickListener,
LoaderCallbacks<Cursor>{

	private final static int TODO_LOADER = 1;
	ListView mListView;
	TodoAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_layout);
		findViewById(R.id.add_default_todo).setOnClickListener(this);
		mListView = (ListView)findViewById(R.id.lv_todos);
		mAdapter = new TodoAdapter(this, null);
		mListView.setAdapter(mAdapter);
		getSupportLoaderManager().initLoader(TODO_LOADER, null, this);
	}
	
	
	@Override
	public void onClick(View v) {
		//insertTodo();
		insertAsync();
	}
	
	private void insertAsync(){
		new Thread(){
			public void run() {insertTodo();};
		}.start();
	}
	
	private void getSingleTodo(int id){
		ContentResolver cr = getContentResolver();
		Uri target = ContentUris.withAppendedId(TodoApi.Todo.URI, id);
		Cursor todoCursor =cr.query(target, null, null, null, null);
		boolean ok =todoCursor.moveToFirst();
		if(ok){
			int idPos = todoCursor.getColumnIndex(TodoApi.Todo.Fields._ID);
			long foundId = todoCursor.getLong(idPos);
			
			int titlePos = todoCursor.getColumnIndex(TodoApi.Todo.Fields.TITLE);
			String foundtitle = todoCursor.getString(titlePos);
			
		}
	}
	
	private void insertTodo(){
		ContentResolver cr = getContentResolver();
		Uri target = TodoApi.Todo.URI;
		Random rand = new Random();
		ContentValues cv = new ContentValues();
		cv.put(TodoApi.Todo.Fields.TITLE, "Default");
		cv.put(TodoApi.Todo.Fields.DONE, rand.nextBoolean());
		cv.put(TodoApi.Todo.Fields.DUE_DATE, System.currentTimeMillis()+rand.nextInt());
		cr.insert(target, cv);
	}


	@Override
	public Loader<Cursor> onCreateLoader(int loaderId, Bundle arg) {
//		if(loaderId==TODO_LOADER){
//			
//		}
		CursorLoader loader = 
				new CursorLoader(this, TodoApi.Todo.URI, null, null, null, 
						TodoApi.Todo.Fields.DUE_DATE+ " asc");
		return loader;
	}


	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//		if(loader.getId()==TODO_LOADER){
//			
//		}
		//Quando abbiamo adpater passare cursor
		mAdapter.swapCursor(cursor);
	}


	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		//
		mAdapter.swapCursor(null);
	}








}
