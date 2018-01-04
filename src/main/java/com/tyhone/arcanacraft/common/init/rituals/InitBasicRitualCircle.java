package com.tyhone.arcanacraft.common.init.rituals;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualManager;
import com.tyhone.arcanacraft.api.registries.RitualRegistry;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.rituals.RitualSummonRain;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.item.ItemStack;

public class InitBasicRitualCircle {

	public static void register(){
		initWeatherRituals();
	}
	
	private static void initWeatherRituals(){
		ArcanacraftRitualManager.registerRitualCircle(
			"summon_rain",
			new ItemStack[]{
					new ItemStack(ModItems.RED_COAL, 1)
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
		
		RitualRegistry.registerRitual("summon_rain", RitualSummonRain.class);
		
	}
}
