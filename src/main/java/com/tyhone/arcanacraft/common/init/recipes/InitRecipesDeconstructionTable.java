package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesDeconstructionTable {
	public static void initDeconstructionTableRecipes(){
		ArcanacraftCraftingManager.registerDeconstructionRecipe(new ItemStack(Items.REDSTONE), new ItemStack(Items.GLOWSTONE_DUST));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(Items.APPLE), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.GOLD_INGOT));
	}
}
