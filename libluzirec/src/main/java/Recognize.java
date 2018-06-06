import com.aura.android.libluzirec.SpeakerRecognitionJNI;

import org.json.JSONArray;
import org.json.JSONObject;

public class Recognize
{
    JSONArray users = new JSONArray();
    SpeakerRecognitionJNI speakRecJNI;

    public Recognize()
    {
        speakRecJNI = new SpeakerRecognitionJNI();
    }

    public void addUserProfile(JSONObject profile)
    {
        users.put(profile);
    }

    public static JSONArray recognizeUser(byte[] pcm_s16le_mono)
    {
        JSONArray results = new JSONArray();

        try
        {
            JSONObject result1 = new JSONObject();

            result1.put("userid", "lukas");
            result1.put("score", "0.98");

            results.put(result1);

            JSONObject result2 = new JSONObject();

            result2.put("userid", "dezi");
            result2.put("score", "0.22");

            results.put(result2);

        }
        catch (Exception ignore)
        {
        }

        return results;
    }

}
