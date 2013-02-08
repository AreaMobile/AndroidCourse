package eu.areamobile.android.course.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class TodoProvider extends ContentProvider{

	private TodoDbHelper mDb;
	@Override
	public boolean onCreate() {
		mDb = new TodoDbHelper(getContext());
		return true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		final int match = DataConstant.MATCHER.match(uri);
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(TodoApi.Todo.PATH);
		switch (match) {
		case DataConstant.TODO_COLLECTION:
			break;
		case DataConstant.TODO_ITEM:
			qb.appendWhere("_id = " +uri.getLastPathSegment());
			break;
		default:
			unsupportedUri(uri);
		}
		
		SQLiteDatabase db=mDb.getReadableDatabase();
		
		final Cursor cursor = qb.query(db, projection, 
				selection, 
				selectionArgs, 
				null, 
				null, 
				sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		// questa operazione non è permessa
		if(DataConstant.MATCHER.match(uri)== DataConstant.TODO_ITEM){
			final String where;
			if(TextUtils.isEmpty(selection)){
				// non è stata passata alcuna where dal client
				// usiamo solo l'id passato nell'uri
				where = "_id = "+ ContentUris.parseId(uri);
			}else{
				// è stata usata una where quindi uniamo le condizioni
				where = "_id = "+ ContentUris.parseId(uri)
						+ " AND ("+selection+")";
			}
			SQLiteDatabase db = mDb.getWritableDatabase();
			final int updated =db.update(TodoApi.Todo.PATH, values,
					where, 
					selectionArgs);
			if(updated>0){
				// Se ci sono stati degli update (al più 1) notifichiamo
				// l'update
				getContext().getContentResolver().notifyChange(uri, null);
			}
			return updated;
		}else{
			throw unsupportedUri(uri);
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		return 0;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(DataConstant.MATCHER.match(uri) == DataConstant.TODO_COLLECTION){
			final SQLiteDatabase db  = mDb.getWritableDatabase();
			long id = db.insert(TodoApi.Todo.PATH, null, values);
			if(id!=-1){
				Uri u =ContentUris.withAppendedId(uri, id);
				getContext().getContentResolver().notifyChange(u,null);
				return u;
			}else{
				return null;
			}
		}
		throw unsupportedUri(uri);
	}
	
	@Override
	public String getType(Uri uri) {
		// Alcune applicazioni potrebbero essere interessate a sapere
		// di che tipo è il dato che restituiamo
		// utilizziamo per questo il MIME_TYPE
		// potremmo restituire un immagine: image/jpeg
		// o un testo: text/plain
		//
		// nel caso di tipi non predefiniti
		// il mime type secondo la specifica RFC deve contenere il
		// prefisso vnd (vendor)
		//
		// per i cursor android in particolare il mimetype sarà:
		// per un intero result set:
		// vnd.android.cursor.dir/vnd.<nostro-tipo>
		// per un singolo record:
		// vnd.android.cursor.item/vnd.<nostro-tipo>
		
		switch (DataConstant.MATCHER.match(uri)) {
		case DataConstant.TODO_COLLECTION: return TodoApi.Todo.TYPE;
		case DataConstant.TODO_ITEM: return TodoApi.Todo.ITEM_TYPE;
		default: return null;
		}
	}



	private static UnsupportedOperationException unsupportedUri(Uri uri) {
		return new UnsupportedOperationException("uri: "+uri+ " is not supported");
	}


	

}
