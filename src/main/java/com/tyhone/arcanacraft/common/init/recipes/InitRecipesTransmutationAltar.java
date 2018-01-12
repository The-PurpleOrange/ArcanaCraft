package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesTransmutationAltar {
	public static void init(){
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.AYRE, 4), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("bone_ash")), new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.RED_COAL), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.PRISMARINE_SHARD, 1), new ItemStack(ModItems.AYRE, 1), new ItemStack(ModItems.AYRE, 1), new ItemStack(ModItems.AYRE, 1));
	}
}
