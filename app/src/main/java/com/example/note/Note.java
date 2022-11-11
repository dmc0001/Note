package com.example.note;

public class Note {
    public static final String TABLE_NAME = "noteS";
    public static String COLUMN_ID = "id";
    public static String COLUMN_NAME = "note";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    private int id;
    private String note;
    private String timestamp;

    //Create SQL: create table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";

    public Note() {

    }

    public Note(int id, String note, String timestamp) {
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}//End class
