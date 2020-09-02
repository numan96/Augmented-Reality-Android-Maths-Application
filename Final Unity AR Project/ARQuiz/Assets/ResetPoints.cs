using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;


public class ResetPoints : MonoBehaviour, IVirtualButtonEventHandler
{
private GameObject backMenu;




	void Start(){
	
	
	
		backMenu = GameObject.Find ("BackMenu");

		backMenu.GetComponent<VirtualButtonBehaviour> ().RegisterEventHandler (this);

	}
	public void OnButtonPressed (VirtualButtonAbstractBehaviour vb) {

	
		PlayerPrefs.DeleteAll ();
		PlayerPrefs.Save ();




	}


	public void OnButtonReleased (VirtualButtonAbstractBehaviour vb) {
		



		Application.Quit ();

	}
	}


