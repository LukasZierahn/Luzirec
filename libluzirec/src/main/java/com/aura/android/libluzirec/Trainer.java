package com.aura.android.libluzirec;

import org.json.JSONObject;

public class Trainer
{

    static
    {
        try
        {
            JSONObject xxx = new JSONObject("{ userid: \"lukas\" }");
            byte[] samples = new byte[ 8000 * 2 ];

            trainUser("lukas", xxx, samples);
        }
        catch (Exception ignore)
        {
        }
    }

    public static JSONObject loadUserFromDisk(String userid)
    {
        return null;
    }

    public static boolean saveUserToDisk(String userid, JSONObject profile)
    {
        return false;
    }

    public static boolean trainUser(String userid, JSONObject profile, byte[] pcm_s16le_mono)
    {
        int len = pcm_s16le_mono.length;

        /*
            char *pcm_s16le_mono;  // item len = 1 byte
            unsigned short *pcm_samples; // item len = 2 byte

            pcm_samples = (unsigned short *) pcm_s16le_mono;

            short sample = pcm_samples[ 0 ];
            short sample = pcm_s16le_mono[ 0 ] << 8 + pcm_s16le_mono[ 1 ];
        */
        return false;
    }
}
