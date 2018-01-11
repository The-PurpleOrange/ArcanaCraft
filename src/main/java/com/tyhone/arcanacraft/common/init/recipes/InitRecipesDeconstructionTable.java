package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesDeconstructionTable {
	public static void initDeconstructionTableRecipes(){
		ArcanacraftCraftingManager.registerDeconstructionRecipe(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("clean_flesh")), new ItemStack(Items.ROTTEN_FLESH, 1));

		ArcanacraftCraftingManager.registerDeconstructionRecipe(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("iron")), new ItemStack(Items.IRON_INGOT));
		ArcanacraftCraftingManager.registerDeconstructionRecipe(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("gold")), new ItemStack(Items.GOLD_INGOT));
		ArcanacraftCraftingManager.registerDeconstructionRecipe(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("magicite")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")));
		
		
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("soul")), new ItemStack(ModItems.SOUL, 1, 0), new ItemStack(Blocks.SOUL_SAND));
		
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("essence")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("iron")), new ItemStack(Items.IRON_INGOT));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("essence")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")), new ItemStack(Items.GOLD_INGOT));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("essence")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("magicite")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("essence")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("nether")), new ItemStack(Items.BLAZE_POWDER, 1));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("essence")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("eldritch")), new ItemStack(Items.ENDER_PEARL, 1));
	}
}
