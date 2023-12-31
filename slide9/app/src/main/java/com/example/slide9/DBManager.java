package com.example.slide9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private FeedReaderDbHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new FeedReaderDbHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String title, String subtitle) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        contentValue.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
        database.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { FeedReaderContract.FeedEntry._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE };
        Cursor cursor = database.query(FeedReaderContract.FeedEntry.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String title, String subtitle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
        int i = database.update(FeedReaderContract.FeedEntry.TABLE_NAME, contentValues, FeedReaderContract.FeedEntry._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(FeedReaderContract.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry._ID + "=" + _id, null);
    }

    public void clear() {
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "title1" };
        database.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }

}
