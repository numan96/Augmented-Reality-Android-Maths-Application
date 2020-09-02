using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;

public class vbCorrect : MonoBehaviour, IVirtualButtonEventHandler{
	private GameObject vbRight;
	// Use this for initialization
	void Start () {

		vbRight = GameObject.Find ("CorrectButton");

		vbRight.GetComponent<VirtualButtonBehaviour> ().RegisterEventHandler (this);


	}
	
	// Update is called once per frame
	public void OnButtonPressed (VirtualButtonAbstractBehaviour vb) {



		TextMesh textObject = GameObject.Find("correctText").GetComponent<TextMesh>();
		textObject.text = "Correct";



	}

	public void OnButtonReleased (VirtualButtonAbstractBehaviour vb) {

		int scorevalue = PlayerPrefs.GetInt("score");
		scorevalue += 10;
		PlayerPrefs.SetInt ("score", scorevalue);
		Application.LoadLevel("TimesQ2");


	}
}
