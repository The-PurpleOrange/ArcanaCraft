package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;
import com.tyhone.arcanacraft.common.util.WildStack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class InitRecipesAlchemicArray {
	public static void init(){
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModBlocks.ALCHEMIC_STONE, 8), new ItemStack(Blocks.STONE, 8), new ItemStack(ModItems.AYRE, 1));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModBlocks.ALCHEMIC_GLASS, 8), new ItemStack(Blocks.GLASS, 8), new ItemStack(ModItems.AYRE, 1), new ItemStack(Items.GOLD_INGOT, 1));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.AYRE, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("bone_ash")), new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.RED_COAL), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.RED_COAL, 1), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.REDSTONE, 4));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.LEATHER, 1), new ItemStack(ModItems.ITEM, 2, ItemMetaUtil.item("clean_flesh")));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.MULCH, 4), new ItemStack(Items.REEDS, 3), new ItemStack(Blocks.DIRT, 1, 0));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.MULCH, 4), WildStack.W(Blocks.SAPLING, 3), new ItemStack(Blocks.DIRT, 1, 0));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.MULCH, 4), WildStack.W(Blocks.LEAVES, 3), new ItemStack(Blocks.DIRT, 1, 0));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.MULCH, 4), WildStack.W(Blocks.LEAVES2, 3), new ItemStack(Blocks.DIRT, 1, 0));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack (Items.IRON_INGOT), new ItemStack(ModItems.AYRE, 1));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.GOLD_INGOT, 2), new OreStack("dustGold", 2));
		
		//Chalk
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.CHALK_METAMORPHIC, 1),
				new ItemStack(ModItems.CHALK, 1, 0),
				new ItemStack(ModItems.CHALK, 1, 1),
				new ItemStack(ModItems.CHALK, 1, 2),
				new ItemStack(ModItems.CHALK, 1, 3),
				new ItemStack(ModItems.CHALK, 1, 4),
				new ItemStack(ModItems.CHALK, 1, 5),
				new ItemStack(ModItems.RED_COAL, 1),
				new ItemStack(ModItems.AYRE, 2),
				new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard"))
				);
	}
}
