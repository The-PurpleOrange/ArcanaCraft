package com.tyhone.arcanacraft.api.registries;

import com.tyhone.arcanacraft.api.ritual.IRitual;

public class RitualManager {
	
	String ritualName;
	Class ritualClass;
	
	public RitualManager(String ritualName, Class ritualClass){
		this.ritualName = ritualName;
		this.ritualClass = ritualClass;
	}
	
	public String getRitualName(){
		return ritualName;
	}
	
	public Class GetRitualClass(){
		return ritualClass;
	}

}
