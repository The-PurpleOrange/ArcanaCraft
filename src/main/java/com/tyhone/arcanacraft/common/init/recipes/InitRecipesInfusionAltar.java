package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;
import com.tyhone.arcanacraft.common.util.WildStack;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesInfusionAltar {
	public static void init(){
		{
			ItemStack[] items = {new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.GHAST_TEAR), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.GHAST_TEAR), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.GHAST_TEAR), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.GHAST_TEAR)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.LOCUS, 32), new TinktureStack(ModTinktureTypes.IMPIRUS, 32), new TinktureStack(ModTinktureTypes.REBUS, 32), new TinktureStack(ModTinktureTypes.AMNIS, 32)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("inert_star")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")), items, tinktures);
		}{
			//ELEVATOR
			Object[] inputs = {new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(ModItems.AYRE, 1), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart_ender")), new ItemStack(ModItems.RED_COAL, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.LOCUS, 2)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModBlocks.ELEVATOR, 2), WildStack.W(ModBlocks.ALCHEMIC_STONE, 1), inputs, tinktures);
		}{
			//CEDIUS
			Object[] inputs = {new ItemStack(ModItems.EVOLITE, 1),new ItemStack(ModItems.EVOLITE, 1),new ItemStack(ModItems.EVOLITE, 1), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.SPECKLED_MELON, 1), new ItemStack(Items.SPECKLED_MELON, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")), new ItemStack(ModItems.AYRE, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.ANIMO, 12), new TinktureStack(ModTinktureTypes.IMPIRUS, 4), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("cedius")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), inputs, tinktures);
		}{
			//AVARICLE
			Object[] inputs = {new ItemStack(Items.EMERALD, 1), new ItemStack(Items.EMERALD, 1), new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("bone_ash")), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")), new ItemStack(ModItems.AYRE, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.REBUS, 12), new TinktureStack(ModTinktureTypes.LOCUS, 4), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("greed")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), inputs, tinktures);
		}{
			//PROSPOROUS
			Object[] inputs = {new ItemStack(Items.DIAMOND, 1), new ItemStack(Items.DIAMOND, 1), new ItemStack(Items.DIAMOND, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")), new ItemStack(Items.GOLDEN_APPLE, 1), new ItemStack(Items.GOLDEN_APPLE, 1), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("magicite")), new ItemStack(ModItems.AYRE, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.AMNIS, 12), new TinktureStack(ModTinktureTypes.ANIMO, 4), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("prosper")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), inputs, tinktures);
		}
	}
}
