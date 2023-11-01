package com.admin.product;

import java.sql.Clob;

public class ProductDTO {
	// 제품 코드는 제품이름 + 양조장홈페이지
	private String productCode;
	private String productName;
	private int productPrice;
	private String productSubject;
	private String expirationDate;
	private String productStorage;
	private String breweryPage;
	private String productCategory;
	private String hashTag;
	private Double alcoholPercent;
	private String productTaste;
	private String productPerson;
	private int inventory;
	private String image;
	private int extinctOrNot;
	private int price;
	private int volume;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductSubject() {
		return productSubject;
	}
	public void setProductSubject(String productSubject) {
		this.productSubject = productSubject;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getProductStorage() {
		return productStorage;
	}
	public void setProductStorage(String productStorage) {
		this.productStorage = productStorage;
	}
	public String getBreweryPage() {
		return breweryPage;
	}
	public void setBreweryPage(String breweryPage) {
		this.breweryPage = breweryPage;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	public Double getAlcoholPercent() {
		return alcoholPercent;
	}
	public void setAlcoholPercent(Double alcoholPercent) {
		this.alcoholPercent = alcoholPercent;
	}
	public String getProductTaste() {
		return productTaste;
	}
	public void setProductTaste(String productTaste) {
		this.productTaste = productTaste;
	}
	public String getProductPerson() {
		return productPerson;
	}
	public void setProductPerson(String productPerson) {
		this.productPerson = productPerson;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getExtinctOrNot() {
		return extinctOrNot;
	}
	public void setExtinctOrNot(int extinctOrNot) {
		this.extinctOrNot = extinctOrNot;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	
}
