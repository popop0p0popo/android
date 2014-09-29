package yi.cui.shenmidepangzi.pangnote.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PangNoteSQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_NOTES = "notes";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CONTENT = "content";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_TIME = "time";
	
	private static final String DATABASE_NAME = "pangnote.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_NOTES + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_CONTENT
			+ " text not null, " + COLUMN_TYPE
			+ " integer, " + COLUMN_TIME
			+ " text not null);";
	
	public PangNoteSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
		onCreate(db);
	}
}
