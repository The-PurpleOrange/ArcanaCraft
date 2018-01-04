package com.tyhone.arcanacraft.api.registries;

import com.tyhone.arcanacraft.api.ritual.RitualBase;

public class RitualManager {
	
	String ritualName;
	RitualBase ritual;
	
	public RitualManager(String ritualName, RitualBase ritualClass){
		this.ritualName = ritualName;
		this.ritual = ritualClass;
	}
	
	public String getRitualName(){
		return ritualName;
	}
	
	public RitualBase GetRitual(){
		return ritual;
	}

}
