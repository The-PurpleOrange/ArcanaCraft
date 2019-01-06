package com.tyhone.arcanacraft.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tyhone.arcanacraft.Config;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class PickaxeOreValues {
	//public static Map<String, Integer> oreMap = new HashMap<>();
	//public static Map<String, Integer> blockMap = new HashMap<>();
	
	public static int compareAgainstOreMap(IBlockState blockState) {
		if(!Config.pickaxeOreMap.isEmpty()) {
			ItemStack blockStack = new ItemStack(blockState.getBlock(), 1, blockState.getBlock().getMetaFromState(blockState));
			for(Entry<String, Integer> entry : Config.pickaxeOreMap.entrySet()) {
				NonNullList<ItemStack> itemStackList = OreDictionary.getOres(entry.getKey(), false);
				for(ItemStack itemStack : itemStackList) {
					if(ItemStackUtil.simpleAreItemStacksEqual(blockStack, itemStack, false)) {
						return entry.getValue();
					}
				}
			}
		}
		
		return 0;
	}
	
	public static int compareAgainstBlockMap(IBlockState blockState) {
		Block block = blockState.getBlock();
		int meta = block.getMetaFromState(blockState);
		
		if(Config.pickaxeBlockMap.containsKey(block.getRegistryName().toString() + "#" + meta)) {
			return Config.pickaxeBlockMap.get(block.getRegistryName().toString() + "#" + meta);
		}
		return 0;
	}
	
	public static String getValueDescription(int value) {
		if(Config.pickaxeValueTierMap.containsKey(value)) {
			return Config.pickaxeValueTierMap.get(value);
		}
		return "NULL VALUE KEY FOUND";
	}
	
	public static int getHighestValue(int value) {
		int highestValue = 0;
		for(Entry<Integer, String> entry : Config.pickaxeValueTierMap.entrySet()) {
			if(value >= entry.getKey() && highestValue < entry.getKey()){
				highestValue = entry.getKey();
			}
		}
		return highestValue;
	}
}
