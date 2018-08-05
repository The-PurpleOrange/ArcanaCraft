package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class InitRecipesAlembic {
	public static void init(){

		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.AQUA_REGIA), new FluidStack(FluidRegistry.WATER, 1000), new ItemStack(Items.GUNPOWDER), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new TinktureStack(ModTinktureTypes.AQUA_REGIA), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("catalyst")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("evolite_sap")));

		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.QUARTZ_SOLUTION), new FluidStack(FluidRegistry.WATER, 1000), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")));
		
		//Tinktures
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.IMPIRUS),  new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heat_core")), new ItemStack(Items.GUNPOWDER, 1));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.LOCUS), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.ALCHEMICAL_COAL, 1), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("gold")));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.REBUS), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("emeradine")), new OreStack("treeSapling", 1));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.AMNIS), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("diamond")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("ender_tear")));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.ANIMO), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("clean_flesh")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart")));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.VOIDUS), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(Items.DRAGON_BREATH, 1), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("eldritch")));

		//Crystals
		ArcanacraftCraftingManager.registerAlembicRecipe(new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("blood")), new TinktureStack(ModTinktureTypes.QUARTZ_SOLUTION), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
		ArcanacraftCraftingManager.registerAlembicRecipe(new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("emeradine")), new TinktureStack(ModTinktureTypes.QUARTZ_SOLUTION), new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("emeradine")), new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("emeradine")));
		ArcanacraftCraftingManager.registerAlembicRecipe(new ItemStack(Items.ENDER_PEARL, 1), new TinktureStack(ModTinktureTypes.QUARTZ_SOLUTION), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("evolite_sap")), new ItemStack(ModItems.DUST, 1, ItemMetaUtil.dust("ender")));
		
		ArcanacraftCraftingManager.registerAlembicRecipe(new ItemStack(ModItems.ALCHEMICAL_COAL, 1), new ItemStack(ModItems.RED_COAL, 1), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.EVOLITE, 1));
		ArcanacraftCraftingManager.registerAlembicRecipe(new ItemStack(ModItems.GORGON_EYE), new ItemStack(Items.ENDER_EYE, 1), new TinktureStack(ModTinktureTypes.VOIDUS), new ItemStack(ModItems.EVOLITE, 1));
		
	}
}
