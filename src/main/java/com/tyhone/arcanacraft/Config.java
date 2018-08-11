package com.tyhone.arcanacraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tyhone.arcanacraft.common.proxy.CommonProxy;
import com.tyhone.arcanacraft.common.reference.ConfigDefaults;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
	private static final String CATEGORY_GENERAL = "general";
	
	public static boolean testValue = true;
	


	private static final String CATEGORY_VALUE_TIERS = "valueTiers";
	public static Map<Integer, String> valueTierMap = new HashMap<>();
	
	private static final String CATEGORY_BLOCK_VALUES = "blockValues";
	public static Map<String, Integer> blockMap = new HashMap<>();
	
	private static final String CATEGORY_ORE_VALUES = "oreValues";
	public static Map<String, Integer> oreMap = new HashMap<>();
	
	
	
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
		

		cfg.addCustomCategoryComment(CATEGORY_VALUE_TIERS, "Pickaxe sense value tiers");
		valueTierMap = decodeValueTiers(cfg);
		cfg.addCustomCategoryComment(CATEGORY_BLOCK_VALUES, "Pickaxe sense Block values");
		blockMap = decodeBlockValues(cfg);
		cfg.addCustomCategoryComment(CATEGORY_ORE_VALUES, "Pickaxe sense OreDict values");
	}
	
	private static Map<Integer, String> decodeValueTiers(Configuration cfg){
		Map<Integer, String> map = new HashMap<>();
		
		String[] values = cfg.getStringList("pickaxeValueTiers", CATEGORY_VALUE_TIERS, ConfigDefaults.VALUE_TIERS, "Pickaxe Value Tiers: [value|Descriptions]");
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
	
	private static Map<String, Integer> decodeBlockValues(Configuration cfg){
		Map<String, Integer> map = new HashMap<>();
		
		String[] values = cfg.getStringList("pickaxeBlockValues", CATEGORY_BLOCK_VALUES, ConfigDefaults.BLOCK_VALUES, "Pickaxe Block Values: [registryName#meta|value]");
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
	
	private static Map<String, Integer> decodeOreValues(Configuration cfg){
		Map<String, Integer> map = new HashMap<>();
		
		String[] values = cfg.getStringList("pickaxeOreDictValues", CATEGORY_ORE_VALUES, ConfigDefaults.ORE_VALUES, "Pickaxe OreDictionary Values: [oreDictName|value]");
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
