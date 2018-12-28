package com.tyhone.arcanacraft.common.init.recipes;

import com.tyhone.arcanacraft.api.recipe.ArcanacraftCraftingManager;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.OreStack;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitRecipesInlayTable {
	public static void init(){
		{
			
			//ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(Items.IRON_SWORD, 1), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.STICK));
			//ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(Items.GOLDEN_SWORD, 1), new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.STICK));
			
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.COMPONENTS, 1, ItemMetaUtil.component("studded_leather")), new ItemStack(Items.LEATHER), new ItemStack(Items.IRON_NUGGET), new ItemStack(Items.IRON_NUGGET), new ItemStack(Items.IRON_NUGGET));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.TOOL_TOOL), new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("gold")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("arcane")));
			
			//Plates
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("iron")), new ItemStack(Items.IRON_INGOT));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("gold")), new ItemStack(Items.GOLD_INGOT));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("magicite")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("blood_iron")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("blood_iron")));
			
			//Gears
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.GEAR, 1, ItemMetaUtil.plate("iron")), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("iron")), new ItemStack(Items.IRON_NUGGET), new ItemStack(Items.IRON_NUGGET), new ItemStack(Items.IRON_NUGGET), new ItemStack(Items.IRON_NUGGET));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.GEAR, 1, ItemMetaUtil.plate("gold")), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("gold")), new ItemStack(Items.GOLD_NUGGET), new ItemStack(Items.GOLD_NUGGET), new ItemStack(Items.GOLD_NUGGET), new ItemStack(Items.GOLD_NUGGET));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.GEAR, 1, ItemMetaUtil.plate("magicite")), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("magicite")), new ItemStack(ModItems.NUGGET, 1, ItemMetaUtil.nugget("magicite")), new ItemStack(ModItems.NUGGET, 1, ItemMetaUtil.nugget("magicite")), new ItemStack(ModItems.NUGGET, 1, ItemMetaUtil.nugget("magicite")), new ItemStack(ModItems.NUGGET, 1, ItemMetaUtil.nugget("magicite")));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.GEAR, 1, ItemMetaUtil.plate("blood_iron")), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("blood_iron")), new ItemStack(ModItems.NUGGET, 1, ItemMetaUtil.nugget("blood_iron")), new ItemStack(ModItems.NUGGET, 1, ItemMetaUtil.nugget("blood_iron")), new ItemStack(ModItems.NUGGET, 1, ItemMetaUtil.nugget("blood_iron")), new ItemStack(ModItems.NUGGET, 1, ItemMetaUtil.nugget("blood_iron")));
		
			//Gearboxes
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.GEARBOX, 1, ItemMetaUtil.gearbox("standard")), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("magicite")), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("gold")), new ItemStack(ModItems.GEAR, 1, ItemMetaUtil.plate("iron")));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.GEARBOX, 1, ItemMetaUtil.gearbox("advanced")), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("magicite")), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("blood_iron")), new ItemStack(ModItems.GEAR, 1, ItemMetaUtil.plate("gold")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_rod")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heart_ender")));
			
			
			//Wands
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.TOOL_WAND_ILLUMINATION), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack(Items.GOLD_INGOT), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heat_core")));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.TOOL_WAND_GRAPPLE), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("star_shard")), new ItemStack(ModItems.CRYSTAL, 1, ItemMetaUtil.crystal("emeradine")));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.TOOL_WAND_SHIELDING), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_rod")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_rod")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack(ModItems.SHARD, 1, ItemMetaUtil.shard("eldritch")));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.TOOL_WAND_EXODUS), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_rod")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_rod")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("ender_tear")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("ender_tear")), new ItemStack(Items.ENDER_EYE));
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.TOOL_WAND_FIRE), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_rod")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("blood_rod")), new ItemStack(ModItems.PLATE, 1, ItemMetaUtil.plate("gold")), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("heat_core")));

			//Trinkets
			ArcanacraftCraftingManager.registerInlayTableRecipe(new ItemStack(ModItems.TRINKET_LAVA_CHARM), new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("lava_quartz")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")), new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("magicite")));

		}
	}
}
