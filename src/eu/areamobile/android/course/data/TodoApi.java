package eu.areamobile.android.course.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class TodoApi {

	public final static String AUTHORITY = "eu.areamobile.android.course.todo";
	
	final static Uri BASE_URI = Uri.parse("content://"+AUTHORITY);

	public final static class Todo{
		
		final static String PATH = "todo";
		public final static String MIME_TYPE = "vnd.eu.areamobile"+PATH;
		//content://eu.areamobile.../todo
		public final static Uri URI = BASE_URI.buildUpon().appendPath(PATH).build();
		public final static String TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE+'/'+MIME_TYPE;
		public final static String ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+'/'+MIME_TYPE;
		public final static class Fields{
			public final static String _ID = BaseColumns._ID;
			public final static String TITLE = "title";
			public final static String DONE = "done";
			public final static String DUE_DATE = "due_date";
		}
	}
	
}
