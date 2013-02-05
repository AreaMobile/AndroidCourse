package eu.areamobile.android.course;

public class Programmatore {

	private final String name;
	private final String language;
	private boolean checked;
	
	public Programmatore(String name,String language) {
		this.name=name;
		this.language=language;
	}
	
	public void setChecked(boolean b){
		checked=b;
	}
	public boolean getChecked(){
		return checked;
	}
	public String getLanguage() {
		return language;
	}
	
	public String getName() {
		return name;
	}
}
