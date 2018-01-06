package com.tyhone.arcanacraft.api.registries;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.common.init.ModTinktureTypes;

public class TinktureManager {
	
	public static List<TinktureType> tinktureTypeList = new ArrayList<TinktureType>();
	
	
	public static void registerTinktureType(TinktureType tinktureType) {
		tinktureTypeList.add(tinktureType);
	}
	
	public static List<TinktureType> getTinktureTypes(){
		return tinktureTypeList;
	}

	public static ArrayList<String> getTinktureNames(){
		ArrayList<String> tinktureNames = new ArrayList<String>();
		for(TinktureType tinkture : tinktureTypeList){
			if(tinkture != ModTinktureTypes.EMPTY){
				tinktureNames.add(tinkture.getTinktureName());
			}
		}
		return tinktureNames;
	}
	
	public static TinktureType getTinktureTypeFromMeta(int i){
		ArrayList<TinktureType> tinktureNames = new ArrayList<TinktureType>();
		for(TinktureType tinkture : tinktureTypeList){
			tinktureNames.add(tinkture);
		}
		return tinktureNames.get(i);
	}

	public static ArrayList<Integer> getTinktureHexs(){
		ArrayList<Integer> tinktureHexs = new ArrayList<Integer>();
		for(TinktureType tinkture : tinktureTypeList){
			tinktureHexs.add(tinkture.getColourHex());
		}
		return tinktureHexs;
	}
	
	public static int getTinktureHexFromMeta(int i){
		ArrayList<Integer> tinktureHexs = new ArrayList<Integer>();
		for(TinktureType tinkture : tinktureTypeList){
			tinktureHexs.add(tinkture.getColourHex());
		}
		return tinktureHexs.get(i);
	}
	
}
