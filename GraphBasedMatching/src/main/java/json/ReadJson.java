package json;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import objects.KCodesFunction;
import objects.KostraCode;
import objects.LegalReference; 

public class ReadJson {


	public static void main(String[] args) throws FileNotFoundException {

		String json = "./files/KCodes_All_Validated.json";

		List<KCodesFunction> functions = getAllKCodesFunctions(json);

		System.out.println("There are " + functions.size() + " functions:");

		for (KCodesFunction kcf : functions) {
			System.out.println("\n" + kcf.toString());
		}
	}

	public static List<KCodesFunction> getAllKCodesFunctions (String jsonPath) throws FileNotFoundException {

		File jsonFilePath = new File(jsonPath);
		FileReader fileReader = new FileReader(jsonFilePath);

		JsonElement jsonElement = JsonParser.parseReader(fileReader);
		JsonObject entireJson = jsonElement.getAsJsonObject();


		Set<Map.Entry<String, JsonElement>> allEntrySet = entireJson.entrySet();

		List<KCodesFunction> allFunctions = new ArrayList<KCodesFunction>();
		KCodesFunction kcf = null;

		String l1Function;	
		JsonObject levelOneObject;
		for (Map.Entry<String, JsonElement> levelOneEntry : allEntrySet) {

			l1Function = levelOneEntry.getKey();
			System.out.println("l1Function: " + l1Function);
			levelOneObject = levelOneEntry.getValue().getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> levelTwoEntries = levelOneObject.entrySet();

			String l2Function;
			JsonObject levelTwoObject;
			for (Map.Entry<String, JsonElement> levelTwoEntry : levelTwoEntries) {

				l2Function = levelTwoEntry.getKey();
				System.out.println("l2Function: " + l2Function);
				levelTwoObject = levelTwoEntry.getValue().getAsJsonObject();
				Set<Map.Entry<String, JsonElement>> levelThreeEntries = levelTwoObject.entrySet();

				String l3Function;
				JsonArray levelThreeArray;
				for (Map.Entry<String, JsonElement> levelThreeObject : levelThreeEntries) {

					l3Function = levelThreeObject.getKey();
					System.out.println("l3Function: " + l3Function);
					levelThreeArray = levelThreeObject.getValue().getAsJsonArray();

					List<JsonElement> levelFourList = levelThreeArray.asList();

					JsonObject levelFourObject;
					for (JsonElement levelFourElement : levelFourList) {

						levelFourObject = levelFourElement.getAsJsonObject();

						Set<Map.Entry<String, JsonElement>> levelFourEntries = levelFourObject.entrySet();

						LegalReference lr = null;
						KostraCode kc = null;

						String id = null;
						String title = null;
						String category = null;
						String kcode = null;
						String description = null;
						String functionId = null;
						
						boolean countyGovernance = true;
						boolean municipalityGovernance = true;
						boolean stateGovernance = true;

						List<LegalReference> legalReferences = new ArrayList<LegalReference>();
						List<KostraCode> kostraCodes = new ArrayList<KostraCode>();
						JsonArray lawAndRegulationsArray;
						JsonArray kostraCodeArray;
						JsonObject governanceObject;
						JsonObject countyObject;
						JsonObject municipalityObject;
						JsonObject stateObject;
						for (Map.Entry<String, JsonElement> properties : levelFourEntries) {

							if (properties.getValue().isJsonArray() == true) {

								if (properties.getKey().equals("lawsAndRegulations") && properties.getValue() != null) {
									lawAndRegulationsArray = properties.getValue().getAsJsonArray();

									List<JsonElement> lawAndRegulationList = lawAndRegulationsArray.asList();									

									String larId = null;
									String larTitle = null;
									String larShortTitle = null;
									String larParagraph = null;
									String larParagraphTitle = null;
									String larDocumentId = null;
									String larActivityId = null;

									JsonObject lawsAndRegulationsObject;
									for (JsonElement lawsAndRegulationElement : lawAndRegulationList) {

										lawsAndRegulationsObject = lawsAndRegulationElement.getAsJsonObject();
										Set<Map.Entry<String, JsonElement>> lawsAndRegulationEntries = lawsAndRegulationsObject.entrySet();

										for (Map.Entry<String, JsonElement> lawsAndRegulationProperties : lawsAndRegulationEntries) {

											if (lawsAndRegulationProperties.getKey().equals("id")) {
												larId = lawsAndRegulationProperties.getValue().toString();
											} else if (lawsAndRegulationProperties.getKey().equals("title")) {
												larTitle = lawsAndRegulationProperties.getValue().toString();
											} else if (lawsAndRegulationProperties.getKey().equals("shortTitle")) {
												larShortTitle = lawsAndRegulationProperties.getValue().toString();
											} else if (lawsAndRegulationProperties.getKey().equals("paragraph")) {
												larParagraph = lawsAndRegulationProperties.getValue().toString();
											} else if (lawsAndRegulationProperties.getKey().equals("paragraphTitle")) {
												larParagraphTitle = lawsAndRegulationProperties.getValue().toString();
											} else if (lawsAndRegulationProperties.getKey().equals("documentId")) {
												larDocumentId = lawsAndRegulationProperties.getValue().toString();
											} else {
												larActivityId = lawsAndRegulationProperties.getValue().toString();
											}

											lr = new LegalReference.LegalReferenceBuilder()
													.setId(larId)
													.setTitle(larTitle)
													.setShortTitle(larShortTitle)
													.setParagraph(larParagraph)
													.setParagraphTitle(larParagraphTitle)
													.setDocumentId(larDocumentId)
													.setActivityId(larActivityId)
													.build();

											
										}
										
										legalReferences.add(lr);
									}


								} else if (properties.getKey().equals("kostraCodes") && properties.getValue() != null) {
									kostraCodeArray = properties.getValue().getAsJsonArray();
									
									List<JsonElement> kostraCodeList = kostraCodeArray.asList();									

									String kcId = null;
									String municipalityArea = null;
									String municipalityAreaName = null;
									String municipalityCode = null;
									String municipalityCodeName = null;
									String countyArea = null;
									String countyAreaName = null;
									String countyCode = null;
									String countyCodeName = null;
									String kcActivityId = null;

									JsonObject kostraCodeObject;
									for (JsonElement kostraCodeElement : kostraCodeList) {

										kostraCodeObject = kostraCodeElement.getAsJsonObject();
										Set<Map.Entry<String, JsonElement>> kostraCodeEntries = kostraCodeObject.entrySet();

										for (Map.Entry<String, JsonElement> kostraCodeProperties : kostraCodeEntries) {

											if (kostraCodeProperties.getKey().equals("id")) {
												kcId = kostraCodeProperties.getValue().toString();
											} else if (kostraCodeProperties.getKey().equals("municipalityArea")) {
												municipalityArea = kostraCodeProperties.getValue().toString();
											} else if (kostraCodeProperties.getKey().equals("municipalityAreaName")) {
												municipalityAreaName = kostraCodeProperties.getValue().toString();
											} else if (kostraCodeProperties.getKey().equals("municipalityCode")) {
												municipalityCode = kostraCodeProperties.getValue().toString();
											} else if (kostraCodeProperties.getKey().equals("municipalityCodeName")) {
												municipalityCodeName = kostraCodeProperties.getValue().toString();
											} else if (kostraCodeProperties.getKey().equals("countyArea")) {
												countyArea = kostraCodeProperties.getValue().toString();
											} else if (kostraCodeProperties.getKey().equals("countyAreaName")) {
												countyAreaName = kostraCodeProperties.getValue().toString();
											} else if (kostraCodeProperties.getKey().equals("countyCode")) {
												countyCode = kostraCodeProperties.getValue().toString();
											} else if (kostraCodeProperties.getKey().equals("countyCodeName")) {
												countyCodeName = kostraCodeProperties.getValue().toString();
											} else {
												kcActivityId = kostraCodeProperties.getValue().toString();
											}
											
											kc = new KostraCode.KostraCodeBuilder()
													.setId(kcId)
													.setMunicipalityArea(municipalityArea)
													.setMunicipalityAreaName(municipalityAreaName)
													.setMunicipalityCode(municipalityCode)
													.setMunicipalityCodeName(municipalityCodeName)
													.setCountyArea(countyArea)
													.setCountyAreaName(countyAreaName)
													.setCountyCode(countyCode)
													.setCountyCodeName(countyCodeName)
													.setActivityId(kcActivityId)
													.build();
											
										}
										
										kostraCodes.add(kc);
									}
									
								}

							} else if (properties.getKey().equals("governance")) {
								
								governanceObject = properties.getValue().getAsJsonObject();
								Set<Map.Entry<String, JsonElement>> governanceEntries = governanceObject.entrySet();
								
//								boolean countyGovernance = true;
//								boolean municipalityGovernance = true;
//								boolean stateGovernance = true;
								
								for (Map.Entry<String, JsonElement> governanceProperties : governanceEntries) {
									
									if (governanceProperties.getKey().equals("county")) {
										countyGovernance = governanceProperties.getValue().getAsBoolean();
									} else if (governanceProperties.getKey().equals("municipality")) {
										municipalityGovernance = governanceProperties.getValue().getAsBoolean();
									} else {
										stateGovernance = governanceProperties.getValue().getAsBoolean();
									}												
								}
								
							}
							
							
							
							else {

								System.out.println(properties.getKey() + ": " + properties.getValue());
								if (properties.getKey().equals("id")) {
									id = properties.getValue().toString();
								} else if (properties.getKey().equals("title")) {
									title = properties.getValue().toString();
								} else if (properties.getKey().equals("category")) {
									category = properties.getValue().toString();
								} else if (properties.getKey().equals("kcode")) {
									kcode = properties.getValue().toString();
								} else if (properties.getKey().equals("description")) {
									description = properties.getValue().toString();
								} else {
									functionId = properties.getValue().toString();
								}

							}
						}

						kcf = new KCodesFunction.KCodesFunctionBuilder()
								.setId(id)
								.setTitle(title)
								.setCategory(category)
								.setCode(kcode)
								.setDescription(description)
								.setFunctionId(functionId)
								.setL1Title(l1Function)
								.setL2Title(l2Function)
								.setL3Title(l3Function)
								.setLegalReferences(legalReferences)
								.setKostraCodes(kostraCodes)
								.setCountyGovernance(countyGovernance)
								.setMunicipalityGovernance(municipalityGovernance)
								.setStateGovernance(stateGovernance)
								.build();

						allFunctions.add(kcf);

					}

				}
			}


		}

		return allFunctions;
	}

}
