package eu.areamobile.android.course.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class TodoDbHelper extends SQLiteOpenHelper{

	public TodoDbHelper(Context context) {
		super(context, DataConstant.DB_NAME, null, DataConstant.DB_VERSION);
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DataConstant.CREATE_TODO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion>oldVersion){
			db.execSQL(DataConstant.DROP_TODO);
		}
		onCreate(db);
	}

}
