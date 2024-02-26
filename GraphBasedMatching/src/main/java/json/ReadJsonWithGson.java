package json;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReadJsonWithGson {
	
	public static void main(String[] args ) {
		
		String json = "./files/KCodes_All_Validated.json";
		
		Gson gson = new Gson();
		JsonObject body = gson.fromJson(json, JsonObject.class);
		JsonArray array = body.getAsJsonArray();
		
		JsonObject object = null;
		for (int i = 0; i < array.size(); i++) {
			
			object = array.get(i).getAsJsonObject();
			System.out.println(object.toString());
			
		}
	}

}
