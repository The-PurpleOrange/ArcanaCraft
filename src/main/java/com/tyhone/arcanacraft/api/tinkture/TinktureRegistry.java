package com.tyhone.arcanacraft.api.tinkture;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;

import net.minecraft.item.ItemStack;

public class TinktureRegistry {
	
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
	
	public static int getTinktureColourFromName(String name){
		for(TinktureType tinkture : tinktureTypeList){
			if(tinkture.getRegistryName().toString().equals(name)){
				return tinkture.getColourHex();
			}
		}
		return 0x000000;
	}
	
	public static TinktureType getTinktureTypeFromName(String name){
		for(TinktureType tinkture : tinktureTypeList){
			if(tinkture.getRegistryName().toString().equals(name)){
				return tinkture;
			}
		}
		return null;
	}

	public static ArrayList<Integer> getTinktureHexs(){
		ArrayList<Integer> tinktureHexs = new ArrayList<Integer>();
		for(TinktureType tinkture : tinktureTypeList){
			tinktureHexs.add(tinkture.getColourHex());
		}
		return tinktureHexs;
	}
}
