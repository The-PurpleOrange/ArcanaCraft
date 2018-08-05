package com.tyhone.arcanacraft.common.init;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.api.ritual.ArcanacraftRitualManager;
import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.api.ritual.RitualType;
import com.tyhone.arcanacraft.common.rituals.GrandRitualCreateChicken;
import com.tyhone.arcanacraft.common.rituals.GrandRitualCreateHomunculus;
import com.tyhone.arcanacraft.common.rituals.RitualClearWeather;
import com.tyhone.arcanacraft.common.rituals.RitualGrowth;
import com.tyhone.arcanacraft.common.rituals.RitualPlaceChalk;
import com.tyhone.arcanacraft.common.rituals.RitualSummonLightning;
import com.tyhone.arcanacraft.common.rituals.RitualSummonRain;
import com.tyhone.arcanacraft.common.rituals.RitualWhy;

public class ModRituals {

	private static final List<RitualType> RITUAL_TYPES = new ArrayList<>();
	
	private static final List<Ritual> RITUALS = new ArrayList<>();
	

	public static final RitualType RITUAL_TYPE_STANDARD = new RitualType(
			"ritual_type_standard", 
			ArcanacraftRitualManager.getRitualList(), 
			ArcanacraftRitualCraftingManager.getRitualCircleRecipes(), 
			ArcanacraftRitualCraftingManager.getStandardBlockPosList(),
			ArcanacraftRitualCraftingManager.getStandardPlaceOrder()
		);
	public static final RitualType RITUAL_TYPE_GRAND = new RitualType(
			"ritual_type_grand", 
			ArcanacraftRitualManager.getGrandRitualList(), 
			ArcanacraftRitualCraftingManager.getGrandRitualCircleRecipes(),
			ArcanacraftRitualCraftingManager.getGrandBlockPosList(),
			ArcanacraftRitualCraftingManager.getGrandPlaceOrder()
		);
	public static final RitualType RITUAL_TYPE_CHALK = new RitualType(
			"ritual_type_chalk", 
			ArcanacraftRitualManager.getChalkList(), 
			ArcanacraftRitualCraftingManager.getRitualChalkRecipes(), 
			ArcanacraftRitualCraftingManager.getChalkBlockPosList(), 
			ArcanacraftRitualCraftingManager.getChalkPlaceOrder()
		);
	
	//public static final RitualTypeBase RITUAL_TYPE_SOUL_ALTAR = new RitualTypeBase("ritual_type_soul_altar", RitualRegistry.getSoulRitualList());

	//CHALK
	public static final Ritual CHALK_CHARCOAL = new RitualPlaceChalk("charcoal", RITUAL_TYPE_CHALK);
	public static final Ritual CHALK_BONE = new RitualPlaceChalk("bone", RITUAL_TYPE_CHALK);
	public static final Ritual CHALK_GREEN = new RitualPlaceChalk("green", RITUAL_TYPE_CHALK);
	public static final Ritual CHALK_BLOOD = new RitualPlaceChalk("blood", RITUAL_TYPE_CHALK);
	public static final Ritual CHALK_LAPIS = new RitualPlaceChalk("lapis", RITUAL_TYPE_CHALK);
	public static final Ritual CHALK_GOLD = new RitualPlaceChalk("gold", RITUAL_TYPE_CHALK);
	public static final Ritual CHALK_MAGICITE = new RitualPlaceChalk("magicite", RITUAL_TYPE_CHALK);
	
	//STANDARD
	public static final Ritual RITUAL_SUMMON_RAIN = new RitualSummonRain("summon_rain", RITUAL_TYPE_STANDARD);
	public static final Ritual RITUAL_CLEAR_WEATHER = new RitualClearWeather("clear_weather", RITUAL_TYPE_STANDARD);
	public static final Ritual RITUAL_SUMMON_LIGHTNING = new RitualSummonLightning("summon_lightning", RITUAL_TYPE_STANDARD);

	public static final Ritual RITUAL_WHY = new RitualWhy("why", RITUAL_TYPE_STANDARD);
	public static final Ritual RITUAL_GROWTH = new RitualGrowth("growth", RITUAL_TYPE_STANDARD);

	//GRAND
	public static final Ritual GRAND_RITUAL_CREATE_HOMUNCULUS = new GrandRitualCreateHomunculus("create_homunculus", RITUAL_TYPE_GRAND);
	public static final Ritual GRAND_RITUAL_SUMMON_CHICKEN = new GrandRitualCreateChicken("summon_chicken", RITUAL_TYPE_GRAND);

	public static final Ritual GRAND_RITUAL_WHY = new RitualWhy("grand_why", RITUAL_TYPE_GRAND);

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
			ArcanacraftRitualManager.registerRitual(ritual);
		}
		for(RitualType ritualType : RITUAL_TYPES){
			ArcanacraftRitualManager.registerRitualType(ritualType);
			ArcanacraftRitualManager.registerRitualType(ritualType, ritualType.getRitualList());
		}
	}
}
