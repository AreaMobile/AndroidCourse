package eu.areamobile.android.course.data;

import android.content.UriMatcher;

final class DataConstant {
	private DataConstant(){}
	
	final static String DB_NAME = "COMMENTS_DB";
	
	final static int DB_VERSION = 1;
	
	final static String CREATE_TODO = 
			"CREATE TABLE IF NOT EXISTS "+ TodoApi.Todo.PATH +" ("+
				TodoApi.Todo.Fields._ID+ " INTEGER PRIMARY KEY,"+
				TodoApi.Todo.Fields.TITLE+" TEXT," +
				TodoApi.Todo.Fields.DUE_DATE+" INTEGER," +
				TodoApi.Todo.Fields.DONE+" INTEGER"+
				")";
	
	final static String DROP_TODO = "DROP TABLE IF EXISTS "+TodoApi.Todo.PATH;
	
	
	final static int TODO_COLLECTION = 1;
	final static int TODO_ITEM = 2;
	final static UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	static{
		MATCHER.addURI(TodoApi.AUTHORITY, TodoApi.Todo.PATH, TODO_COLLECTION);
		MATCHER.addURI(TodoApi.AUTHORITY, TodoApi.Todo.PATH+"/#", TODO_ITEM);
	}
}
