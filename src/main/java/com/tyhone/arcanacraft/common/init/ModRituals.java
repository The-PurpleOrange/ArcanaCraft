package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.api.ritual.RitualRegistry;
import com.tyhone.arcanacraft.api.ritual.RitualType;
import com.tyhone.arcanacraft.common.rituals.GrandRitualSummonChicken;
import com.tyhone.arcanacraft.common.rituals.RitualClearWeather;
import com.tyhone.arcanacraft.common.rituals.RitualSummonLightning;
import com.tyhone.arcanacraft.common.rituals.RitualSummonRain;

public class ModRituals {

	private static final List<RitualType> RITUAL_TYPES = new ArrayList<>();
	
	private static final List<Ritual> RITUALS = new ArrayList<>();
	

	public static final RitualType RITUAL_TYPE_STANDARD = new RitualType(
			"ritual_type_standard", 
			RitualRegistry.getRitualList(), 
			ArcanacraftRitualCraftingManager.getRitualCircleRecipes(), 
			ArcanacraftRitualCraftingManager.getStandardBlockPosList(),
			ArcanacraftRitualCraftingManager.getStandardPlaceOrder()
		);
	public static final RitualType RITUAL_TYPE_GRAND = new RitualType(
			"ritual_type_grand", 
			RitualRegistry.getGrandRitualList(), 
			ArcanacraftRitualCraftingManager.getGrandRitualCircleRecipes(),
			ArcanacraftRitualCraftingManager.getGrandBlockPosList(),
			ArcanacraftRitualCraftingManager.getGrandPlaceOrder()
		);
	//public static final RitualTypeBase RITUAL_TYPE_SOUL_ALTAR = new RitualTypeBase("ritual_type_soul_altar", RitualRegistry.getSoulRitualList());
	
	
	public static final Ritual RITUAL_SUMMON_RAIN = new RitualSummonRain("summon_rain", RITUAL_TYPE_STANDARD);
	public static final Ritual RITUAL_CLEAR_WEATHER = new RitualClearWeather("clear_weather", RITUAL_TYPE_STANDARD);
	public static final Ritual RITUAL_SUMMON_LIGHTNING = new RitualSummonLightning("summon_lightning", RITUAL_TYPE_STANDARD);

	public static final Ritual GRAND_RITUAL_SUMMON_CHICKEN = new GrandRitualSummonChicken("summon_chicken", RITUAL_TYPE_GRAND);

	private ModRituals() {}
	
	public static List<Ritual> getRituals(){
		return RITUALS;
	}
	
	public static void register(Ritual ritual){
		RITUALS.add(ritual);
	}
	
	public static void register(RitualType ritualType){
		RITUAL_TYPES.add(ritualType);
	}
	
	
	public static void init(){
		for(Ritual ritual : getRituals()){
			RitualRegistry.registerRitual(ritual);
		}
		for(RitualType ritualType : RITUAL_TYPES){
			RitualRegistry.registerRitualType(ritualType);
			RitualRegistry.registerRitualType(ritualType, ritualType.getRitualList());
		}
	}
}
