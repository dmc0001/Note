package com.example.note;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseController extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes_db";

    public DataBaseController(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }//End Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Note.CREATE_TABLE);

    }//End onCreate

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Note.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }//End onUpgrade

    //add new note
    public long insertNote(String note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NAME, note);
        long id = sqLiteDatabase.insert(Note.TABLE_NAME, null, values);
        sqLiteDatabase.close();
        return id;
    }//End insertNote

    //getNote
    public Note getNote(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_NAME, Note.COLUMN_TIMESTAMP},
                Note.COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        @SuppressLint("Range") Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP))
        );
        cursor.close();
        return note;
    }//End getNote


    //getAllNotes

    @SuppressLint("Range")
    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        String selectQuery = " SELECT * FROM " + Note.TABLE_NAME + " ORDER BY " + Note.COLUMN_TIMESTAMP + " DESC ";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NAME)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));
                notes.add(note);

            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return notes;
    }//End getNotes

    //Update note
    public int updateNote(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NAME, note.getNote());
//update for database
        return (sqLiteDatabase.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " =? ", new String[]{String.valueOf(note.getNote())}));
    }//End updateNote

    //delete note
    public void deleteNote(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Note.TABLE_NAME, Note.COLUMN_ID + " =? ", new String[]{String.valueOf(note.getId())});
        sqLiteDatabase.close();
    }


}//End Class
