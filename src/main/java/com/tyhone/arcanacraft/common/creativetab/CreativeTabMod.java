package com.tyhone.arcanacraft.common.creativetab;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabMod extends CreativeTabs{

	public CreativeTabMod() {
		super(Arcanacraft.MODID);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.ALCHEMICAL_COAL);
	}

}
