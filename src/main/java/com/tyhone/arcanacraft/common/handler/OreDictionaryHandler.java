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
	
	/*public static boolean compareOreDictionaryEntry(ItemStack stack, String string){
		if(!stack.isEmpty()){
			List<ItemStack> oreList = getOreDictionaryEntries(string);
			if(oreList.size()>0){
				for(ItemStack oreStack : oreList){
					if(ItemStackUtil.simpleAreStacksEqual(stack, oreStack)){
						return true;
					}
				}
			}
		}
		
		return false;
	}*/
}
