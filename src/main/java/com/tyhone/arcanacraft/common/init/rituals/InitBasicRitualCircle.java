package com.tyhone.arcanacraft.common.init.rituals;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualManager;
import com.tyhone.arcanacraft.api.registries.RitualRegistry;
import com.tyhone.arcanacraft.api.ritual.RitualBase;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.rituals.RitualClearWeather;
import com.tyhone.arcanacraft.common.rituals.RitualSummonRain;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitBasicRitualCircle {

	private static final RitualBase RitualSummonRain = new RitualSummonRain();
	private static final RitualBase RitualClearWeather = new RitualClearWeather();

	public static void register(){
		initSummonRain();
		initClearWeather();
	}
	
	private static void initSummonRain(){
		ArcanacraftRitualManager.registerRitualCircle(
				RitualSummonRain.getRitualName(),
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
		
		RitualRegistry.registerRitual(RitualSummonRain);
		
	}
	
	private static void initClearWeather(){
		ArcanacraftRitualManager.registerRitualCircle(
			RitualClearWeather.getRitualName(),
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
		
		RitualRegistry.registerRitual(RitualClearWeather);
		
	}
}
