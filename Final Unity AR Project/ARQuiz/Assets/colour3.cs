﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class colour3 : MonoBehaviour {

	// Use this for initialization
	void Start () {



		GetComponent<Renderer>().material.color = new Color(0,1,0,1);
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
