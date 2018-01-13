package com.tyhone.arcanacraft.api.ritual;

import java.util.ArrayList;
import java.util.List;

public class RitualRegistry {

	public static List<List> ritualTypes = new ArrayList<List>();
	
	public static List<RitualBase> ritualList = new ArrayList<RitualBase>();
	public static List<RitualBase> grandRitualList = new ArrayList<RitualBase>();
	
	
	public static void registerRitualType(List<List> registryList){
		ritualTypes.add(registryList);
	}
	
	public static List<List> getRitualRegistyLists(){
		return ritualTypes;
	}

	public static void registerRitual(RitualBase ritual) {
		ritualList.add(ritual);
	}
	
	public static List<RitualBase> getRitualList(){
		return ritualList;
	}
	
	public static void registerGrandRitual(RitualBase ritual) {
		grandRitualList.add(ritual);
	}
	
	public static List<RitualBase> getGrandRitualList(){
		return grandRitualList;
	}
	
	
}
