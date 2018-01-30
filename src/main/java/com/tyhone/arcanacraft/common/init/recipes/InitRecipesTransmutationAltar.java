package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesTransmutationAltar {
	public static void init(){
		
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.AYRE, 4), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")), new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.RED_COAL), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(Items.PRISMARINE_SHARD, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")), new ItemStack(ModItems.AYRE, 1), new ItemStack(ModItems.AYRE, 1));

		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.CHALK, 1, ItemMetaUtil.chalk("magicite")), new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("magicite")), new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("magicite")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")));
		
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("ender_tear")), new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.GHAST_TEAR));
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart_ender")), new ItemStack(Items.ENDER_PEARL), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("fragment")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart")));
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heat_core")), new ItemStack(Blocks.OBSIDIAN), new ItemStack(Items.BLAZE_ROD), new ItemStack(Items.BLAZE_ROD), new ItemStack(ModItems.RED_COAL));
		
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.EVOLITE, 1), new ItemStack(Items.MAGMA_CREAM, 1), new ItemStack(Items.PRISMARINE_CRYSTALS), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("magicite")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("blood")));
		
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.TRINKET_MAGNET, 1), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("iron")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")), new ItemStack(ModItems.AYRE, 1));
		//ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("alcharium")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart_ender")), new ItemStack(ModItems.EVOLITE), new ItemStack(ModItems.ALCHEMICAL_COAL), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.item("magicite")));
		
		ArcanacraftCraftingManager.registerTransmutationRecipes(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("emeradine")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("emeradine")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("emeradine")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("emeradine")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("emeradine")));
	}
}
