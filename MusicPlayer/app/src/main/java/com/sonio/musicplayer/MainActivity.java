package com.sonio.musicplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String singer_name;
    private String song_name;

    final MediaPlayer mp= new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<HashMap<String,String>> musicList = new ArrayList<HashMap<String, String>>();


        final ListView listView = (ListView) findViewById(R.id.listView);




        if (!isExternalStorageWritable()){
            Toast.makeText(this,"bukeyong",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"keyong",Toast.LENGTH_LONG).show();
        }

        File[] files = new File("/storage/emulated/0/").listFiles();

        for (File file :files){
            if (file.getName().endsWith(".mp3")) {
//                System.out.println(file.getPath());
                HashMap<String,String> map= new HashMap<String,String>();
                String [] temp= null;
                String [] songTemp = null;
                if (file.getName().contains("(")){
                    temp=file.getName().split("\\(");
                }
                else
                {
                    temp=file.getName().split("-");
                }
                if (file.getName().contains("-")){
                songTemp =file.getName().split("-");
                if (file.getName().contains("[")){
                    songTemp =file.getName().split("\\[");
                    map.put("singer_name",songTemp[0].replace(".mp3",""));
                }
                    else
                {
                    map.put("singer_name",songTemp[1].replace(".mp3",""));
                }


                }
                else{
                    map.put("singer_name",file.getName().replace(".mp3",""));
                }
                map.put("song_name",temp[0]);
//                map.put()
                map.put("file_path",file.getPath());
                musicList.add(map);
            }
        }

        System.out.println(musicList.size());

        SimpleAdapter musicAdapter = new SimpleAdapter(this,musicList,R.layout.items_layout,new String[]{"song_name","singer_name"},new int[]{R.id.song_name,R.id.singer_name});
        listView.setAdapter(musicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView1 = (ListView) parent;
                HashMap<String,String> hashMap = (HashMap<String, String>) listView1.getItemAtPosition(position);
                String song_name = hashMap.get("song_name");
                String file_path = hashMap.get("file_path");
                int  currentPosition =hashMap.size();
//                String song_name=
//                Toast.makeText(getBaseContext(),song_name,Toast.LENGTH_SHORT).show();
                Log.i("tt","");
                if (mp.isPlaying())
                {
                    mp.reset();
                    mp.stop();
                try{
//
                mp.setDataSource(file_path);
                    mp.prepare();
                    mp.start();

                   }catch (IOException ex){
                    Log.d("error","jjj");
                    }
                }
                else {
                    try{
                        mp.setDataSource(file_path);
                        mp.prepare();
                        mp.start();

                    }catch (IOException ex){
                        Log.d("error","jjj");
                    }
                }
            }
        });
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("tag", "播放完毕");
//                Log.d("tt",musicList.get());

            }
        });
    }

    /***
     * 检查外部设备是否可用
     */
    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        else{
            return false;
        }
    }



}
