package objects;
import java.util.Objects;

public class LegalReference {
	
	private String id;
	private String title;
	private String shortTitle;
	private String paragraph;
	private String paragraphTitle;
	private String documentId;
	private String activityId;
	
	public LegalReference (LegalReferenceBuilder builder) {
		
		this.id = builder.id;
		this.title = builder.title;
		this.shortTitle = builder.shortTitle;
		this.paragraph = builder.paragraph;
		this.paragraphTitle = builder.paragraphTitle;
		this.documentId = builder.documentId;
		this.activityId = builder.activityId;
		
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public String getParagraph() {
		return paragraph;
	}

	public String getParagraphTitle() {
		return paragraphTitle;
	}

	public String getDocumentId() {
		return documentId;
	}

	public String getActivityId() {
		return activityId;
	}
	
	@Override
	public boolean equals (Object o) {
		if ( o instanceof LegalReference && ((LegalReference) o).getId() == this.id ) {
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
		  
		  return "Legal Reference ID: " + this.id;
	  }

	public static class LegalReferenceBuilder {
		
		private String id;
		private String title;
		private String shortTitle;
		private String paragraph;
		private String paragraphTitle;
		private String documentId;
		private String activityId;
		
		public LegalReferenceBuilder() {}

		public LegalReferenceBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public LegalReferenceBuilder setTitle(String title) {
			this.title = title;
			return this;
		}

		public LegalReferenceBuilder setShortTitle(String shortTitle) {
			this.shortTitle = shortTitle;
			return this;
		}

		public LegalReferenceBuilder setParagraph(String paragraph) {
			this.paragraph = paragraph;
			return this;
		}

		public LegalReferenceBuilder setParagraphTitle(String paragraphTitle) {
			this.paragraphTitle = paragraphTitle;
			return this;
		}

		public LegalReferenceBuilder setDocumentId(String documentId) {
			this.documentId = documentId;
			return this;
		}

		public LegalReferenceBuilder setActivityId(String activityId) {
			this.activityId = activityId;
			return this;
		}
		
		public LegalReference build() {
			return new LegalReference(this);
		}
		
		
	}

}
