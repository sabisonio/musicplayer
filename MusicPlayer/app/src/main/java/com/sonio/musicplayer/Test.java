package com.sonio.musicplayer;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 812 on 2017/4/17.
 */

public class Test extends Activity{

        public static List<String> getAudioNames(Context context) {
            List<String> list = new ArrayList<String>();
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    new String[] { MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.TITLE,
                            MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
                            MediaStore.Audio.Media.YEAR, MediaStore.Audio.Media.MIME_TYPE, MediaStore.Audio.Media.SIZE,
                            MediaStore.Audio.Media.DATA }, null, new String[] {}, null);
            while (cursor.moveToNext()) {
                String fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                list.add(fileName);
            }
            return list;
        }

      protected void onCreate(Bundle savedInstanceState){
          super.onCreate(savedInstanceState);
          setContentView(R.layout.test);
          Context context = this.getBaseContext();
          Button button = (Button) findViewById(R.id.button);
          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              }
          });
          fetchAllContacts();


      }
    public void fetchAllContacts() {
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);
        cursor.getCount();
        while(cursor.moveToNext()) {
            System.out.println(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
            System.out.println(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
            System.out.println(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
            System.out.println(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            System.out.print(cursor.getPosition());
        }
        cursor.close();
    }
}
