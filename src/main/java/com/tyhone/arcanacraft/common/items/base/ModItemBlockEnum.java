package com.tyhone.arcanacraft.common.items.base;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockEnum;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ModItemBlockEnum extends ItemBlock{

	protected final ModBlockEnum block;
	
	public ModItemBlockEnum(ModBlockEnum block) {
		super(block);
		this.block = block;
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta){
		return meta;
	}
	
	@Override
	public String getTranslationKey(ItemStack stack){
		return (block != null) ? this.block.getUnlocalizedName(stack) : super.getTranslationKey(stack);
	}
}
