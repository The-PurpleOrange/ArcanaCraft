package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitVanillaRecipesSmelting {

	public static void init(){
		GameRegistry.addSmelting(new ItemStack(Items.BONE), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("bone_ash")), 0F);
		GameRegistry.addSmelting(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("iron")), new ItemStack(Items.IRON_INGOT), 0.7F);
		GameRegistry.addSmelting(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("gold")), new ItemStack(Items.GOLD_INGOT), 1F);
		GameRegistry.addSmelting(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("magicite")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), 1F);
	}
}
