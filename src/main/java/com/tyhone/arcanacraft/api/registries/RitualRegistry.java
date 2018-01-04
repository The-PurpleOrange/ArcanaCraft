package com.tyhone.arcanacraft.api.registries;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.ritual.IRitual;

public class RitualRegistry {
	
	public static List<RitualManager> ritualList = new ArrayList<RitualManager>();
	
	
	public static RitualManager registerRitual(String ritualName, Class<? extends IRitual> ritualClass) {
		RitualManager ritual = new RitualManager(ritualName, ritualClass);
		ritualList.add(ritual);
		return ritual;
	}
	
	public static List<RitualManager> getRitualList(){
		return ritualList;
	}
	
	
}
