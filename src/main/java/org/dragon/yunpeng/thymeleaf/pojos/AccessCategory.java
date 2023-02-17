package org.dragon.yunpeng.thymeleaf.pojos;

import java.util.ArrayList;
import java.util.List;

public class AccessCategory {

	private int categoryId;
	private String categoryName;

	private List<LabelIdPair> labelIdPairList;

	public AccessCategory() {
		labelIdPairList = new ArrayList<LabelIdPair>();
	}

	public AccessCategory(int categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;

		labelIdPairList = new ArrayList<LabelIdPair>();

		for (int i = 0; i < 4; i++) {
			LabelIdPair labelIdPair = new LabelIdPair(categoryName, "Label" + i, -1);
			labelIdPairList.add(labelIdPair);
		}

	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<LabelIdPair> getLabelIdPairList() {
		return labelIdPairList;
	}

	public void setLabelIdPairList(List<LabelIdPair> labelIdPairList) {
		this.labelIdPairList = labelIdPairList;
	}

	@Override
	public String toString() {
		return "{categoryId=" + categoryId + ", categoryName=" + categoryName + ", labelIdPairList=" + labelIdPairList
				+ ")";
	}
}
