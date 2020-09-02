using UnityEngine;
using System.Collections;
using UnityEngine.UI;
public class DataLoader : MonoBehaviour {

	public string[] items;

	IEnumerator Start(){
		WWWForm form = new WWWForm ();
		form.AddField ("classroomid", AndroidReceiver.Inst.ClassroomID);
			WWW itemsData = new WWW("http://w1480440fyp.co.uk/getuserandpoints.php", form);
		
		yield return itemsData;
		string itemsDataString = itemsData.text;
		print (itemsDataString);
		items = itemsDataString.Split(';');
		print(items.Length);
		
	
for (int i = 0;i <= items.Length;i = i + 1)
	GetComponent<TextMesh>().text += GetDataValue(items[i], "User:") + " " + GetDataValue(items[i], "Points:") + "\r\n";
	
		
	}

	string GetDataValue(string data, string index){
		string value = data.Substring(data.IndexOf(index)+index.Length);
		if(value.Contains("|"))value = value.Remove(value.IndexOf("|"));
		return value;
	}


}


























//void Start(){
//	string data = "ID:1|Name:Health Potion|Type:consumables|Cost:50";
//	print(GetDataValue(data, "Cost:"));
//}
//
//void Update(){
//	
//}
//
//string GetDataValue(string data ,string index){
//	string value = data.Substring(data.IndexOf(index)+index.Length);
//	if(value.Contains("|"))value = value.Remove(value.IndexOf("|"));
//	return value;
//}
