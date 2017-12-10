package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesDeconstructionTable {
	public static void initDeconstructionTableRecipes(){
		ArcanacraftCraftingManager.registerDeconstructionRecipe(new ItemStack(Items.REDSTONE), new ItemStack(Items.GLOWSTONE_DUST));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, 0), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.GOLD_INGOT));
	}
}
