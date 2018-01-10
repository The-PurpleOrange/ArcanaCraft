package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesAlchemicArray {
	public static void initAlchemicArrayRecipes(){
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.AYRE, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("bone_ash")), new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.RED_COAL), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.RED_COAL, 1), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.REDSTONE, 4));
	}
}
