package com.aura.android.libluzirec;

import org.json.JSONArray;

public class SpeakerRecognitionJNI
{
    static
    {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();
    public native float scoreUser(String userID);
}
