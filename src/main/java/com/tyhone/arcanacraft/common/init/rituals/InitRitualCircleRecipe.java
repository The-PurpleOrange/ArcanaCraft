package com.tyhone.arcanacraft.common.init.rituals;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModRituals;
import com.tyhone.arcanacraft.common.rituals.RitualClearWeather;
import com.tyhone.arcanacraft.common.rituals.RitualSummonLightning;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRitualCircleRecipe {
	
	public static void initRecipe(){
		initSummonRain();
		initSummonLightning();
		initClearWeather();
	}
	
	private static void initSummonRain(){
		ArcanacraftRitualCraftingManager.registerRitualCircleRecipe(
				ModRituals.RITUAL_SUMMON_RAIN,
			new ItemStack[]{
					new ItemStack(Items.DYE, 1, 4)
			},
			new Object[]{
					" HGH ",
					"HB BH",
					"G C G",
					"HB BH",
					" HGH ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'C', new ItemStack(ModBlocks.RITUAL_CIRCLE)
			});
	}
	
	private static void initClearWeather(){
		ArcanacraftRitualCraftingManager.registerRitualCircleRecipe(
			ModRituals.RITUAL_CLEAR_WEATHER,
			new ItemStack[]{
					new ItemStack(Blocks.GLASS, 1)
			},
			new Object[]{
					" HGH ",
					"HB BH",
					"G C G",
					"HB BH",
					" HGH ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'C', new ItemStack(ModBlocks.RITUAL_CIRCLE)
			});
	}
	
	private static void initSummonLightning(){
		ArcanacraftRitualCraftingManager.registerRitualCircleRecipe(
			ModRituals.RITUAL_SUMMON_LIGHTNING,
			new ItemStack[]{
					new ItemStack(Items.GLOWSTONE_DUST, 1)
			},
			new Object[]{
					" HGH ",
					"HB BH",
					"G C G",
					"HB BH",
					" HGH ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'C', new ItemStack(ModBlocks.RITUAL_CIRCLE)
			});
	}
}
