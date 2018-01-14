package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.api.ritual.RitualBase;
import com.tyhone.arcanacraft.api.ritual.RitualRegistry;
import com.tyhone.arcanacraft.api.ritual.RitualTypeBase;
import com.tyhone.arcanacraft.common.rituals.GrandRitualSummonCreeper;
import com.tyhone.arcanacraft.common.rituals.RitualClearWeather;
import com.tyhone.arcanacraft.common.rituals.RitualSummonLightning;
import com.tyhone.arcanacraft.common.rituals.RitualSummonRain;

public class ModRituals {

	private static final List<RitualTypeBase> RITUAL_TYPES = new ArrayList<>();
	
	private static final List<RitualBase> RITUALS = new ArrayList<>();
	

	public static final RitualTypeBase RITUAL_TYPE_STANDARD = new RitualTypeBase(
			"ritual_type_standard", 
			RitualRegistry.getRitualList(), 
			ArcanacraftRitualCraftingManager.getRitualCircleRecipes(), 
			ArcanacraftRitualCraftingManager.getStandardBlockPosList(),
			ArcanacraftRitualCraftingManager.getStandardPlaceOrder()
		);
	public static final RitualTypeBase RITUAL_TYPE_GRAND = new RitualTypeBase(
			"ritual_type_grand", 
			RitualRegistry.getGrandRitualList(), 
			ArcanacraftRitualCraftingManager.getGrandRitualCircleRecipes(),
			ArcanacraftRitualCraftingManager.getGrandBlockPosList(),
			ArcanacraftRitualCraftingManager.getGrandPlaceOrder()
		);
	//public static final RitualTypeBase RITUAL_TYPE_SOUL_ALTAR = new RitualTypeBase("ritual_type_soul_altar", RitualRegistry.getSoulRitualList());
	
	
	public static final RitualBase RITUAL_SUMMON_RAIN = new RitualSummonRain("summon_rain", RITUAL_TYPE_STANDARD);
	public static final RitualBase RITUAL_CLEAR_WEATHER = new RitualClearWeather("clear_weather", RITUAL_TYPE_STANDARD);
	public static final RitualBase RITUAL_SUMMON_LIGHTNING = new RitualSummonLightning("summon_lightning", RITUAL_TYPE_STANDARD);

	public static final RitualBase GRAND_RITUAL_SUMMON_CREEPER = new GrandRitualSummonCreeper("summon_creeper", RITUAL_TYPE_GRAND);

	private ModRituals() {}
	
	public static List<RitualBase> getRituals(){
		return RITUALS;
	}
	
	public static void register(RitualBase ritual){
		RITUALS.add(ritual);
	}
	
	public static void register(RitualTypeBase ritualType){
		RITUAL_TYPES.add(ritualType);
	}
	
	
	public static void init(){
		for(RitualBase ritual : getRituals()){
			RitualRegistry.registerRitual(ritual);
		}
		for(RitualTypeBase ritualType : RITUAL_TYPES){
			RitualRegistry.registerRitualType(ritualType);
			RitualRegistry.registerRitualType(ritualType, ritualType.getRitualList());
		}
	}
}
