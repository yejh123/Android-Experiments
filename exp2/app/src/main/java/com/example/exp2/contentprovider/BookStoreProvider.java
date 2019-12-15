package com.example.exp2.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exp2.dbsqlite.MySQLiteOpenHelper;

public class BookStoreProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.exp2.provider";
    public static final String URI_HEADER = "content://";
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final String BOOK = "book";
    private static final String TAG = "BookStoreProvider";

    private MySQLiteOpenHelper dbHelper;

    private static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);

    }
    @Override
    public boolean onCreate() {
        dbHelper = new MySQLiteOpenHelper(getContext(), "library", MySQLiteOpenHelper.Version);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        Log.d(TAG, "query: pathSegments: " + uri.getPathSegments() + ", path: " + uri.getPath());

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = database.query(BOOK, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                cursor = database.query(BOOK, projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd."  + AUTHORITY + "." + BOOK;
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd."  + AUTHORITY + "." + BOOK;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        Log.d(TAG, "insert: pathSegments: " + uri.getPathSegments() + ", path: " + uri.getPath());
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert(BOOK, null, contentValues);
                uriReturn = Uri.parse(URI_HEADER + AUTHORITY + "/" + BOOK + "/" + newBookId);
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        Log.d(TAG, "delete: pathSegments: " + uri.getPathSegments() + ", path: " + uri.getPath());
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deletedRows = db.delete(BOOK, s, strings);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete(BOOK, "id=?", new String[]{bookId});
                break;
        }
        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        Log.d(TAG, "update: pathSegments: " + uri.getPathSegments() + ", path: " + uri.getPath());
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updatedRows = db.update(BOOK, contentValues, s, strings);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update(BOOK, contentValues, "id=?", new String[]{bookId});
                break;
        }
        return updatedRows;
    }
}
