package com.aura.android.luzirec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aura.android.libluzirec.JNIExample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtview = findViewById(R.id.textView);

        JNIExample example = new JNIExample();

        txtview.setText(example.stringFromJNI());
    }
}
