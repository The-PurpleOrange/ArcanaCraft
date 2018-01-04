package com.tyhone.arcanacraft.api.registries;

import java.util.ArrayList;
import java.util.List;

public class TinktureManager {
	
	public static List<RegistryTinktureType> tinktureTypeList = new ArrayList<RegistryTinktureType>();
	

	//TINKTURE TYPES
	public static RegistryTinktureType registerTinktureType(String fluidType, int colourHex) {
		RegistryTinktureType recipe = new RegistryTinktureType(fluidType, colourHex);
		tinktureTypeList.add(recipe);
		return recipe;
	}
	
	public static List<RegistryTinktureType> getTinktureTypes(){
		return tinktureTypeList;
	}

	public static ArrayList<String> getTinktureNames(){
		ArrayList<String> tinktureNames = new ArrayList<String>();
		for(RegistryTinktureType tinkture : tinktureTypeList){
			tinktureNames.add(tinkture.getFluidType());
		}
		return tinktureNames;
	}
	
	public static String getTinktureNameFromMeta(int i){
		ArrayList<String> tinktureNames = new ArrayList<String>();
		for(RegistryTinktureType tinkture : tinktureTypeList){
			tinktureNames.add(tinkture.getFluidType());
		}
		return tinktureNames.get(i);
	}

	public static ArrayList<Integer> getTinktureHexs(){
		ArrayList<Integer> tinktureHexs = new ArrayList<Integer>();
		for(RegistryTinktureType tinkture : tinktureTypeList){
			tinktureHexs.add(tinkture.getColourHex());
		}
		return tinktureHexs;
	}
	
	public static int getTinktureHexFromMeta(int i){
		ArrayList<Integer> tinktureHexs = new ArrayList<Integer>();
		for(RegistryTinktureType tinkture : tinktureTypeList){
			tinktureHexs.add(tinkture.getColourHex());
		}
		return tinktureHexs.get(i);
	}
	
}
