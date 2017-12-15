package com.tyhone.arcanacraft.common.items.enums;

import java.util.ArrayList;

public enum EnumTinktures {
	IMPIRUS(0, "impirus", "ed170b"),
	AMNIS(1, "amnis", "0a78ed"),
	REBUS(2, "rebus", "239909"),
	LOCUS(3, "locus", "fcec0a");
	
	private int meta;
	private String name;
	private String hex;
	
	private EnumTinktures(int meta, String name, String hex){
		this.meta = meta;
		this.name = name;
		this.hex = hex;
	}
	
	public int getMeta(){
		return meta;
	}
	
	public String getName(){
		return name;
	}
	
	public String getHex(){
		return hex;
	}
	
	public static ArrayList<String> getAllNames(){
		ArrayList<String> names = new ArrayList<>();
		for(EnumTinktures e : values()) {
	        names.add(e.name);
	    }
	    return names;
	}
	
	public static EnumTinktures getByMeta(int meta){
		for(EnumTinktures e : values()) {
	        if(e.meta == meta){ 
	        	return e;
	        }
	    }
	    return null;
	}
	
	public static String getNameByMeta(int meta){
		return getByMeta(meta).getName();
	}
	
	public static String getHexByMeta(int meta){
		return getByMeta(meta).getHex();
	}
	

	public static EnumTinktures getByName(String name){
		for(EnumTinktures e : values()) {
	        if(e.name == name){ 
	        	return e;
	        }
	    }
	    return null;
	}
	
	public static String getHexByName(String name){
		return getByName(name).getHex();
	}
}
