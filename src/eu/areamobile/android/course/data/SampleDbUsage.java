package eu.areamobile.android.course.data;

import android.database.sqlite.SQLiteDatabase;

public class SampleDbUsage {
	
	
	public void howToOpenADb(TodoDbHelper helper){
//		helper.getReadableDatabase();
		SQLiteDatabase db = helper.getWritableDatabase();
		
//		db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
		
		db.yieldIfContendedSafely();
		db.close();
		db.beginTransaction();
		try{
			///
			db.setTransactionSuccessful();
		}finally{
			db.endTransaction();
		}
	}
}
