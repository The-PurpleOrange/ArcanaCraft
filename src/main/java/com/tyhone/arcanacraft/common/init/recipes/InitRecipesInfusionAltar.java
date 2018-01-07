package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.api.registries.TinktureStack;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class InitRecipesInfusionAltar {
	public static void initInfusionAltarRecipes(){
		ItemStack[] items = {new ItemStack(Blocks.SOUL_SAND)};
		TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.LOCUS, 10), new TinktureStack(ModTinktureTypes.IMPIRUS, 10)};
		ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("raging")), new ItemStack(Blocks.GLASS), items, tinktures);
	}
}
