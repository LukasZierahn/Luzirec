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

import com.aura.android.libluzirec.Recognize;
import com.aura.android.libluzirec.SpeakerRecognitionJNI;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.media.MediaRecorder.AudioSource.MIC;

public class MainActivity extends AppCompatActivity {

    private static final int sampleRate = 8000;
    private static final int bufferSize = 1024;

    private TextView txtview;
    private TextView resultTxtView;
    private AudioRecord audioRecorder;
    private Button recordbttn;
    private boolean isRecording = false;
    private Thread recordingThread;

    private Recognize recognize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialising variables
        txtview = findViewById(R.id.textView);
        resultTxtView = findViewById(R.id.resultTxtView);
        recognize = new Recognize();
        JSONObject Lukas = new JSONObject();
        JSONObject Maxi = new JSONObject();
        try
        {
            Lukas.put("userID", "Lukas");
            Maxi.put("userID", "Maxi");
        }
        catch (Exception ignore)
        {
        }
        recognize.addUserProfile(Lukas);
        recognize.addUserProfile(Maxi);

        //creating a button and making it record when pressed
        recordbttn = findViewById(R.id.recordButton);
        recordbttn.setOnClickListener(btnClick);

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
        JSONArray results = recognize.recognizeUser(null);
        String resultString = "";
        for (int i = 0; i < recognize.GetUserCount(); i++)
        {
            try
            {
                resultString += results.getJSONObject(i).get("userID") + " " + results.getJSONObject(i).get("score") + "\n";
            }
            catch (Exception ignore)
            {
            }
        }

        resultTxtView.setText(resultString);


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
