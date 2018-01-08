package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.registries.RitualRegistry;
import com.tyhone.arcanacraft.api.ritual.RitualBase;
import com.tyhone.arcanacraft.common.rituals.RitualClearWeather;
import com.tyhone.arcanacraft.common.rituals.RitualSummonLightning;
import com.tyhone.arcanacraft.common.rituals.RitualSummonRain;

public class ModRituals {

	private static final List<RitualBase> RITUALS = new ArrayList<>();
	
	public static final RitualBase RITUAL_SUMMON_RAIN = new RitualSummonRain("summon_rain");
	public static final RitualBase RITUAL_CLEAR_WEATHER = new RitualClearWeather("clear_weather");
	public static final RitualBase RITUAL_SUMMON_LIGHTNING = new RitualSummonLightning("summon_lightning");

	private ModRituals() {}
	
	public static Collection<RitualBase> getRituals(){
		return RITUALS;
	}
	
	public static void register(RitualBase ritual){
		RITUALS.add(ritual);
	}
	
	public static void init(){
		for(RitualBase ritual : getRituals()){
			RitualRegistry.registerRitual(ritual);
		}
	}
}
