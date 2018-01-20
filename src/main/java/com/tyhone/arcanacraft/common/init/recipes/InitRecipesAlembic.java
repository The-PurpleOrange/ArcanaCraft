package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class InitRecipesAlembic {
	public static void init(){

		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.AQUA_REGIA), new FluidStack(FluidRegistry.WATER, 1000), new ItemStack(Items.GUNPOWDER), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new TinktureStack(ModTinktureTypes.AQUA_REGIA), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("soul")));

		//Tinktures
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.IMPIRUS),  new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heat_core")), new ItemStack(Items.GUNPOWDER, 1));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.LOCUS), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.ALCHEMICAL_COAL, 1), new ItemStack(Items.GOLDEN_APPLE, 1));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.REBUS), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("alcharium")), new ItemStack(Items.RABBIT_FOOT, 1));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.AMNIS), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(Items.DIAMOND, 1), new ItemStack(Items.GHAST_TEAR, 1));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.ANIMO), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("clean_flesh")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart")));
		ArcanacraftCraftingManager.registerAlembicRecipe(new TinktureStack(ModTinktureTypes.VOIDUS), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(Items.DRAGON_BREATH, 1), new ItemStack(ModItems.ESSENCE, 1, ItemMetaUtil.essence("eldritch")));

		ArcanacraftCraftingManager.registerAlembicRecipe(new ItemStack(ModItems.ALCHEMICAL_COAL, 1), new ItemStack(ModItems.RED_COAL, 1), new TinktureStack(ModTinktureTypes.ALCHEMIC_BASE), new ItemStack(ModItems.EVOLITE, 1));
		ArcanacraftCraftingManager.registerAlembicRecipe(new ItemStack(ModItems.GORGON_EYE), new ItemStack(Items.ENDER_EYE, 1), new TinktureStack(ModTinktureTypes.VOIDUS), new ItemStack(ModItems.EVOLITE, 1));
		
	}
}
