package json;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 

public class ReadJson {
	
public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
	
	String json = "./files/KCodes_All_Validated.json";
	JSONParser parser = new JSONParser();
	
	JSONObject result = (JSONObject) parser.parse(new FileReader(json));

	JSONObject støttefunksjoner = (JSONObject) result.get("Støttefunksjoner");
	Collection<JSONObject> støttefunksjonerSet = støttefunksjoner.values();
	
	JSONObject genericJsonObject = null;
	JSONArray genericJSONArray = null;
	for (JSONObject s : støttefunksjonerSet) {
		
		System.out.println(s);
		
//		genericJsonObject = s;
//		genericJSONArray = (JSONArray) genericJsonObject.get(genericJsonObject.toJSONString());
		
	}
	
	JSONObject juridiskStøtteObject = (JSONObject) støttefunksjoner.get("Juridisk støtte");
	
	JSONArray juridiskStøtteArray = (JSONArray) juridiskStøtteObject.get("Juridisk støtte");

	Iterator it = juridiskStøtteArray.iterator();
	
	String id = null;
	String title = null;
//	while (it.hasNext()) {
//		JSONObject funksjon = (JSONObject) it.next();   
//		id = (String)funksjon.get("id");
//		System.out.println(id);
//		title = (String)funksjon.get("title");
//		System.out.println(title);
//		
//	}
}
}
