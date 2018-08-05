package com.tyhone.arcanacraft.common.init.rituals;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftRitualCraftingManager;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModRituals;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitGrandRitualCircleRecipe {
	
	public static void initRecipe(){
		initSummonChicken();
		initCreateHomunculus();
		initWhy();
	}
	
	private static void initCreateHomunculus(){
		ArcanacraftRitualCraftingManager.registerGrandRitualCircleRecipe(
				ModRituals.GRAND_RITUAL_CREATE_HOMUNCULUS,
			new ItemStack[]{
					new ItemStack(ModBlocks.FLESHY_BLOCK, 1),
					new ItemStack(Items.BONE, 3),
					new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop"))
			},
			new Object[]{
					"             ",
					"    F   F    ",
					"  Q  RRR  Q  ",
					"   GO M OG   ",
					" F OGBBBGO F ",
					"  R BG GB R  ",
					"  RMB C BMR  ",
					"  R BG GB R  ",
					" F OGBBBGO F ",
					"   GO M OG   ",
					"  Q  RRR  Q  ",
					"    F   F    ",
					"             ",
					'C', new ItemStack(ModBlocks.GRAND_RITUAL_CIRCLE),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("blood")),
					'O', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'R', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("magicite")),
					'M', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("gold")),
					'Q', new ItemStack(Blocks.BONE_BLOCK, 1),
					'F', new ItemStack(ModBlocks.FLESHY_BLOCK, 1)
			});
	}
	
	private static void initSummonChicken(){
		ArcanacraftRitualCraftingManager.registerGrandRitualCircleRecipe(
				ModRituals.GRAND_RITUAL_SUMMON_CHICKEN,
			new ItemStack[]{
					new ItemStack(Items.BONE, 2),
					new ItemStack(ModItems.ITEM, 2, ItemMetaUtil.item("clean_flesh")),
					new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")),
			},
			new Object[]{
					"      K      ",
					" K         K ",
					"             ",
					"             ",
					"    ZHGHZ    ",
					"    HB BH    ",
					"K   G C G   K",
					"    HB BH    ",
					"    ZHGHZ    ",
					"             ",
					"             ",
					" K         K ",
					"      K      ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'C', new ItemStack(ModBlocks.GRAND_RITUAL_CIRCLE),
					'Z', new ItemStack(Blocks.LAPIS_BLOCK, 1),
					'K', new ItemStack(Blocks.GLOWSTONE, 1)
			});
	}
	
	private static void initWhy(){
		ArcanacraftRitualCraftingManager.registerGrandRitualCircleRecipe(
				ModRituals.GRAND_RITUAL_WHY,
			new ItemStack[]{
					new ItemStack(ModItems.AYRE, 1)
			},
			new Object[]{
					"      K      ",
					" K         K ",
					"             ",
					"             ",
					"    ZHGHZ    ",
					"    HB BH    ",
					"K   G C G   K",
					"    HB BH    ",
					"    ZHGHZ    ",
					"             ",
					"             ",
					" K         K ",
					"      K      ",
					'H', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("bone")),
					'B', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("charcoal")),
					'G', new ItemStack(ModBlocks.CHALK_BLOCK, 1, ItemMetaUtil.chalk("lapis")),
					'C', new ItemStack(ModBlocks.GRAND_RITUAL_CIRCLE),
					'Z', new ItemStack(ModBlocks.FLESHY_BLOCK, 1),
					'K', new ItemStack(Blocks.GLOWSTONE, 1)
			});
	}
}
