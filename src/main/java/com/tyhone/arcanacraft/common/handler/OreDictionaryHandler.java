package com.tyhone.arcanacraft.common.handler;

import java.util.List;

import com.tyhone.arcanacraft.api.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.WildStack;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHandler {
	
	public static void registerOreDictionary(){
		OreDictionary.registerOre("dustIron", new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("iron")));
		OreDictionary.registerOre("dustGold", new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("gold")));
	}
	
	public static ItemStack getOreDictionaryEntry(String string){
		List<ItemStack> ores = OreDictionary.getOres(string, true);
		if(ores.size()>0){
			return ores.get(0);
		}
		return null;
	}
	
	public static List<ItemStack> getOreDictionaryEntries(String string){
		List<ItemStack> ores = OreDictionary.getOres(string, true);
		if(ores.size()>0){
			return ores;
		}
		return null;
	}
	
	public static boolean compareOreDictionaryEntry(Object object, String string){
		ItemStack stack = WildStack.ObjectToInput(object);
		if(!stack.isEmpty()){
			List<ItemStack> oreList = getOreDictionaryEntries(string);
			for(ItemStack oreStack : oreList){
				if(ItemStackUtil.simpleAreStacksEqual(stack, oreStack)){
					return true;
				}
			}
		}
		
		return false;
	}
}
