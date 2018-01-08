package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesTransmutationAltar {
	public static void initTransmutationAltarRecipes(){
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.ITEM, 4, ItemMetaUtil.item("fey_powder")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("bone_ash")), new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.RED_COAL), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
	}
}
