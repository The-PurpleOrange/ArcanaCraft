package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesDeconstructionTable {
	public static void initDeconstructionTableRecipes(){
		ArcanacraftCraftingManager.registerDeconstructionRecipe(new ItemStack(Items.REDSTONE), new ItemStack(Items.GLOWSTONE_DUST));
		ArcanacraftCraftingManager.registerDeconstructionRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.GOLD_INGOT));
		
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("soul")), new ItemStack(ModItems.SOUL, 1, 0), new ItemStack(Blocks.SOUL_SAND));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("essence")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("iron")), new ItemStack(Items.IRON_INGOT));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("essence")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")), new ItemStack(Items.GOLD_INGOT));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("essence")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("magicite")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")));
		ArcanacraftCraftingManager.registerLensDeconstructionRecipe(new ItemStack(ModItems.LENS, 1, ItemMetaUtil.lens("essence")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("blood_iron")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("blood_iron")));
	}
}
