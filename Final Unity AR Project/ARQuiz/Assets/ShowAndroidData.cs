using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ShowAndroidData : MonoBehaviour {

    [SerializeField]
    Text displayDataText;

    private void Awake()
    {
        displayDataText.text = AndroidReceiver.Inst.Username + ";" + AndroidReceiver.Inst.ClassroomID + ";" + AndroidReceiver.Inst.sceneName;
    } 
}
