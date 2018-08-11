package com.tyhone.arcanacraft.common.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.Config;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class PickaxeOreValues {
	public static Map<String, Integer> oreMap = new HashMap<>();
	//public static Map<String, Integer> blockMap = new HashMap<>();
	
	
	public static void init() {
		//initValueTiers();
		//initBlock();
		initOre();
	}
	
	public static void initOre() {
		oreMap.put("stone", 1);
	}
	
	/*public static void initBlock() {
		blockMap.put(Blocks.EMERALD_ORE.getUnlocalizedName(), 256);
		blockMap.put(Blocks.DIAMOND_ORE.getUnlocalizedName(), 128);
		blockMap.put(Blocks.GOLD_ORE.getUnlocalizedName(), 96);
		blockMap.put(Blocks.REDSTONE_ORE.getUnlocalizedName(), 80);
		blockMap.put(Blocks.LIT_REDSTONE_ORE.getUnlocalizedName(), 80);
		blockMap.put(Blocks.IRON_ORE.getUnlocalizedName(), 64);
		blockMap.put(Blocks.COAL_ORE.getUnlocalizedName(), 16);
	}*/
	
	public static int compareAgainstBlockMap(IBlockState blockState) {
		Block block = blockState.getBlock();
		int meta = block.getMetaFromState(blockState);
		
		if(Config.blockMap.containsKey(block.getRegistryName().toString() + "#" + meta)) {
			return Config.blockMap.get(block.getRegistryName().toString() + "#" + meta);
		}
		return 0;
	}
	
	public static String getValueDescription(int value) {
		if(Config.valueTierMap.containsKey(value)) {
			return Config.valueTierMap.get(value);
		}
		return "NULL VALUE KEY FOUND";
	}
	
	public static int compareAgainstOreMap() {
		return 0;
	}
	
	public static int getHighestValue(int value) {
		int highestValue = 0;
		for(Entry<Integer, String> entry : Config.valueTierMap.entrySet()) {
			if(value >= entry.getKey() && highestValue < entry.getKey()){
				highestValue = entry.getKey();
			}
		}
		return highestValue;
	}
}
