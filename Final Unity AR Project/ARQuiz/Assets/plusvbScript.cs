using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;
public class plusvbScript : MonoBehaviour, IVirtualButtonEventHandler {


	private GameObject vbPlus;
	// Use this for initialization
	void Start () {

		vbPlus = GameObject.Find ("PlusButton");

		vbPlus.GetComponent<VirtualButtonBehaviour> ().RegisterEventHandler (this);


	}

	public void OnButtonPressed (VirtualButtonAbstractBehaviour vb) {


		Application.LoadLevel ("plusdifficulty");



	}

	public void OnButtonReleased (VirtualButtonAbstractBehaviour vb) {
	
	
	
	
	
	
	}
	
	// Update is called once per frame

}
