package com.tyhone.arcanacraft.common.jei;

import com.tyhone.arcanacraft.common.items.items.ItemTinkture;

import mezz.jei.api.ISubtypeRegistry.ISubtypeInterpreter;
import net.minecraft.item.ItemStack;

public class TinktureSubtypeInterpreter implements ISubtypeInterpreter {

	@Override
	public String apply(ItemStack stack) {
		if(stack.hasTagCompound()){
			if(stack.getTagCompound().getString(ItemTinkture.NBT_TINKTURE) != null){
				return ItemTinkture.NBT_TINKTURE;
			}
		}
		return null;
	}

}
