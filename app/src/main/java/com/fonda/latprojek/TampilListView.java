package com.fonda.latprojek;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TampilListView extends AppCompatActivity {

    BantuDatabase bd;
    ListView listView;
    EditText textTitle , textGenre , textEps , textRelase;
    Button tblTambah;

    ArrayAdapter adapter;
    ArrayList<String> listviewku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_list_view);

        listView = findViewById((R.id.listdataanime));
        textTitle = findViewById((R.id.editTitle));
        textGenre = findViewById((R.id.editGenre));
        textEps = findViewById((R.id.editEps));
        textRelase = findViewById((R.id.editRelase));
        tblTambah = findViewById(R.id.tblTambah);
        bd = new BantuDatabase(this);
        listviewku = new ArrayList<>();
        tampilkan_anime();

        tblTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd.tambahData(textTitle.getText().toString(),textGenre.getText().toString(),textEps.getText().toString(),textRelase.getText().toString());
                Toast.makeText(TampilListView.this, "DATA TERSIMPAN", Toast.LENGTH_SHORT).show();
                listviewku.clear();
                tampilkan_anime();
            }
        });
    }



    private void tampilkan_anime() {
        Cursor cursor = bd.tampilAnime();
        if(cursor.getCount()==0){
            Toast.makeText(this, "RECORD KOSONG !!!", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                listviewku.add(String.valueOf(cursor.getInt(0))+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4));
            }
            adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,listviewku);
            listView.setAdapter(adapter);
        }
    }
}