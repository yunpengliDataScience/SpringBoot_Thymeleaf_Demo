package org.dragon.yunpeng.thymeleaf.pojos;

public class LabelIdPair {

	private String categoryName;
	private String label;
	private Integer id;

	public LabelIdPair() {
	}

	public LabelIdPair(String label, Integer id) {
		this.label = label;
		this.id = id;
	}

	public LabelIdPair(String categoryName, String label, Integer id) {
		this.categoryName = categoryName;
		this.label = label;
		this.id = id;
	}

	@Override
	public String toString() {
		return "(categoryName=" + categoryName + ", label=" + label + ", id=" + id + ")";
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
