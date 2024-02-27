package objects;
import java.util.List;
import java.util.Objects;

public class KCodesFunction {
	
	private String id;
	private String title;
	private String category;
	private String code;
	private String description;
	private String functionId;
	private boolean countyGovernance;
	private boolean municipalityGovernance;
	private boolean stateGovernance;
	private List<LegalReference> legalReferences;
	private List<KostraCode> kostraCodes;
	private String l1Title;
	private String l2Title;
	private String l3Title;
	
	public KCodesFunction(KCodesFunctionBuilder builder) {
		
		this.id = builder.id;
		this.title = builder.title;
		this.category = builder.category;
		this.code = builder.code;
		this.description = builder.description;
		this.functionId = builder.functionId;
		this.countyGovernance = builder.countyGovernance;
		this.municipalityGovernance = builder.municipalityGovernance;
		this.stateGovernance = builder.stateGovernance;
		this.legalReferences = builder.legalReferences;
		this.kostraCodes = builder.kostraCodes;
		this.l1Title = builder.l1Title;
		this.l2Title = builder.l2Title;
		this.l3Title = builder.l3Title;
		
	}
	
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public String getFunctionId() {
		return functionId;
	}

	public boolean isCountyGovernance() {
		return countyGovernance;
	}

	public boolean isMunicipalityGovernance() {
		return municipalityGovernance;
	}

	public boolean isStateGovernance() {
		return stateGovernance;
	}

	public List<LegalReference> getLegalReferences() {
		return legalReferences;
	}

	public List<KostraCode> getKostraCodes() {
		return kostraCodes;
	}

	public String getL1Title() {
		return l1Title;
	}

	public String getL2Title() {
		return l2Title;
	}

	public String getL3Title() {
		return l3Title;
	}

	@Override
	public boolean equals (Object o) {
		if ( o instanceof KostraCode && ((KostraCode) o).getId() == this.id ) {
			return true;
		} else {
			return false;
		}
	}
	
	  @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }
	  
	  public String toString() {
		  
		  return "Id: " + this.id + "\nTitle: " + this.title + "\nKCode: " 
		  + this.code + "\nL1-function: " + this.l1Title + "\nL2-function: " 
				  + this.l2Title + "\nL3-function: " + this.l3Title 
				  + "\nLegal References: " + this.legalReferences 
				  + "\nKostra Codes:" + this.kostraCodes;
		  
	  }


	public static class KCodesFunctionBuilder {
		
		private String id;
		private String title;
		private String category;
		private String code;
		private String description;
		private String functionId;
		private boolean countyGovernance;
		private boolean municipalityGovernance;
		private boolean stateGovernance;
		private List<LegalReference> legalReferences;
		private List<KostraCode> kostraCodes;
		private String l1Title;
		private String l2Title;
		private String l3Title;
		
		public KCodesFunctionBuilder() {}

		public KCodesFunctionBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public KCodesFunctionBuilder setTitle(String title) {
			this.title = title;
			return this;
		}

		public KCodesFunctionBuilder setCategory(String category) {
			this.category = category;
			return this;
		}

		public KCodesFunctionBuilder setCode(String code) {
			this.code = code;
			return this;
		}

		public KCodesFunctionBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public KCodesFunctionBuilder setFunctionId(String functionId) {
			this.functionId = functionId;
			return this;
		}

		public KCodesFunctionBuilder setCountyGovernance(boolean countyGovernance) {
			this.countyGovernance = countyGovernance;
			return this;
		}

		public KCodesFunctionBuilder setMunicipalityGovernance(boolean municipalityGovernance) {
			this.municipalityGovernance = municipalityGovernance;
			return this;
		}

		public KCodesFunctionBuilder setStateGovernance(boolean stateGovernance) {
			this.stateGovernance = stateGovernance;
			return this;
		}

		public KCodesFunctionBuilder setLegalReferences(List<LegalReference> legalReferences) {
			this.legalReferences = legalReferences;
			return this;
		}

		public KCodesFunctionBuilder setKostraCodes(List<KostraCode> kostraCodes) {
			this.kostraCodes = kostraCodes;
			return this;
		}
		
		public KCodesFunctionBuilder setL1Title(String l1Title) {
			this.l1Title = l1Title;
			return this;
		}

		public KCodesFunctionBuilder setL2Title(String l2Title) {
			this.l2Title = l2Title;
			return this;
		}

		public KCodesFunctionBuilder setL3Title(String l3Title) {
			this.l3Title = l3Title;
			return this;
		}

		public KCodesFunction build() {
			return new KCodesFunction(this);
		}
	}
}
