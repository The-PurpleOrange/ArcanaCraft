package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesSoulAltar {
	public static void init(){
		{
			
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
			
			ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart_ender")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart")), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("complete")), new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("arcane")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("evolite_sap")));
			
			ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModItems.INGOT, 4, ItemMetaUtil.ingot("blood_iron")), new ItemStack(Blocks.IRON_BLOCK, 1), new ItemStack(ModItems.CRYSTAL, 1, 0), new ItemStack(ModItems.CRYSTAL, 1, 0), new ItemStack(ModItems.CRYSTAL, 1, 0), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("fragment")), new ItemStack(ModItems.AYRE, 1));
			
			ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(Items.NETHER_STAR, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("inert_star")), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("nether")), new ItemStack(ModItems.EVOLITE, 1), new ItemStack(ModItems.EVOLITE, 1), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("alcharium")), new ItemStack(ModItems.ALCHEMICAL_COAL, 1));
			ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModItems.STAR, 1, ItemMetaUtil.star("eldritch")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("inert_star")), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("eldritch")), new ItemStack(ModItems.EVOLITE, 1), new ItemStack(ModItems.EVOLITE, 1), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("alcharium")), new ItemStack(ModItems.ALCHEMICAL_COAL, 1));
			ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModItems.STAR, 1, ItemMetaUtil.star("mundane")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("inert_star")), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("complete")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("catalyst")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("catalyst")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("arcane")), new ItemStack(ModItems.RED_COAL, 1));
			
			
			//ArcanacraftCraftingManager.registerSoulInfusionRecipe(new ItemStack(ModBlocks.INFUSION_ALTAR, 1), new ItemStack(ModBlocks.TRANSMUTATION_ALTAR, 1), ItemMetaUtil.item(name));
		}
	}
}
