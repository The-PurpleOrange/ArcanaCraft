package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesHammer {
	public static void init(){
		
		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("charcoal")), new ItemStack(Items.COAL, 1, 1));
		
		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("bone")), new ItemStack(Items.BONE));
		ArcanacraftCraftingManager.registerHammerRecipe(new ItemStack(Items.DYE, 4, 15), new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("bone")));
	}
}
