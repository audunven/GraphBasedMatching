package json;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser; 
public class TestJsonRead {

	public static void main(String[] args) throws IOException, ParseException {


		String json = "./files/KCodes_All_Validated.json";
		File jsonFilePath = new File(json);
		FileReader fileReader = new FileReader(jsonFilePath);

		JsonElement jsonElement = JsonParser.parseReader(fileReader);
		JsonObject result = jsonElement.getAsJsonObject();

		Set<Map.Entry<String, JsonElement>> entrySet = result.entrySet();
		JsonObject jo;
		for (Map.Entry<String, JsonElement> entry : entrySet) {

			System.out.println("L1: " + entry.getKey());
			jo = entry.getValue().getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entries = jo.entrySet();
			
			JsonObject job;
			
			for (Map.Entry<String, JsonElement> e : entries) {
				
				System.out.println("\tL2: " + e.getKey());
				job = e.getValue().getAsJsonObject();
				Set<Map.Entry<String, JsonElement>> childEntries = job.entrySet();
				
				JsonArray array;
				for (Map.Entry<String, JsonElement> ce : childEntries) {
					
					System.out.println("\t\tL3: " + ce.getKey());
					
					array = ce.getValue().getAsJsonArray();
					
					//System.out.println("Items within array: " + array.size());
					
					List<JsonElement> list = array.asList();
					
					JsonObject jsonObj;
					for (JsonElement element : list) {
						
						jsonObj = element.getAsJsonObject();
						
						Set<Map.Entry<String, JsonElement>> cEntries = jsonObj.entrySet();
						
						//System.out.println("\nProperties (L4):");
						
						for (Map.Entry<String, JsonElement> entr : cEntries) {
							
							
							if (entr.getValue().isJsonArray() == true) {
								
								//if lawsAndRegulations
								//System.out.println("This is an array: " + entr.getKey());
								//if kostraCodes
							}

							else {
							//else
							//System.out.println(entr.getKey() + ": " + entr.getValue());
							}
						}
						
					}
					
					
//					Iterator <JsonElement> itr = array.iterator();
//					while (itr.hasNext()) {
//						JsonObject obj = itr.next().getAsJsonObject();
//						String id = obj.getAsString();
//						
//					}
					
				}
				
			}
			//System.out.println("Value: " + entry.getValue());

		}

	}

}
