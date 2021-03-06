package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;
import com.tyhone.arcanacraft.common.util.WildStack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesInfusionAltar {
	public static void init(){
		/*{
			//INERT STAR
			ItemStack[] items = {new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("catalyst")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("catalyst")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("catalyst")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("catalyst"))};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.LOCUS, 8), new TinktureStack(ModTinktureTypes.IMPIRUS, 8), new TinktureStack(ModTinktureTypes.REBUS, 8), new TinktureStack(ModTinktureTypes.AMNIS, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("inert_star")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")), items, tinktures);
		}*/{
			//ELEVATOR
			Object[] inputs = {new ItemStack(ModItems.AYRE, 1), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")), new ItemStack(ModItems.GEARBOX, 1, ItemMetaUtil.gearbox("advanced")), new ItemStack(ModItems.RED_COAL, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.LOCUS, 2)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModBlocks.ELEVATOR, 2), WildStack.W(ModBlocks.ALCHEMIC_STONE, 1), inputs, tinktures);
		}{
			//NETHER
			Object[] inputs = {new ItemStack(Items.BLAZE_ROD, 1), new ItemStack(Items.BLAZE_ROD, 1), new ItemStack(Items.BLAZE_ROD, 1), new ItemStack(Items.NETHERBRICK, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heat_core")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heat_core")), new ItemStack(Items.NETHER_WART, 1), new ItemStack(ModItems.AYRE, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.IMPIRUS, 12), new TinktureStack(ModTinktureTypes.LOCUS, 4), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("nether")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), inputs, tinktures);
		}{
			//ELDRITCH
			Object[] inputs = {new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(Items.ENDER_PEARL, 1), new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("ender")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart_ender")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart_ender")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("ender_tear")), new ItemStack(ModItems.AYRE, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.LOCUS, 12), new TinktureStack(ModTinktureTypes.REBUS, 4), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("eldritch")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), inputs, tinktures);
		}{
			//CEDIUS
			Object[] inputs = {new ItemStack(ModItems.EVOLITE, 1),new ItemStack(ModItems.EVOLITE, 1),new ItemStack(ModItems.EVOLITE, 1), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.SPECKLED_MELON, 1), new ItemStack(Items.SPECKLED_MELON, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")), new ItemStack(ModItems.AYRE, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.ANIMO, 12), new TinktureStack(ModTinktureTypes.IMPIRUS, 4), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("cedius")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), inputs, tinktures);
		}{
			//AVARICLE
			Object[] inputs = {new ItemStack(Items.EMERALD, 1), new ItemStack(Items.EMERALD, 1), new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("bone_ash")), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")), new ItemStack(ModItems.AYRE, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.REBUS, 12), new TinktureStack(ModTinktureTypes.AMNIS, 4), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("greed")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), inputs, tinktures);
		}{
			//PROSPOROUS
			Object[] inputs = {new ItemStack(Items.DIAMOND, 1), new ItemStack(Items.DIAMOND, 1), new ItemStack(Items.DIAMOND, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")), new ItemStack(Items.GOLDEN_APPLE, 1), new ItemStack(Items.GOLDEN_APPLE, 1), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("magicite")), new ItemStack(ModItems.AYRE, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.AMNIS, 12), new TinktureStack(ModTinktureTypes.ANIMO, 4), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("prosper")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), inputs, tinktures);
		}{
			//HUNGER CHARM
			Object[] inputs = {new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("cedius")), new ItemStack(Items.ENDER_PEARL), WildStack.W(ModBlocks.ALCHEMIC_STONE, 1), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("Alcharium"))};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.ANIMO, 16), new TinktureStack(ModTinktureTypes.VOIDUS, 8)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.TRINKET_HUNGER_CHARM, 1), new ItemStack(ModItems.STAR, 1, ItemMetaUtil.item("mundane")), inputs, tinktures);
		}{
			//ALCHARIUM
			Object[] inputs = {new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("cedius")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("greed")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("nether")), new ItemStack(ModItems.ALCHEMICAL_COAL, 1), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("eldritch")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("emeradine")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("cedius")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("greed"))};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.LOCUS, 16), new TinktureStack(ModTinktureTypes.IMPIRUS, 16), new TinktureStack(ModTinktureTypes.REBUS, 16), new TinktureStack(ModTinktureTypes.AMNIS, 16)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("alcharium")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), inputs, tinktures);
		}{
			//HOVER CHARM
			Object[] inputs = {new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("eldritch")), new ItemStack(ModItems.ALCHEMICAL_COAL, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("emeradine")), new ItemStack(ModItems.ALCHEMICAL_COAL, 1), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("blood_iron")), new ItemStack(ModItems.ALCHEMICAL_COAL, 1), new ItemStack(ModItems.EVOLITE, 1), new ItemStack(ModItems.ALCHEMICAL_COAL, 1)};
			TinktureStack[] tinktures = {new TinktureStack(ModTinktureTypes.LOCUS, 24), new TinktureStack(ModTinktureTypes.AMNIS, 16)};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(ModItems.TRINKET_HOVER_CHARM, 1), new ItemStack(Items.FEATHER, 1), inputs, tinktures);
		}{
			//NOTCH APPLE
			Object[] inputs = new Object[8];
			for(int i = 0; i < inputs.length; i++){
				inputs[i] = new ItemStack(Blocks.GOLD_BLOCK, 1);
			}
			TinktureStack[] tinktures = {null};
			ArcanacraftCraftingManager.registerInfusionRecipe(new ItemStack(Items.GOLDEN_APPLE, 1, 1), new ItemStack(Items.APPLE, 1), inputs, tinktures);
		}
	}
}
