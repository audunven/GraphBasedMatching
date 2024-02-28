package ontology;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import json.KCodesJsonParser;
import objects.KCodesFunction;
import utilities.KGUtilities;
import utilities.StringUtilities;

import objects.KostraCode;
import objects.LegalReference;

public class KCodesOntology {

	//proper namespaces for concepts and datatypes
	final static String DATATYPE_INT_NS = "^^<http://www.w3.org/2001/XMLSchema#int";
	final static String DATATYPE_DATETIME_NS = "^^<http://www.w3.org/2001/XMLSchema#dateTime";
	final static String DATATYPE_STRING_NS = "<http://www.w3.org/2001/XMLSchema#string";
	final static String DATATYPE_DECIMAL_NS = "^^<http://www.w3.org/2001/XMLSchema#decimal";
	final static String DATATYPE_BOOLEAN_NS = "<http://www.w3.org/2001/XMLSchema#boolean";
	final static String DATAPROPERTY_NS = "<http://www.w3.org/2002/07/owl#DatatypeProperty";
	final static String OBJECTPROPERTY_NS = "<http://www.w3.org/2002/07/owl#ObjectProperty";
	final static String ONTOLOGY_NS = "<http://www.w3.org/2002/07/owl#Ontology";
	final static String RDF_TYPE_NS = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>";
	final static String DOMAIN_NS = "<http://www.w3.org/2000/01/rdf-schema#domain>";
	final static String RANGE_NS = "<http://www.w3.org/2000/01/rdf-schema#range>";
	final static String INVERSE_NS = "<http://www.w3.org/2002/07/owl#inverseOf>";


	public static void main(String[] args) throws Exception {

		//parse K-Koder json
		String json = "./files/KCodes_All_Validated.json";
		List<KCodesFunction> functions = KCodesJsonParser.getAllKCodesFunctions(json);
		System.out.println("There are " + functions.size() + " functions.");

		String ntFile = "./files/KCodesOntology.nt";

		transformKCodesJsonToNTripleGraph (functions, ntFile);
		
	}

		public static void transformKCodesJsonToNTripleGraph (List<KCodesFunction> functions, String ntFile) throws Exception {

			String baseURI = "<https://w3id.org/trondheim_kommune/ontology/kcodesonto#";
			String tripleClosure = "> .\n";

			//concepts and their names
			String functionType = "Function";
//			String coreFunctionType = "CoreFunction";
//			String managementFunctionType = "ManagementFunction";
//			String supportFunctionType = "SupportFunction";
			String legalReferenceType = "LegalReference";
			String kostraCodeType = "KostraCode";

			String functionEntity;
			String l1FunctionEntity;
			String l2FunctionEntity;
			String l3FunctionEntity;
//			String coreFunctionEntity;
//			String managementFunctionEntity;
//			String supportFunctionEntity;
			String legalReferenceEntity;
			String kostraCodeEntity;
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(ntFile, true));

			//declare the ontology
			bw.write(KGUtilities.declareOntology(baseURI, RDF_TYPE_NS, ONTOLOGY_NS, tripleClosure));
			
			//declare the relevant object properties
			Set<String> objectProperties = new HashSet<String>();
			objectProperties.add("hasKostraCode");
			objectProperties.add("hasLegalReference");
			objectProperties.add("hasParentFunction");
			objectProperties.add("isKostraCodeOf");
			objectProperties.add("isLegalReferenceOf");
			objectProperties.add("isParentFunctionOf");
			
			//inverse statements
			bw.write(KGUtilities.createInverseStatements("hasKostraCode", baseURI, INVERSE_NS, "isKostraCodeOf", tripleClosure));
			bw.write(KGUtilities.createInverseStatements("hasLegalReference", baseURI, INVERSE_NS, "isLegalReferenceOf", tripleClosure));
			bw.write(KGUtilities.createInverseStatements("hasParentFunction", baseURI, INVERSE_NS, "isParentFunctionOf", tripleClosure));

			for (String op : objectProperties) {

				bw.write(KGUtilities.declareObjectProperty(baseURI, op, RDF_TYPE_NS, OBJECTPROPERTY_NS, tripleClosure));

			}
			
			//declare the relevant data properties
			Set<String> dataProperties = new HashSet<String>();
			dataProperties.add("category");
			dataProperties.add("countyGovernance");
			dataProperties.add("description");
			dataProperties.add("functionId");
			dataProperties.add("id");
			dataProperties.add("kCode");
			dataProperties.add("municipalityGovernance");
			dataProperties.add("stateGovernance");
			dataProperties.add("title");
			
