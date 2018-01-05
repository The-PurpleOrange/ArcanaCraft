package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesSoulAltar {
	public static void initSoulAltarRecipes(){
		ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("complete")), new ItemStack(Blocks.GLASS), new ItemStack(Blocks.SOUL_SAND), new ItemStack(Blocks.SOUL_SAND), new ItemStack(Blocks.SOUL_SAND), new ItemStack(Blocks.SOUL_SAND), new ItemStack(Blocks.SOUL_SAND));
	}
}
