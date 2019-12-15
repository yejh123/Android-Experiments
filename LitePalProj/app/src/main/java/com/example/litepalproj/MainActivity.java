package com.example.litepalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.litepalproj.entity.Album;
import com.example.litepalproj.entity.Song;

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_add;
    private Button btn_delete;
    private Button btn_update;
    private Button btn_query;

    private Button btn_toContentProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.initialize(this);
        SQLiteDatabase db = LitePal.getDatabase();
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);
        btn_query = findViewById(R.id.btn_query);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Album album = new Album();
                album.setName("album");
                album.setPrice(10.99f);
                //album.setCover(getCoverImageBytes());
                album.save();
                Song song1 = new Song();
                song1.setName("song1");
                song1.setDuration(320);
                song1.setAlbum(album);
                song1.save();
                Song song2 = new Song();
                song2.setName("song2");
                song2.setDuration(356);
                song2.setAlbum(album);
                song2.save();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.delete(Song.class, 1);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Album albumToUpdate = LitePal.find(Album.class, 1);
                albumToUpdate.setPrice(20.99f); // raise the price
                albumToUpdate.save();
            }
        });


        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Song> allSongs = LitePal.findAll(Song.class);
                List<Album> allAlbums = LitePal.findAll(Album.class);
                Log.d("btn_query", allSongs + "");
                Log.d("btn_query", allAlbums + "");

            }
        });

        btn_toContentProvider = findViewById(R.id.btn_toContentProvider);
        btn_toContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ContentProviderActivity.class));
            }
        });
    }
}
