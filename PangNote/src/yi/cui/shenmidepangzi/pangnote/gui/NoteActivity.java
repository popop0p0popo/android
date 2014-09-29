package yi.cui.shenmidepangzi.pangnote.gui;

import java.util.Calendar;

import yi.cui.shenmidepangzi.pangnote.R;
import yi.cui.shenmidepangzi.pangnote.R.id;
import yi.cui.shenmidepangzi.pangnote.R.layout;
import yi.cui.shenmidepangzi.pangnote.R.menu;
import yi.cui.shenmidepangzi.pangnote.dao.NoteDataSource;
import yi.cui.shenmidepangzi.pangnote.object.Note;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class NoteActivity extends ActionBarActivity {
	
	private NoteDataSource ds;
	private long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note, menu);
		
		ds = new NoteDataSource(this);
		ds.open();
		
		id = saveNote().getId();
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (item.getItemId() == R.id.action_finish) {
			this.finish();
			
            return true;
        }
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPause() {
		modifyNote();
		ds.close();
		super.onPause();
	}
	
	private Note saveNote() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		String date_in_format = day + "/" + month + "/" + year  + " " + hour  + ":" + minute;
					
		EditText view = (EditText) findViewById(R.id.note_content);
		String content = view.getText().toString();
					
		int type = Note.TYPE_NOTE;
					
		return ds.insertNewNote(content, type, date_in_format);
	}
	
	private void modifyNote() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		String date_in_format = day + "/" + month + "/" + year  + " " + hour  + ":" + minute;
					
		EditText view = (EditText) findViewById(R.id.note_content);
		String content = view.getText().toString();
					
		int type = Note.TYPE_NOTE;
		
		ds.modifyNote(id, content, type, date_in_format);
	}
}
