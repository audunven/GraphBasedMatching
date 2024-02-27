package objects;
import java.util.Objects;

public class KostraCode {
	
	private String id;
	private String municipalityArea;
	private String municipalityAreaName;
	private String municipalityCode;
	private String municipalityCodeName;
	private String countyArea;
	private String countyAreaName;
	private String countyCode;
	private String countyCodeName;
	private String activityId;
	
	public KostraCode (KostraCodeBuilder builder) {
		
		this.id = builder.id;
		this.municipalityArea = builder.municipalityArea;
		this.municipalityAreaName = builder.municipalityAreaName;
		this.municipalityCode = builder.municipalityCode;
		this.municipalityCodeName = builder.municipalityCodeName;
		this.countyArea = builder.countyArea;
		this.countyAreaName = builder.countyAreaName;
		this.countyCode = builder.countyCode;
		this.countyCodeName = builder.countyCodeName;
		this.activityId = builder.activityId;
	}

	public String getId() {
		return id;
	}
	
	public String getMunicipalityArea() {
		return municipalityArea;
	}
	
	public String getMunicipalityAreaName() {
		return municipalityAreaName;
	}

	public String getMunicipalityCode() {
		return municipalityCode;
	}

	public String getMunicipalityCodeName() {
		return municipalityCodeName;
	}

	public String getCountyArea() {
		return countyArea;
	}

	public String getCountyAreaName() {
		return countyAreaName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public String getCountyCodeName() {
		return countyCodeName;
	}

	public String getActivityId() {
		return activityId;
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
		  
		  return "Kostra Code ID: " + this.id;
	  }

	public static class KostraCodeBuilder {
		
		private String id;
		private String municipalityArea;
		private String municipalityAreaName;
		private String municipalityCode;
		private String municipalityCodeName;
		private String countyArea;
		private String countyAreaName;
		private String countyCode;
		private String countyCodeName;
		private String activityId;
		
		public KostraCodeBuilder() {}

		public KostraCodeBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public KostraCodeBuilder setMunicipalityArea(String municipalityArea) {
			this.municipalityArea = municipalityArea;
			return this;
		}

		public KostraCodeBuilder setMunicipalityAreaName(String municipalityAreaName) {
			this.municipalityAreaName = municipalityAreaName;
			return this;
		}

		public KostraCodeBuilder setMunicipalityCode(String municipalityCode) {
			this.municipalityCode = municipalityCode;
			return this;
		}

		public KostraCodeBuilder setMunicipalityCodeName(String municipalityCodeName) {
			this.municipalityCodeName = municipalityCodeName;
			return this;
		}

		public KostraCodeBuilder setCountyArea(String countyArea) {
			this.countyArea = countyArea;
			return this;
		}

		public KostraCodeBuilder setCountyAreaName(String countyAreaName) {
			this.countyAreaName = countyAreaName;
			return this;
		}

		public KostraCodeBuilder setCountyCode(String countyCode) {
			this.countyCode = countyCode;
			return this;
		}

		public KostraCodeBuilder setCountyCodeName(String countyCodeName) {
			this.countyCodeName = countyCodeName;
			return this;
		}

		public KostraCodeBuilder setActivityId(String activityId) {
			this.activityId = activityId;
			return this;
		}
		
		public KostraCode build() {
			return new KostraCode(this);
		}
		
		
	}
}
