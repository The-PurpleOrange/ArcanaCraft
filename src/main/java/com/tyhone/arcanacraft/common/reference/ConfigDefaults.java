package com.tyhone.arcanacraft.common.reference;

import com.tyhone.arcanacraft.common.init.ModBlocks;

import net.minecraft.init.Blocks;

public class ConfigDefaults {
	

//AQUARRY CONFIGS
	public static final String[] QUARRY_ORES = {
			"oreEmerald|3",
			"oreDiamond|7",
			"oreRedstone|28",
			"oreLapis|15",
			"oreGold|20",
			"oreIron|55",
			"oreCoal|85"
		};

	
// PICKAXE CONFIGS
	public static final String[] PICKAXE_VALUE_TIERS = { 
			"0|Nothing of value", 
			"16|A tingle of power", 
			"32|a tremor of power", 
			"64|A pulse of power", 
			"96|A hum of power", 
			"128|A strong hum of power", 
			"256|You viabrate with the power", 
			"2056|Uncalulable power" 
		};
	
	public static final String[] PICKAXE_ORE_VALUES = { 
			"oreEmerald|256",
			"oreDiamond|128",
			"oreRedstone|80",
			"oreLapis|112",
			"oreGold|96",
			"oreIron|64",
			"oreCoal|16",
			"oreQuartz|32"
		};
	
	public static final String[] PICKAXE_BLOCK_VALUES = {
			Blocks.GLOWSTONE.getRegistryName() + "|16"
		};
}
