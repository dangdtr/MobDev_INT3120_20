package com.example.slide10;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ContactsProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.contactsprovider";
    private static final String BASE_PATH = "contacts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final int CONTACTS = 1;
    private static final int CONTACT_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, CONTACTS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", CONTACT_ID);
    }

    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        ContactsDbHelper dbHelper = new ContactsDbHelper(getContext());
        database = dbHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                cursor = database.query(ContactsDbHelper.TABLE_CONTACTS, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CONTACT_ID:
                String id = uri.getLastPathSegment();
                String[] selectionArguments = {id};
                cursor = database.query(ContactsDbHelper.TABLE_CONTACTS, projection, ContactsDbHelper.COLUMN_ID + "=?", selectionArguments, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        // Notify any observers of the change in the data
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                return "vnd.android.cursor.dir/" + BASE_PATH;
            case CONTACT_ID:
                return "vnd.android.cursor.item/" + BASE_PATH;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = database.insert(ContactsDbHelper.TABLE_CONTACTS, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdated;
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                rowsUpdated = database.update(ContactsDbHelper.TABLE_CONTACTS, values, selection, selectionArgs);
                break;
            case CONTACT_ID:
                String id = uri.getLastPathSegment();
                String[] selectionArguments = {id};
                rowsUpdated = database.update(ContactsDbHelper.TABLE_CONTACTS, values, ContactsDbHelper.COLUMN_ID + "=?", selectionArguments);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if (rowsUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted;
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                rowsDeleted = database.delete(ContactsDbHelper.TABLE_CONTACTS, selection, selectionArgs);
                break;
            case CONTACT_ID:
                String id = uri.getLastPathSegment();
                String[] selectionArguments = {id};
                rowsDeleted = database.delete(ContactsDbHelper.TABLE_CONTACTS, ContactsDbHelper.COLUMN_ID + "=?", selectionArguments);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        if (rowsDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }
}
