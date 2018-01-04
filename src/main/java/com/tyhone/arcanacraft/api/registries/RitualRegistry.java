package com.tyhone.arcanacraft.api.registries;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.ritual.RitualBase;

public class RitualRegistry {
	
	public static List<RitualManager> ritualList = new ArrayList<RitualManager>();
	
	
	public static RitualManager registerRitual(RitualBase ritualClass) {
		RitualManager ritual = new RitualManager(ritualClass.getRitualName(), ritualClass);
		ritualList.add(ritual);
		return ritual;
	}
	
	public static List<RitualManager> getRitualList(){
		return ritualList;
	}
	
	
}
