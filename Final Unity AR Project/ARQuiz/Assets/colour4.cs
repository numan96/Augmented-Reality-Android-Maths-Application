using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class colour4 : MonoBehaviour {

	// Use this for initialization
	void Start () {
		GetComponent<Renderer>().material.color = new Color(1f, 0.6f, 0f);
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
