package com.example.exp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class GameActivity extends Activity {
    static boolean isPlay; //Music playing status
    MediaPlayer mediaPlayer; //Music player object
    Button music_btn; //Music playing button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        music_btn = (Button) findViewById(R.id.btn_music);

//        music_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onMusic(view);
//            }
//        });

        playMusic();
        isPlay = true;


    }

    //跳转到“关于”界面
    public void onAbout(View view) {
        startActivity(new Intent(GameActivity.this, AboutActivity.class));
    }

    //跳转到游戏界面
    public void onPlay(View view) {
        startActivity(new Intent(GameActivity.this, SelectActivity.class));
    }

    public void playMusic() {
        Toast.makeText(this, "播放", Toast.LENGTH_SHORT).show();

        mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.main_music);

        mediaPlayer.start();
        isPlay = true;
    }


    //切换音频
    //如果正在播放，就停止播放
    //如果没有播放，就播放
    public void onMusic(View view) {

        if (isPlay) {
            Toast.makeText(this, "暂停", Toast.LENGTH_SHORT).show();
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                music_btn.setBackgroundResource(R.mipmap.btn_music2);
                isPlay = false;
            }
        } else {
            Toast.makeText(this, "播放", Toast.LENGTH_SHORT).show();
            playMusic();
            music_btn.setBackgroundResource(R.mipmap.btn_music1);
            isPlay = true;
        }
    }

    /*音乐播放功能
        • 功能1：
        • ⽤按钮停⽌播放⾳乐，或者重新播放。
        • onMusic
        • 功能2：启动后⾃动播放⾳乐
        • 功能3：跳转到其它界⾯时，⾳乐停⽌播放
        • 功能4：返回当前界⾯时，⾳乐继续播放
         */
    @Override
    protected void onStop() {
        super.onStop();
        if (isPlay && mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (isPlay == true) {
            playMusic();
        }
    }
}
