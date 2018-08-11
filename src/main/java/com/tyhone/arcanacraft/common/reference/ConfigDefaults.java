package com.tyhone.arcanacraft.common.reference;

import com.tyhone.arcanacraft.common.init.ModBlocks;

import net.minecraft.init.Blocks;

public class ConfigDefaults {
	public static final String[] VALUE_TIERS = { 
			"0|Nothing of value", 
			"16|A tingle of power", 
			"32|a tremor of power", 
			"64|A pulse of power", 
			"96|A hum of power", 
			"128|A strong hum of power", 
			"256|You viabrate with the power", 
			"2056|Uncalulable power" 
		};
	
	public static final String[] ORE_VALUES = { 
			"stone|1"
		};
	
	public static final String[] BLOCK_VALUES = { 
			Blocks.EMERALD_ORE.getRegistryName() + "|256", 
			Blocks.EMERALD_ORE.getRegistryName() + "|256", 
			Blocks.EMERALD_ORE.getRegistryName() + "|256", 
			Blocks.EMERALD_ORE.getRegistryName() + "|256", 
			Blocks.EMERALD_ORE.getRegistryName() + "|256", 
			Blocks.DIAMOND_ORE.getRegistryName() + "|128", 
			Blocks.GOLD_ORE.getRegistryName() + "|96", 
			Blocks.LAPIS_ORE.getRegistryName() + "|112", 
			Blocks.REDSTONE_ORE.getRegistryName() + "|80", 
			Blocks.LIT_REDSTONE_ORE.getRegistryName() + "|80", 
			Blocks.IRON_ORE.getRegistryName() + "|64", 
			Blocks.COAL_ORE.getRegistryName() + "|16",
			Blocks.GLOWSTONE.getRegistryName() + "|16",
			Blocks.QUARTZ_ORE.getRegistryName() + "|32",
		};
}
