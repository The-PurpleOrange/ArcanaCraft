package com.tyhone.arcanacraft.common.items;

import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ModToolMaterial {

	public static ToolMaterial BLOOD_IRON = EnumHelper.addToolMaterial("blood_iron", 2, 200, 6.0F, 2.5F, 8);
	public static ToolMaterial EMERADINE = EnumHelper.addToolMaterial("emeradine", 3, 1024, 7.0F, 2.5F, 15);
	
	public static void initRepairMaterial() {
		BLOOD_IRON.setRepairItem(new ItemStack(ModItems.INGOT, 1, ItemMetaUtil.ingot("blood_iron")));
		EMERADINE.setRepairItem(new ItemStack(ModItems.ITEM, 1, ItemMetaUtil.item("emeradine")));
	}
}
