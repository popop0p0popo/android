package yi.cui.shenmidepangzi.pangnote.dao;

import java.util.ArrayList;
import java.util.List;

import yi.cui.shenmidepangzi.pangnote.database.PangNoteSQLiteHelper;
import yi.cui.shenmidepangzi.pangnote.object.Note;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NoteDataSource {
	
	private SQLiteDatabase db;
	private PangNoteSQLiteHelper dbHelper;
	private String[] allColumns = { PangNoteSQLiteHelper.COLUMN_ID,
			PangNoteSQLiteHelper.COLUMN_CONTENT,
			PangNoteSQLiteHelper.COLUMN_TYPE,
			PangNoteSQLiteHelper.COLUMN_TIME
	};
	
	public NoteDataSource(Context context) {
		dbHelper = new PangNoteSQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		if (db == null) {
			db = dbHelper.getWritableDatabase();
		}
	}
	
	public void close() throws SQLException {
		dbHelper.close();
	}
	
	public Note insertNewNote(String content, int type, String time) {
		ContentValues values = new ContentValues();
		values.put(PangNoteSQLiteHelper.COLUMN_CONTENT, content);
		values.put(PangNoteSQLiteHelper.COLUMN_TYPE, type);
		values.put(PangNoteSQLiteHelper.COLUMN_TIME, time);
		long id = db.insert(PangNoteSQLiteHelper.TABLE_NOTES, null, values);
		
		Cursor cursor = db.query(PangNoteSQLiteHelper.TABLE_NOTES,
				allColumns, PangNoteSQLiteHelper.COLUMN_ID + " = " + id, null,null, null, null);
		cursor.moveToFirst();
		Note newNote = cursorToNote(cursor);
		cursor.close();
		
		return newNote;
	}
	
	public int modifyNote(long id, String content, int type, String time) {
		ContentValues values = new ContentValues();
		values.put(PangNoteSQLiteHelper.COLUMN_CONTENT, content);
		values.put(PangNoteSQLiteHelper.COLUMN_TYPE, type);
		values.put(PangNoteSQLiteHelper.COLUMN_TIME, time);
		
		int count = db.update(PangNoteSQLiteHelper.TABLE_NOTES,
				values,
				PangNoteSQLiteHelper.COLUMN_ID + " = " + id,
				null);
		return count;
	}
	
	public void deleteNote(Note note) {
		long id = note.getId();
		
		db.delete(PangNoteSQLiteHelper.TABLE_NOTES, PangNoteSQLiteHelper.COLUMN_ID + " = " + id, null);
	}
	
	public List<Note> getNotesByType(int type) {
		List<Note> notes = new ArrayList<Note>();
		
		Cursor cursor = db.query(PangNoteSQLiteHelper.TABLE_NOTES,
				allColumns, PangNoteSQLiteHelper.COLUMN_TYPE + " = " + type, null,null, null, null);
		
		cursor.moveToLast();
		while (!cursor.isBeforeFirst()) {
			Note note = cursorToNote(cursor);
			notes.add(note);
			cursor.moveToPrevious();
		}
		cursor.close();
		
		return notes;
	}
	
	private Note cursorToNote(Cursor cursor) {
		Note note = new Note();
		note.setId(cursor.getLong(0));
		note.setContent(cursor.getString(1));
		note.setType(cursor.getInt(2));
		note.setTime(cursor.getString(3));
		
		return note;
	}

}
