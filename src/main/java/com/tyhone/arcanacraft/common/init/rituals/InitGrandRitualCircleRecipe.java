package com.tyhone.arcanacraft.common.init.rituals;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModRituals;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class InitGrandRitualCircleRecipe {
	
	public static void initRecipe(){
		initSummonChicken();
	}
	
	private static void initSummonChicken(){
		ArcanacraftRitualCraftingManager.registerGrandRitualCircleRecipe(
				ModRituals.GRAND_RITUAL_SUMMON_CHICKEN,
			new ItemStack[]{
					new ItemStack(ModItems.AYRE, 1)
			},
			new Object[]{
					"K           K",
					"             ",
					"             ",
					"             ",
					"    ZHGHZ    ",
					"    HB BH    ",
					"    G C G    ",
					"    HB BH    ",
					"    ZHGHZ    ",
					"             ",
					"             ",
					"             ",
					"K           K",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'C', new ItemStack(ModBlocks.GRAND_RITUAL_CIRCLE),
					'Z', new ItemStack(Blocks.LAPIS_BLOCK, 1),
					'K', new ItemStack(Blocks.GLOWSTONE, 1)
			});
	}
}
