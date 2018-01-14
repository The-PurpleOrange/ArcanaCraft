package com.tyhone.arcanacraft.api.ritual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RitualRegistry {

	
	public static HashMap<RitualTypeBase, List<RitualBase>> hashRitualTypeMap = new HashMap<RitualTypeBase, List<RitualBase>>();
	public static List<RitualTypeBase> ritualTypesList = new ArrayList<RitualTypeBase>();
	
	public static List<RitualBase> ritualList = new ArrayList<RitualBase>();
	public static List<RitualBase> grandRitualList = new ArrayList<RitualBase>();
	//public static List<RitualBase> soulRitualList = new ArrayList<RitualBase>();
	
	//RITUAL TYPE
	public static void registerRitualType(RitualTypeBase ritualType, List<RitualBase> list){
		hashRitualTypeMap.put(ritualType, list);
	}
	
	public static HashMap<RitualTypeBase, List<RitualBase>> getRitualRegistyLists(){
		return hashRitualTypeMap;
	}
	
	public static List<RitualBase> getRitualListFromHashMap(RitualTypeBase ritualType){
		return hashRitualTypeMap.get(ritualType);
	}
	
	public static void registerRitualType(RitualTypeBase ritualType){
		ritualTypesList.add(ritualType);
	}
	
	public static List<RitualTypeBase> getRitualTypeList(){
		return ritualTypesList;
	}
	
	public static List<RitualBase> getRitualListFromString(String ritualTypeName){
		if(ritualTypeName != null){
			RitualTypeBase ritualType = getRitualTypeFromString(ritualTypeName);
			if(ritualType != null){
				return getRitualListFromHashMap(ritualType);
			}
		}
		return null;
	}
	
	public static RitualTypeBase getRitualTypeFromString(String ritualTypeName){
		for(RitualTypeBase ritualType : ritualTypesList){
			if(ritualTypeName.equals(ritualType.getUnlocalizedName())){
				return ritualType;
			}
		}
		return null;
	}

	public static RitualBase getRitualFromString(String ritualName, RitualTypeBase ritualType) {
		List<RitualBase> ritualList = getRitualListFromHashMap(ritualType);
		for(RitualBase ritual : ritualList){
			if(ritualName.equals(ritual.getUnlocalizedName())){
				return ritual;
			}
		}
		return null;
	}

	
	//STANDARD RITUAL
	public static void registerRitual(RitualBase ritual) {
		ritual.getRitualType().getRitualList().add(ritual);
	}
	
	public static List<RitualBase> getRitualList(){
		return ritualList;
	}
	
	
	//GRAND RITUAL
	public static void registerGrandRitual(RitualBase ritual) {
		grandRitualList.add(ritual);
	}
	
	public static List<RitualBase> getGrandRitualList(){
		return grandRitualList;
	}
	
	
	//SOUL RITUAL
	/*public static void registerSoulRitual(RitualBase ritual) {
		soulRitualList.add(ritual);
	}
	
	public static List<RitualBase> getSoulRitualList(){
		return soulRitualList;
	}*/
}
