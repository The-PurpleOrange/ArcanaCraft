package com.tyhone.arcanacraft;

import java.util.HashMap;
import java.util.Map;

import com.tyhone.arcanacraft.common.proxy.CommonProxy;
import com.tyhone.arcanacraft.common.reference.ConfigDefaults;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
	private static final String CATEGORY_GENERAL = "general";
	
	public static boolean testValue = true;

	//QUARRY
	private static final String CATEGORY_QUARRY_VALUES = "quarryOreDictWeights";
	public static Map<String, Integer> quarryOreMap = new HashMap<>();
	public static int quarryOreMapTotalValue = 0;

	//PICKAXE
	private static final String CATEGORY_VALUE_TIERS = "pickaxeOreSense";
	public static Map<Integer, String> pickaxeValueTierMap = new HashMap<>();
	public static Map<String, Integer> pickaxeBlockMap = new HashMap<>();
	public static Map<String, Integer> pickaxeOreMap = new HashMap<>();
	
	
	
	public static void readConfig(){
		Configuration cfg = CommonProxy.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
		} catch (Exception e){
			Arcanacraft.logger.error("Problem loading config file", e);
		} finally {
			if(cfg.hasChanged()){
				cfg.save();
			}
		}
	}

	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
		testValue = cfg.getBoolean("testCFG", CATEGORY_GENERAL, testValue, "Boolean test cfg setting");

		cfg.addCustomCategoryComment(CATEGORY_QUARRY_VALUES, "Quarry Ore weights");
		quarryOreMap = decodeQuarryOreValues(cfg);

		cfg.addCustomCategoryComment(CATEGORY_VALUE_TIERS, "Magic Pickaxe OreSense Ability configuration");
		pickaxeValueTierMap = decodePickaxeValueTiers(cfg);
		pickaxeBlockMap = decodePickaxeBlockValues(cfg);
		pickaxeOreMap = decodePickaxeOreValues(cfg);
	}
	
	private static Map<String, Integer> decodeQuarryOreValues(Configuration cfg){
		Map<String, Integer> map = new HashMap<>();
		
		String[] values = cfg.getStringList(CATEGORY_QUARRY_VALUES+"Name", CATEGORY_QUARRY_VALUES, ConfigDefaults.QUARRY_ORES, "Quarry Ore Weights: [Ore|Weight] (Higher weight means more chance of spawning)");
		if(values.length>0) {
			for(String value : values) {
				String[] parts = value.split("\\|");
				try {
					map.put(parts[0], Integer.parseInt(parts[1]));
				} catch(Exception e) {
					Arcanacraft.log("Error passing in quarryOreDictWeights for: " + value + " : " + e);
				}
			}
		}
		
		return map;
	}
	
	private static Map<Integer, String> decodePickaxeValueTiers(Configuration cfg){
		Map<Integer, String> map = new HashMap<>();
		
		String[] values = cfg.getStringList(CATEGORY_VALUE_TIERS+"ValueTiers", CATEGORY_VALUE_TIERS, ConfigDefaults.PICKAXE_VALUE_TIERS, "Pickaxe Value Tiers: [value|Descriptions]");
		if(values.length>0) {
			for(String value : values) {
				String[] parts = value.split("\\|");
				try {
					map.put(Integer.parseInt(parts[0]), parts[1]);
				} catch(Exception e) {
					Arcanacraft.log("Error passing in pickaxeValueTier for: " + value + " : " + e);
				}
			}
		}
		
		return map;
	}
	
	private static Map<String, Integer> decodePickaxeBlockValues(Configuration cfg){
		Map<String, Integer> map = new HashMap<>();
		
		String[] values = cfg.getStringList(CATEGORY_VALUE_TIERS+"BlockValues", CATEGORY_VALUE_TIERS, ConfigDefaults.PICKAXE_BLOCK_VALUES, "Pickaxe Block Values: [registryName#meta|value]");
		if(values.length>0) {
			for(String value : values) {
				String[] parts = value.split("\\|");
				try {
					if(parts[0].contains("#")) {
						map.put(parts[0], Integer.parseInt(parts[1]));
					}
					else {
						map.put(parts[0] + "#0", Integer.parseInt(parts[1]));
					}
				} catch(Exception e) {
					Arcanacraft.log("Error passing in pickaxeBlockValues for: " + value + " : " + e);
				}
			}
		}
		
		return map;
	}
	
	private static Map<String, Integer> decodePickaxeOreValues(Configuration cfg){
		Map<String, Integer> map = new HashMap<>();
		
		String[] values = cfg.getStringList(CATEGORY_VALUE_TIERS+"OreValues", CATEGORY_VALUE_TIERS, ConfigDefaults.PICKAXE_ORE_VALUES, "Pickaxe OreDictionary Values: [oreDictName|value]");
		if(values.length>0) {
			for(String value : values) {
				String[] parts = value.split("\\|");
				try {
					map.put(parts[0], Integer.parseInt(parts[1]));
				} catch(Exception e) {
					Arcanacraft.log("Error passing in pickaxeOreDictValues for: " + value + " : " + e);
				}
			}
		}
		
		return map;
	}
}
