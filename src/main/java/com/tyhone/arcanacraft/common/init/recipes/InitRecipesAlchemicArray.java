package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;
import com.tyhone.arcanacraft.common.util.WildStack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class InitRecipesAlchemicArray {
	public static void init(){
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.AYRE, 1), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("quartz_dust")), new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.RED_COAL), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.ITEM, 2, ItemMetaUtil.item("catalyst")), new ItemStack(ModItems.AYRE, 1), new ItemStack(Items.GLOWSTONE_DUST, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.GUNPOWDER, 2), new ItemStack(Items.CLAY_BALL, 1));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.ITEM, 2, ItemMetaUtil.item("catalyst")), new ItemStack(ModItems.AYRE, 1), new ItemStack(Items.GLOWSTONE_DUST, 2), new ItemStack(Items.REDSTONE, 2), new ItemStack(Items.GUNPOWDER, 2), new ItemStack(Items.SLIME_BALL, 1));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.RED_COAL, 1), new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.REDSTONE, 4));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.LEATHER, 1), new ItemStack(ModItems.ITEM, 2, ItemMetaUtil.item("clean_flesh")));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack (Items.IRON_INGOT), new ItemStack(ModItems.AYRE, 1));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.DUST, 2, ItemMetaUtil.dust("emeradine")), new ItemStack (Items.EMERALD), new ItemStack(ModItems.AYRE, 1), new ItemStack(ModItems.DUST, 2, ItemMetaUtil.dust("diamond")));
		
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.COMPONENTS, 1, ItemMetaUtil.component("iustare")), new ItemStack (ModItems.ITEM, 1, ItemMetaUtil.item("inert_star")), new ItemStack(ModItems.ESSENCE, 2, ItemMetaUtil.essence("gold")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("emeradine")));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.COMPONENTS, 1, ItemMetaUtil.component("iniquis")), new ItemStack (ModItems.ITEM, 1, ItemMetaUtil.item("ender_tear")), new ItemStack(ModItems.ESSENCE, 2, ItemMetaUtil.essence("iron")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("emeradine")));
				
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModBlocks.ALCHEMIC_STONE, 8), new ItemStack(Blocks.STONE, 8), new ItemStack(ModItems.AYRE, 1));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModBlocks.BLOOD_STONE, 8), new ItemStack(Blocks.STONE, 8), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_drop")));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModBlocks.SOUL_STONE, 8), new ItemStack(Blocks.STONE, 8), new ItemStack(ModItems.SOUL, 1, ItemMetaUtil.soul("fragment")));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModBlocks.FEY_STONE, 16), new ItemStack(Blocks.STONE, 16), new ItemStack(ModItems.EVOLITE, 1));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModBlocks.ALCHEMIC_GLASS, 8), new ItemStack(Blocks.GLASS, 8), new ItemStack(ModItems.AYRE, 1), new ItemStack(Items.GOLD_INGOT, 1));

		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.MULCH, 1), new ItemStack(Items.REEDS, 6), new ItemStack(Blocks.DIRT, 1, 0));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.MULCH, 4), new OreStack("treeSapling", 3), new ItemStack(Blocks.DIRT, 1, 0));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.MULCH, 1), new OreStack("treeLeaves", 8), new ItemStack(Blocks.DIRT, 1, 0));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.MULCH, 4), new OreStack("arcanacraftFruit", 3), new ItemStack(Blocks.DIRT, 1, 0));
		
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.BEETROOT_SEEDS), new ItemStack (Items.WHEAT_SEEDS));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.PUMPKIN_SEEDS), new ItemStack (Items.BEETROOT_SEEDS));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.MELON_SEEDS), new ItemStack (Items.PUMPKIN_SEEDS));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.WHEAT_SEEDS), new ItemStack (Items.MELON_SEEDS));

		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.CARROT), new ItemStack (Items.WHEAT));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.POTATO), new ItemStack (Items.CARROT));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.BEETROOT), new ItemStack (Items.POTATO));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.WHEAT), new ItemStack (Items.BEETROOT));

		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.REEDS), new ItemStack (Items.APPLE));
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(Items.APPLE), new ItemStack (Items.REEDS));
		
		//Chalk
		ArcanacraftCraftingManager.registerAlchemicArrayRecipe(new ItemStack(ModItems.CHALK_METAMORPHIC, 1),
				new ItemStack(ModItems.CHALK, 1, 0),
				new ItemStack(ModItems.CHALK, 1, 1),
				new ItemStack(ModItems.CHALK, 1, 2),
				new ItemStack(ModItems.CHALK, 1, 3),
				new ItemStack(ModItems.CHALK, 1, 4),
				new ItemStack(ModItems.CHALK, 1, 5),
				new ItemStack(ModItems.RED_COAL, 1),
				new ItemStack(ModItems.AYRE, 2),
				new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("ender_tear"))
				);
	}
}
