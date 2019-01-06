package com.tyhone.arcanacraft.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHandler {
	
	public static void registerOreDictionary(){
		OreDictionary.registerOre("dustIron", new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("iron")));
		OreDictionary.registerOre("dustGold", new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("gold")));
		OreDictionary.registerOre("dustQuartz", new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")));
		OreDictionary.registerOre("dustEnder", new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("ender")));
		
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
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return ItemStack.EMPTY;
	}
	
	public static Block getBlockFromOreDictionaryEntry(String string){
		ItemStack itemStack = getOreDictionaryEntry(string);
		if(itemStack.getItem() instanceof ItemBlock) {
			return Block.getBlockFromItem(itemStack.getItem());
		}
		return Blocks.AIR;
	}
	
	public static List<ItemStack> getOreDictionaryEntries(String string){
		List<ItemStack> ores = OreDictionary.getOres(string, false);
		if(ores != null && ores.size()>0){
			return ores;
		}
		return null;
	}
}
