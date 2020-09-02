using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class hideIncorrect : MonoBehaviour {

	// Use this for initialization
	void Start () {
		TextMesh textObject = GameObject.Find("incorrectText").GetComponent<TextMesh>();
		textObject.gameObject.SetActive(false);
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
