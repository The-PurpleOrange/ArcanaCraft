package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesAlchemicArray {
	public static void initAlchemicArrayRecipes(){
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.ITEM, 2, ItemMetaUtil.item("fey_powder")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("bone_ash")), new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.RED_COAL), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
	}
}
