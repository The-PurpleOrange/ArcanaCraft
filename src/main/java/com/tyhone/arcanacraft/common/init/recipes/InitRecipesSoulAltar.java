package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesSoulAltar {
	public static void init(){
		{
			

			ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(Items.APPLE), new OreStack("dustGold"), new OreStack("logWood"));
			
			{
				ItemStack item = new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("fragment"));
				ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("complete")), new ItemStack(ModBlocks.ALCHEMIC_GLASS, 1), item, item, item, item, item);
			}{
				ItemStack item = new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("nether"));
				ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("nether")), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("complete")), item, item, item, item, item);
			}{
				ItemStack item = new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("eldritch"));
				ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("eldritch")), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("complete")), item, item, item, item, item);
			}
			
			ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(Items.NETHER_STAR, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("inert_star")), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("nether")));
			ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModItems.STAR, 1, ItemMetaUtil.star("eldritch")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("inert_star")), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("eldritch")));
		
		}
	}
}
