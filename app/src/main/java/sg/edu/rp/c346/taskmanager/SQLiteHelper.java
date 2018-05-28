package sg.edu.rp.c346.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "task";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CHECKED = "checked";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTaskTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_CHECKED + " BOOLEAN) ";
        db.execSQL(createTaskTableSql);
    }
    public ArrayList<String> getAllNotes() {
        ArrayList<String> notes = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + ","
                + COLUMN_DESCRIPTION
                + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String content = cursor.getString(1);
                notes.add("ID:" + id + ", " + content);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_TASK + " ADD COLUMN module_name TEXT ");

    }
    public long insertSong(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task.getTitle());
        values.put(COLUMN_DESCRIPTION, task.getDescriptions());
        values.put(COLUMN_CHECKED,task.getCompleted());
        long result = db.insert(TABLE_TASK, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }
        db.close();
        Log.d("SQL Insert ",""+ result); //id returned, shouldn’t be -1
        return result;
    }
    public ArrayList<Task> getAllSongs(String keyword) {
        ArrayList<Task> notes = new ArrayList<Task>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION,COLUMN_CHECKED};
        String condition = COLUMN_TITLE + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_TASK, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                boolean value = cursor.getInt(3) > 0;
                Task note = new Task(id,title, description,value);
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }
    public int updateNote(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task.getTitle());
        values.put(COLUMN_DESCRIPTION, task.getDescriptions());
        values.put(COLUMN_CHECKED, task.getCompleted());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(task.getId())};
        int result = db.update(TABLE_TASK, values, condition, args);
        db.close();
        return result;
    }
}
