using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class colour2 : MonoBehaviour {

	// Use this for initialization
	void Start () {
		GetComponent<Renderer>().material.color = new Color(255, 128, 0);
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
