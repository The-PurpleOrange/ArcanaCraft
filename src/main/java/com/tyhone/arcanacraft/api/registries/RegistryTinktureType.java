package com.tyhone.arcanacraft.api.registries;

public class RegistryTinktureType {

	String fluidType;
	int colourHex;
	
	public RegistryTinktureType(String fluidType, int colourHex){
		this.fluidType = fluidType;
		this.colourHex = colourHex;
	}
	
	public String getFluidType(){
		return this.fluidType;
	}
	
	public int getColourHex(){
		return colourHex;
	}
	
}
