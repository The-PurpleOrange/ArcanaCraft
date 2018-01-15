package com.tyhone.arcanacraft.common.handler;

import java.util.List;

import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.util.WildStack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHandler {
	
	public static void registerOreDictionary(){
		OreDictionary.registerOre("dustIron", new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("iron")));
		OreDictionary.registerOre("dustGold", new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("gold")));
		
		//Being Selfish, dont want to repeat recipes
		OreDictionary.registerOre("arcanacraftFruit", Items.APPLE);
		OreDictionary.registerOre("arcanacraftFruit", Items.WHEAT);
		OreDictionary.registerOre("arcanacraftFruit", Items.POTATO);
		OreDictionary.registerOre("arcanacraftFruit", Items.CARROT);
		OreDictionary.registerOre("arcanacraftFruit", Items.NETHER_WART);
		OreDictionary.registerOre("arcanacraftFruit", Blocks.RED_MUSHROOM);
		OreDictionary.registerOre("arcanacraftFruit", Blocks.BROWN_MUSHROOM);
	}
	
	public static ItemStack getOreDictionaryEntry(String string){
		List<ItemStack> list = getOreDictionaryEntries(string);
		if(list.size()>0){
			return list.get(0);
		}
		return ItemStack.EMPTY;
	}
	
	public static List<ItemStack> getOreDictionaryEntries(String string){
		List<ItemStack> ores = OreDictionary.getOres(string, false);
		if(ores.size()>0){
			return ores;
		}
		return null;
	}
}
