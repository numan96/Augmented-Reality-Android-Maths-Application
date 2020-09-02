using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;

public class MinusvbScript : MonoBehaviour, IVirtualButtonEventHandler {

	// Use this for initialization
	private GameObject vbMinus;
	// Use this for initialization
	void Start () {

		vbMinus = GameObject.Find ("MinusButton");

		vbMinus.GetComponent<VirtualButtonBehaviour> ().RegisterEventHandler (this);


	}

	public void OnButtonPressed (VirtualButtonAbstractBehaviour vb) {


		Application.LoadLevel ("minusdifficulty");



	}

	public void OnButtonReleased (VirtualButtonAbstractBehaviour vb) {






	}
}
