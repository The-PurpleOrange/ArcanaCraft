package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.api.registries.TinktureStack;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesInfusionAltar {
	public static void init(){
		ItemStack[] items = {new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.GHAST_TEAR), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.GHAST_TEAR), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.GHAST_TEAR), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.GHAST_TEAR)};
		TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.LOCUS, 32), new TinktureStack(ModTinktureTypes.IMPIRUS, 32), new TinktureStack(ModTinktureTypes.REBUS, 32), new TinktureStack(ModTinktureTypes.AMNIS, 32)};
		ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("inert_star")), new ItemStack(ModItems.ALCHEMICAL_COAL, 1), items, tinktures);
	}
}
