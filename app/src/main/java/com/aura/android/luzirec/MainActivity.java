package com.aura.android.luzirec;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aura.android.libluzirec.SpeakerRecognitionJNI;

import static android.media.MediaRecorder.AudioSource.MIC;

public class MainActivity extends AppCompatActivity {

    private static final int sampleRate = 8000;
    private static final int bufferSize = 1024;

    private TextView txtview;
    private AudioRecord audioRecorder;
    private Button recordbttn;
    private boolean isRecording = false;
    private Thread recordingThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialising variables
        txtview = findViewById(R.id.textView);

        //creating a button and making it record when pressed
        recordbttn = findViewById(R.id.recordButton);
        recordbttn.setOnClickListener(btnClick);

        //c++ stuff, not doing anything right now
        SpeakerRecognitionJNI example = new SpeakerRecognitionJNI();
        txtview.setText(example.stringFromJNI());

        //asking the user to use the Microphone
        int hasMicrophonePermission = PackageManager.PERMISSION_GRANTED;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, hasMicrophonePermission);

        }

        if(hasMicrophonePermission == PackageManager.PERMISSION_DENIED)
        {
            txtview.setText("Microphone Access Denied");
        }
        else
        {
            txtview.setText("Microphone Access Granted");
        }
    }

    protected void StartRecording()
    {
        txtview.setText("Recording");
        audioRecorder = new AudioRecord(MIC, sampleRate, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, 2 * bufferSize);

        audioRecorder.startRecording();
    }

    protected void StopRecording()
    {
        txtview.setText("Microphone Access Granted");
        audioRecorder.stop();
        audioRecorder.release();
    }

    private View.OnClickListener btnClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.recordButton:
                    if(isRecording)
                    {
                        StopRecording();
                    }
                    else
                    {
                        StartRecording();
                    }
                    isRecording = !isRecording;
                    break;
            }

        }
    };
}
