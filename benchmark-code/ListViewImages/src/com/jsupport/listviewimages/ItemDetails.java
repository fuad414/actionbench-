package com.jsupport.listviewimages;

public class ItemDetails {
	private String name ;
	private String itemDescription;
	private int imageNumber;
	
	public ItemDetails(){
		
	}
	
	public ItemDetails(String newName, String description, int newNumber){
		name = newName;
		itemDescription = description;
		imageNumber = newNumber;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public int getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}
	
	

	
}
