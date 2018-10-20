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
			"oreEmerald|256",
			"oreDiamond|128",
			"oreRedstone|80",
			"oreLapis|112",
			"oreGold|96",
			"oreIron|64",
			"oreCoal|16",
			"oreQuartz|32"
		};
	
	public static final String[] BLOCK_VALUES = {
			Blocks.GLOWSTONE.getRegistryName() + "|16"
		};
}
