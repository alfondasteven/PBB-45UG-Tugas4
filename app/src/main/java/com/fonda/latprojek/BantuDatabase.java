package com.fonda.latprojek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BantuDatabase extends SQLiteOpenHelper {

    private static final String DB_ANIME = "DB_ANIME";
    private static final String TABEL_ANIME = "tabel_anime";
    private static final String KODE = "kode";
    private static final String NAMA_ANIME = "nama_anime";
    private static final String GENRE = "genre";
    private static final String EPISODE = "episode";
    private static final String RELASE_DATE = "relase_date";

    public BantuDatabase(@Nullable Context context) {
        super(context, DB_ANIME, null, 1);
    }

    @Override


    public void onCreate(SQLiteDatabase db) {
        String nama_tabel = "create table TABEL_ANIME(KODE integer primary key, NAMA_ANIME text null, GENRE text null, EPISODE text null, RELASE_DATE text null);";
        Log.d("DATA","onCREATE: "+nama_tabel);
        db.execSQL(nama_tabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public boolean tambahData(String nama, String genre, String episode, String relase){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_ANIME ,nama);
        contentValues.put(GENRE ,genre);
        contentValues.put(EPISODE ,episode);
        contentValues.put(RELASE_DATE ,relase);

        long hasil = db.insert(TABEL_ANIME,null,contentValues);
        return hasil != -1;
    }

    public Cursor tampilAnime() {
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="select * from "+TABEL_ANIME;
        Cursor cursor = db.rawQuery(sql,null);
        return  cursor;
    }
}
