using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class score : MonoBehaviour {

	// Use this for initialization
	void Start () {
		string scorevalue = PlayerPrefs.GetInt("score").ToString();
		TextMesh textObject = GameObject.Find("score").GetComponent<TextMesh>();

		textObject.text = scorevalue;

		WWWForm form = new WWWForm ();
		form.AddField ("username", AndroidReceiver.Inst.Username);
		form.AddField ("points", scorevalue);
		WWW itemsData = new WWW("http://w1480440fyp.co.uk/addpoints.php", form);
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
