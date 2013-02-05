package eu.areamobile.android.course;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SampleList extends FragmentActivity implements OnItemClickListener{
	
	private final static String[] DATA= new String[]{
		"Nico",
		"Federico",
		"Camelia",
		"Tiberio",
		"Andrea",
		"Flavia",
		"Flavio",
		"Roberto",
		"Domenico",
		"Leonida",
		"Giuseppe",
		"Antonio",
		"Leonida",
		"Daniela"
		
	};
	private ListView mListView;
	private ProgrammerAdapter mAdapter;
	private EditText mProgrammerInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_list);
		mListView = (ListView)findViewById(R.id.the_list);
		mProgrammerInput = (EditText)findViewById(R.id.edt_prog_name);
		findViewById(R.id.btn_add_prog).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nome = mProgrammerInput.getText().toString();
				if(TextUtils.isEmpty(nome)){
					Toast.makeText(SampleList.this, "Inseriscilo!!!!", Toast.LENGTH_SHORT).show();
				}else{
					Programmatore p = new Programmatore(nome, "COBOL");
					Constants.LIST.add(0,p);
					mAdapter.notifyDataSetChanged();
				}
				mProgrammerInput.setText("");
			}
		});
		
//		final SampleAdapter adapter = new SampleAdapter(DATA,this);
		mAdapter=new ProgrammerAdapter(Constants.LIST, this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
		final Programmatore p=mAdapter.getItem(position);
		Toast.makeText(this, String.format("%s : %s", p.getName(),p.getLanguage()), Toast.LENGTH_LONG).show();
	}
	
	
	

}
