package com.tyhone.arcanacraft.api.ritual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArcanacraftRitualManager {

	
	public static HashMap<RitualType, List<Ritual>> hashRitualTypeMap = new HashMap<RitualType, List<Ritual>>();
	public static List<RitualType> ritualTypesList = new ArrayList<RitualType>();
	
	public static List<Ritual> chalkList = new ArrayList<Ritual>();
	public static List<Ritual> ritualList = new ArrayList<Ritual>();
	public static List<Ritual> grandRitualList = new ArrayList<Ritual>();
	//public static List<RitualBase> soulRitualList = new ArrayList<RitualBase>();
	
	//RITUAL TYPE
	public static void registerRitualType(RitualType ritualType, List<Ritual> list){
		hashRitualTypeMap.put(ritualType, list);
	}
	
	public static HashMap<RitualType, List<Ritual>> getRitualRegistyLists(){
		return hashRitualTypeMap;
	}
	
	public static List<Ritual> getRitualListFromHashMap(RitualType ritualType){
		return hashRitualTypeMap.get(ritualType);
	}
	
	public static void registerRitualType(RitualType ritualType){
		ritualTypesList.add(ritualType);
	}
	
	public static List<RitualType> getRitualTypeList(){
		return ritualTypesList;
	}
	
	public static List<Ritual> getRitualListFromString(String ritualTypeName){
		if(ritualTypeName != null){
			RitualType ritualType = getRitualTypeFromString(ritualTypeName);
			if(ritualType != null){
				return getRitualListFromHashMap(ritualType);
			}
		}
		return null;
	}
	
	public static RitualType getRitualTypeFromString(String ritualTypeName){
		for(RitualType ritualType : ritualTypesList){
			if(ritualTypeName.equals(ritualType.getUnlocalizedName())){
				return ritualType;
			}
		}
		return null;
	}

	public static Ritual getRitualFromString(String ritualName, RitualType ritualType) {
		List<Ritual> ritualList = getRitualListFromHashMap(ritualType);
		for(Ritual ritual : ritualList){
			if(ritualName.equals(ritual.getUnlocalizedName())){
				return ritual;
			}
		}
		return null;
	}

	
	//CHALK RITUAL
	public static void registerChalk(Ritual ritual) {
		ritual.getRitualType().getRitualList().add(ritual);
	}
	
	public static List<Ritual> getChalkList(){
		return chalkList;
	}

	
	//STANDARD RITUAL
	public static void registerRitual(Ritual ritual) {
		ritual.getRitualType().getRitualList().add(ritual);
	}
	
	public static List<Ritual> getRitualList(){
		return ritualList;
	}
	
	
	//GRAND RITUAL
	public static void registerGrandRitual(Ritual ritual) {
		ritual.getRitualType().getRitualList().add(ritual);
		//grandRitualList.add(ritual);
	}
	
	public static List<Ritual> getGrandRitualList(){
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
