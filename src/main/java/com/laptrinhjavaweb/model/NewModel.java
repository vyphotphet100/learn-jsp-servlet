package com.laptrinhjavaweb.model;

import java.util.List;

public class NewModel extends AbstractModel<NewModel>{
	private String title;
	private String thumbnail;
	private String shortDescription;
	private String content;
	private Long categoryId;
	
	private List<CategoryModel> listCategory;
	private String categoryCode;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<CategoryModel> getListCategory() {
		return listCategory;
	}
	public void setListCategory(List<CategoryModel> listCategory) {
		this.listCategory = listCategory;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
}
