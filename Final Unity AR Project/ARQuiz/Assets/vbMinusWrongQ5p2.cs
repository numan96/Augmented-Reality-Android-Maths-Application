using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;


	public class vbMinusWrongQ5p2
		: MonoBehaviour, IVirtualButtonEventHandler {

		private GameObject vbinCorrect;
		// Use this for initialization
		void Start () {

			vbinCorrect = GameObject.Find ("WrongButton2");

			vbinCorrect.GetComponent<VirtualButtonBehaviour> ().RegisterEventHandler (this);


		}

		// Update is called once per frame
		public void OnButtonPressed (VirtualButtonAbstractBehaviour vb) {



			TextMesh textObject = GameObject.Find("incorrectText").GetComponent<TextMesh>();
			textObject.text = "Incorrect";



		}

		public void OnButtonReleased (VirtualButtonAbstractBehaviour vb) {

			Application.LoadLevel("MinusQ6");




		}
	}
