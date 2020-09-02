package org.example.braintraining;

import android.util.Log;

import com.unity3d.player.UnityPlayer;


public class UnityData {
    public static String Username;
    public static String ClassroomID;
    public static String sceneName;

    public static void SendDataIntoUnity() {
        Log.d("Unity", "SendDataIntoUnity");
        UnityPlayer.UnitySendMessage("AndroidReceiver", "ReceiveNativeData", Username + ";" + ClassroomID + ";" + sceneName);
    }
}
