package eu.areamobile.android.course;

import java.util.Set;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Environment;

public class PreferenceAccess {

	private static final String OTHERWISE_IF_NOT_PRESENT = "default value if not present";
	private final static String PREFS_FILE = "GLOBAL_PREFS";
	private final static String USER_NAME_KEY = "username";
	
	@SuppressLint("NewApi")
	public static void saveSomethingInPrefs(Context context,Activity a){
		final SharedPreferences p = a.getPreferences(Context.MODE_PRIVATE);
		
		final SharedPreferences prefs = 
				context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
		
		Editor edit2 = prefs.edit();
		
		prefs.edit()
			 .putString(USER_NAME_KEY, "Andrea")
			 .putBoolean("FIRST_TIME", false)
			 
			 .commit(); // Commit scrive in modo sincrono su disco
						// restituisce true se è tutto ok
		
		// oppure:
		prefs.edit()
			 .putString(USER_NAME_KEY, "Andrea Smarter")
			 .apply(); // Scrive in modo asincrono ma funziona dall'api 9
		
		// oppure:
		final SharedPreferences.Editor edit=prefs.edit()
			 .putString(USER_NAME_KEY, "Andrea The MIGHTY!!!");
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
			edit.apply();
		}else{
			new Thread(){
				public void run() {edit.commit();};
			}.start();
		}
		
		
		
	}
	
	private void privateFolder(Context context){
		context.getFilesDir();
		context.getCacheDir();
		
//		context.getExternalFilesDir();
	}
	
	@SuppressLint("NewApi")
	private static void getSomethingFromPrefs(Context context){
		final SharedPreferences prefs =
				context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
		String userName =prefs.getString(USER_NAME_KEY, OTHERWISE_IF_NOT_PRESENT);
		
		
		prefs.getBoolean("BOOLEAN_PREF", false);
//		Set<String> s = prefs.getStringSet("SET_PREFS", null);
//		Set<String> s2 = prefs.getStringSe("SET_PREFS", null);
	}
	
	
}
