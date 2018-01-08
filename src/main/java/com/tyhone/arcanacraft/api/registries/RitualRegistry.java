package com.tyhone.arcanacraft.api.registries;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.ritual.RitualBase;

public class RitualRegistry {
	
	public static List<RitualBase> ritualList = new ArrayList<RitualBase>();
	
	
	public static void registerRitual(RitualBase ritual) {
		ritualList.add(ritual);
	}
	
	public static List<RitualBase> getRitualList(){
		return ritualList;
	}
	
	
}
