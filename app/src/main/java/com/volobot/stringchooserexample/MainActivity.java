package com.volobot.stringchooserexample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.volobot.stringchooser.StringChooser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StringChooser stringChooser = findViewById(R.id.stringChooser);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            strings.add("option "+i);
        }
        stringChooser.setStrings(strings);
        stringChooser.setStringChooserCallback(new StringChooser.StringChooserCallback() {
            @Override
            public void onStringPickerValueChange(String s, int position) {
                Log.d(TAG, s);
            }
        });
    }
}
