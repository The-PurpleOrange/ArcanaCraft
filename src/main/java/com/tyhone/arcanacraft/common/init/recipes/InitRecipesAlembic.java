package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesAlembic {
	public static void init(){

		ArcanacraftCraftingManager.registerAlembicRecipe(new ItemStack(Items.APPLE),  null, new ItemStack(Items.ARROW), new ItemStack(Items.BEETROOT));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.IMPIRUS),  null, new ItemStack(ModItems.RED_COAL), new ItemStack(Items.BLAZE_ROD));
	}
}
