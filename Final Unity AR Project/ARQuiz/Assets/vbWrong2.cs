using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;
public class vbWrong2 : MonoBehaviour, IVirtualButtonEventHandler {
	private GameObject vbinCorrect2;
	// Use this for initialization
	void Start () {
		vbinCorrect2 = GameObject.Find ("WrongButton2");

		vbinCorrect2.GetComponent<VirtualButtonBehaviour> ().RegisterEventHandler (this);

	}
	
	public void OnButtonPressed (VirtualButtonAbstractBehaviour vb) {



		TextMesh textObject = GameObject.Find("incorrectText").GetComponent<TextMesh>();
		textObject.text = "Incorrect";



	}

	public void OnButtonReleased (VirtualButtonAbstractBehaviour vb) {

		Application.LoadLevel("TimesQ2");



	}
}
