package com.tyhone.arcanacraft.api.registries;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.ritual.RitualBase;

public class RitualRegistry {
	
	public static List<Ritual> ritualList = new ArrayList<Ritual>();
	
	
	public static Ritual registerRitual(RitualBase ritualClass) {
		Ritual ritual = new Ritual(ritualClass.getRitualName(), ritualClass);
		ritualList.add(ritual);
		return ritual;
	}
	
	public static List<Ritual> getRitualList(){
		return ritualList;
	}
	
	
}
