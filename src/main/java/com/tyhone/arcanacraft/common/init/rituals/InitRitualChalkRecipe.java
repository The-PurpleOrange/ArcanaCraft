package com.tyhone.arcanacraft.common.init.rituals;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModRituals;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.item.ItemStack;

public class InitRitualChalkRecipe {
	
	public static void initRecipe(){
		initChalk(ModRituals.CHALK_CHARCOAL);
		initChalk(ModRituals.CHALK_BONE);
		initChalk(ModRituals.CHALK_BLOOD);
		initChalk(ModRituals.CHALK_LAPIS);
		initChalk(ModRituals.CHALK_GOLD);
		initChalk(ModRituals.CHALK_MAGICITE);
	}
	

	private static void initChalk(Ritual ritual){
		ArcanacraftRitualCraftingManager.registerRitualChalkRecipe(
			ritual,
			new ItemStack[]{
					ItemStack.EMPTY
			},
			new Object[]{
					"C",
					'C', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk(ritual.getNameShort()))
			});
	}
}
