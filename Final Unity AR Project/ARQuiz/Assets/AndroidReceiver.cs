using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class AndroidReceiver : MonoBehaviour {

    private static AndroidReceiver instance;

	protected string username;
	protected string classroomid;
	protected string scenename;

    [SerializeField]
    string packageName;

    public string Username { get { return username; } }
    public string ClassroomID { get { return classroomid; } }
	public string sceneName { get { return scenename; } }
    public static AndroidReceiver Inst
    {
        get
        {
            return instance;
        }
    }

#if UNITY_EDITOR && !TEST
#elif UNITY_ANDROID
        AndroidJavaObject nativeObj = null;
#endif

    private void Awake()
    {
        instance = this;
        this.name = "AndroidReceiver";
        // this object will stay on scene even when scene change
        DontDestroyOnLoad(gameObject);

#if UNITY_EDITOR && !TEST
#elif UNITY_ANDROID
                if (nativeObj == null)
			        nativeObj = new AndroidJavaObject(packageName + ".UnityData");
#elif UNITY_IOS && !UNITY_WEBGL
		
#endif
        GetData();
    }

    private void Start()
    {

    }

    public void GetData ()
    {
#if UNITY_EDITOR && !TEST
#elif UNITY_ANDROID
                if (nativeObj != null)
                    nativeObj.CallStatic("SendDataIntoUnity", new object[0] { });
#elif UNITY_IOS && !UNITY_WEBGL
		
#endif
    }

    // data is spited by ";" like: login;passowrd;scenename 
    public void ReceiveNativeData(string heatStr)
    {
        string[] strs = heatStr.Split(';');
        try
        {
            if (strs.Length == 3)
            {
		username = strs[0];
		classroomid = strs[1];
		scenename = strs[2];

		if (scenename != null)
                {
		Application.LoadLevel (scenename);  
                }
            }
        }
        catch (System.Exception e)
        {
            Debug.LogError("AndroidReceiver::ReceiveNativeData ERROR: " + e.ToString());
        }
    }
}
