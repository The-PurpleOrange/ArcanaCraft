package com.tyhone.arcanacraft.common.init.rituals;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModRituals;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class InitRitualCircleRecipe {
	
	public static void initRecipe(){
		initSummonRain();
		initSummonLightning();
		initClearWeather();
		initWhy();
		initGrowth();
	}
	
	private static void initSummonRain(){
		ArcanacraftRitualCraftingManager.registerRitualCircleRecipe(
				ModRituals.RITUAL_SUMMON_RAIN,
			new ItemStack[]{
					new ItemStack(ModItems.AYRE, 1)
			},
			new Object[]{
					"ZHGHZ",
					"HB BH",
					"G C G",
					"HB BH",
					"ZHGHZ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'C', new ItemStack(ModBlocks.RITUAL_CIRCLE),
					'Z', new ItemStack(Blocks.LAPIS_BLOCK, 1)
			});
	}
	
	private static void initClearWeather(){
		ArcanacraftRitualCraftingManager.registerRitualCircleRecipe(
			ModRituals.RITUAL_CLEAR_WEATHER,
			new ItemStack[]{
					new ItemStack(ModItems.AYRE, 1)
			},
			new Object[]{
					"ZHGHZ",
					"HB BH",
					"G C G",
					"HB BH",
					"ZHGHZ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'C', new ItemStack(ModBlocks.RITUAL_CIRCLE),
					'Z', new ItemStack(Blocks.GLASS, 1)
			});
	}
	
	private static void initSummonLightning(){
		ArcanacraftRitualCraftingManager.registerRitualCircleRecipe(
			ModRituals.RITUAL_SUMMON_LIGHTNING,
			new ItemStack[]{
					new ItemStack(ModItems.AYRE, 1)
			},
			new Object[]{
					"ZHGHZ",
					"HB BH",
					"G C G",
					"HB BH",
					"ZHGHZ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'C', new ItemStack(ModBlocks.RITUAL_CIRCLE),
					'Z', new ItemStack(Blocks.GLOWSTONE, 1)
			});
	}
	
	private static void initWhy(){
		ArcanacraftRitualCraftingManager.registerRitualCircleRecipe(
				ModRituals.RITUAL_WHY,
			new ItemStack[]{
					new ItemStack(ModItems.AYRE, 1)
			},
			new Object[]{
					"ZHGHZ",
					"HB BH",
					"G C G",
					"HB BH",
					"ZHGHZ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("blood")),
					'C', new ItemStack(ModBlocks.RITUAL_CIRCLE),
					'Z', new ItemStack(ModBlocks.FLESHY_BLOCK, 1)
			});
	}
	
	private static void initGrowth(){
		ArcanacraftRitualCraftingManager.registerRitualCircleRecipe(
				ModRituals.RITUAL_GROWTH,
			new ItemStack[]{
					new ItemStack(ModItems.AYRE, 1)
			},
			new Object[]{
					"ZHGHZ",
					"HB BH",
					"G C G",
					"HB BH",
					"ZHGHZ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("magicite")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("green")),
					'C', new ItemStack(ModBlocks.RITUAL_CIRCLE),
					'Z', new ItemStack(Blocks.LEAVES, 1)
			});
	}
}
