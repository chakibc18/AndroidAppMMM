package com.example.youssef.mysqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by youssef on 15/11/17.
 */

public class SqlClasse extends SQLiteOpenHelper {

    private static final String TABLE_LIVRES = "table_livres";
    private static final String COL_ID = "ID";
    private static final String COL_ISBN = "ISBN";
    private static final String COL_TITRE = "Titre";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_LIVRES + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ISBN + " TEXT NOT NULL, "
            + COL_TITRE + " TEXT NOT NULL);";

    public SqlClasse(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_LIVRES + " ;");
        this.onCreate(db);
    }
}
