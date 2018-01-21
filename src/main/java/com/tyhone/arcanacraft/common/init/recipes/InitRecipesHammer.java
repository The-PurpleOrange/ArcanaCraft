package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesHammer {
	public static void init(){

		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("charcoal")), new ItemStack(Items.COAL, 1, 1));
		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(ModItems.MULCH, 1), new OreStack("logWood", 1));

		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")), new ItemStack(Items.QUARTZ, 1));
		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("bone")), new ItemStack(Items.BONE));
		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(Items.DYE, 4, 15), new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("bone")));
		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(ModItems.NEEDLE, 4, 1), new ItemStack(Blocks.CACTUS));
		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("blood")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("blood")));
	}
}
