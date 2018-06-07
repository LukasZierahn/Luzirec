package com.aura.android.libluzirec;

import com.aura.android.libluzirec.SpeakerRecognitionJNI;

import org.json.JSONArray;
import org.json.JSONObject;

public class Recognize
{
    private JSONArray users = new JSONArray();
    private SpeakerRecognitionJNI speakRecJNI;

    public Recognize()
    {
        speakRecJNI = new SpeakerRecognitionJNI();
    }

    public void addUserProfile(JSONObject profile)
    {
        users.put(profile);
    }
    public int GetUserCount() { return users.length(); }

    public JSONArray recognizeUser(byte[] pcm_s16le_mono)
    {
        JSONArray results = new JSONArray();

        try
        {
            JSONObject result1 = new JSONObject();

            for(int i = 0; i < users.length(); i++)
            {
                users.getJSONObject(i).put("score", speakRecJNI.scoreUser(users.getJSONObject(i).getString("userID")));
                results.put(users.getJSONObject(i));
            }
        }
        catch (Exception ignore)
        {
        }

        return results;
    }
}