			dataProperties.add("countyArea");
			dataProperties.add("countyAreaName");
			dataProperties.add("countyCode");
			dataProperties.add("countyCodeName");
			dataProperties.add("kostraCodeActivityId");
			dataProperties.add("kostraCodeId");
			dataProperties.add("municipalityArea");
			dataProperties.add("municipalityAreaName");
			dataProperties.add("municipalityAreaCodeName");
			dataProperties.add("municipalityAreaCode");
			
			dataProperties.add("activityId");
			dataProperties.add("documentId");
			dataProperties.add("legalReferenceId");
			dataProperties.add("legalReferenceTitle");
			dataProperties.add("paragraph");
			dataProperties.add("paragraphTitle");
			dataProperties.add("shortTitle");

			for (String dp : dataProperties) {

				bw.write(KGUtilities.declareDataProperty(baseURI, dp, RDF_TYPE_NS, DATAPROPERTY_NS, tripleClosure));
			}
			
			//add entities and connect them to types
			List<KostraCode> kostraCodes = new ArrayList<KostraCode>();
			List<LegalReference> legalReferences = new ArrayList<LegalReference>();
			for (KCodesFunction kcf : functions) {
				
				kostraCodes = kcf.getKostraCodes();
				legalReferences = kcf.getLegalReferences();
				
				functionEntity = StringUtilities.replaceQuotesAndCommas(kcf.getTitle()) + ">";
				l1FunctionEntity = StringUtilities.replaceQuotesAndCommas(kcf.getL1Title()) + ">";
				l2FunctionEntity = StringUtilities.replaceQuotesAndCommas(kcf.getL2Title()) + ">";
				l3FunctionEntity = StringUtilities.replaceQuotesAndCommas(kcf.getL3Title()) + ">";
			
								
//				if (kcf.getL3Title().equals("Bibliotek")) {
//				System.out.println("functionEntity: " + functionEntity);
//				System.out.println("l1FunctionEntity: " + l1FunctionEntity);
//				System.out.println("l2FunctionEntity: " + l2FunctionEntity);
//				System.out.println("l3FunctionEntity: " + l3FunctionEntity);
//				}
				
				bw.write(KGUtilities.createType(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, RDF_TYPE_NS, functionType.replaceAll("[\\s()?+]", "_"), tripleClosure));
				bw.write(KGUtilities.createType(l1FunctionEntity.replaceAll("[\\s()?+]", "_"), baseURI, RDF_TYPE_NS, functionType.replaceAll("[\\s()?+]", "_"), tripleClosure));
				bw.write(KGUtilities.createType(l2FunctionEntity.replaceAll("[\\s()?+]", "_"), baseURI, RDF_TYPE_NS, functionType.replaceAll("[\\s()?+]", "_"), tripleClosure));
				bw.write(KGUtilities.createType(l3FunctionEntity.replaceAll("[\\s()?+]", "_"), baseURI, RDF_TYPE_NS, functionType.replaceAll("[\\s()?+]", "_"), tripleClosure));
				
				if (kostraCodes != null || !kostraCodes.isEmpty()) {
					kostraCodeEntity = null;
					for (KostraCode kc : kostraCodes) {
						kostraCodeEntity = StringUtilities.replaceQuotesAndCommas(kc.getId()) + ">";
						bw.write(KGUtilities.createType(kostraCodeEntity, baseURI, RDF_TYPE_NS, kostraCodeType.replaceAll("[\\s()?+]", "_"), tripleClosure));
						//declare object properties
						bw.write(KGUtilities.createObjectProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "hasKostraCode", kostraCodeEntity, " .\n"));
						//declare data properties
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "countyArea", StringUtilities.replaceQuotesAndCommas(kc.getCountyArea()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "countyAreaName", StringUtilities.replaceQuotesAndCommas(kc.getCountyAreaName()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "countyCode", StringUtilities.replaceQuotesAndCommas(kc.getCountyCode()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "countyCodeName", StringUtilities.replaceQuotesAndCommas(kc.getCountyCodeName()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "kostraCodeActivityId", StringUtilities.replaceQuotesAndCommas(kc.getActivityId()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "kostraCodeId", StringUtilities.replaceQuotesAndCommas(kc.getId()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "municipalityArea", StringUtilities.replaceQuotesAndCommas(kc.getMunicipalityArea()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "municipalityAreaName", StringUtilities.replaceQuotesAndCommas(kc.getMunicipalityAreaName()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "municipalityAreaCodeName", StringUtilities.replaceQuotesAndCommas(kc.getMunicipalityCodeName()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(kostraCodeEntity, baseURI, "municipalityAreaCode", StringUtilities.replaceQuotesAndCommas(kc.getMunicipalityCode()), "^^" + DATATYPE_STRING_NS, tripleClosure));
					}
					
				}
				
				if (legalReferences != null || !legalReferences.isEmpty()) {
					legalReferenceEntity = null;
					for (LegalReference lr : legalReferences) {
						legalReferenceEntity = StringUtilities.replaceQuotesAndCommas(lr.getShortTitle()) + ">";
						bw.write(KGUtilities.createType(legalReferenceEntity.replaceAll("[\\s()?+]", "_"), baseURI, RDF_TYPE_NS, legalReferenceType.replaceAll("[\\s()?+]", "_"), tripleClosure));
						//declare object properties
						bw.write(KGUtilities.createObjectProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "hasLegalReference", legalReferenceEntity.replaceAll("[\\s()?+]", "_"), " .\n"));
						//declare data properties
						bw.write(KGUtilities.createDataProperty(legalReferenceEntity.replaceAll("[\\s()?+]", "_"), baseURI, "activityId", StringUtilities.replaceQuotesAndCommas(lr.getActivityId()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(legalReferenceEntity.replaceAll("[\\s()?+]", "_"), baseURI, "documentId", StringUtilities.replaceQuotesAndCommas(lr.getDocumentId()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(legalReferenceEntity.replaceAll("[\\s()?+]", "_"), baseURI, "legalReferenceId", StringUtilities.replaceQuotesAndCommas(lr.getId()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(legalReferenceEntity.replaceAll("[\\s()?+]", "_"), baseURI, "legalReferenceTitle", StringUtilities.replaceQuotesAndCommas(lr.getTitle()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(legalReferenceEntity.replaceAll("[\\s()?+]", "_"), baseURI, "paragraph", StringUtilities.replaceQuotesAndCommas(lr.getParagraph()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(legalReferenceEntity.replaceAll("[\\s()?+]", "_"), baseURI, "paragraphTitle", StringUtilities.replaceQuotesAndCommas(lr.getParagraphTitle()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						bw.write(KGUtilities.createDataProperty(legalReferenceEntity.replaceAll("[\\s()?+]", "_"), baseURI, "shortTitle", StringUtilities.replaceQuotesAndCommas(lr.getShortTitle()), "^^" + DATATYPE_STRING_NS, tripleClosure));
						
					}			
				}
				
				//declare object properties
				bw.write(KGUtilities.createObjectProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "hasParentFunction", l3FunctionEntity.replaceAll("[\\s()?+]", "_"), " .\n"));
				bw.write(KGUtilities.createObjectProperty(l3FunctionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "hasParentFunction", l2FunctionEntity.replaceAll("[\\s()?+]", "_"), " .\n"));
				bw.write(KGUtilities.createObjectProperty(l2FunctionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "hasParentFunction", l1FunctionEntity.replaceAll("[\\s()?+]", "_"), " .\n"));
				
				//declare data properties
				bw.write(KGUtilities.createDataProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "category", StringUtilities.replaceQuotesAndCommas(kcf.getCategory()), "^^" + DATATYPE_STRING_NS, tripleClosure));
				bw.write(KGUtilities.createDataProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "countyGovernance", String.valueOf(kcf.isCountyGovernance()), "^^" + DATATYPE_BOOLEAN_NS, tripleClosure));
				bw.write(KGUtilities.createDataProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "description", StringUtilities.replaceQuotesAndCommas(kcf.getDescription()), "^^" + DATATYPE_STRING_NS, tripleClosure));
				bw.write(KGUtilities.createDataProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "functionId", StringUtilities.replaceQuotesAndCommas(kcf.getFunctionId()), "^^" + DATATYPE_STRING_NS, tripleClosure));
				bw.write(KGUtilities.createDataProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "id", StringUtilities.replaceQuotesAndCommas(kcf.getId()), "^^" + DATATYPE_STRING_NS, tripleClosure));
				bw.write(KGUtilities.createDataProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "kCode", StringUtilities.replaceQuotesAndCommas(kcf.getCode()), "^^" + DATATYPE_STRING_NS, tripleClosure));
				bw.write(KGUtilities.createDataProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "municipalityGovernance", String.valueOf(kcf.isMunicipalityGovernance()), "^^" + DATATYPE_BOOLEAN_NS, tripleClosure));
				bw.write(KGUtilities.createDataProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "stateGovernance", String.valueOf(kcf.isStateGovernance()), "^^" + DATATYPE_BOOLEAN_NS, tripleClosure));
				bw.write(KGUtilities.createDataProperty(functionEntity.replaceAll("[\\s()?+]", "_"), baseURI, "title", StringUtilities.replaceQuotesAndCommas(kcf.getTitle()), "^^" + DATATYPE_STRING_NS, tripleClosure));
			}
			
			bw.close();
			

		}

	}


