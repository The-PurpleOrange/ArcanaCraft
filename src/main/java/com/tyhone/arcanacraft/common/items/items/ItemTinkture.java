package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.api.item.IEssenceVessel;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.reference.Names;

import net.minecraft.item.ItemStack;

public class ItemTinkture extends ModItemBase implements IEssenceVessel{
	
	public ItemTinkture() {
		super("tinkture", Names.MetaItems.TINKTURE);
	}

	@Override
	public double getFluid() {
		return 4D;
	}

	@Override
	public String getFluidType(ItemStack stack) {
		return Names.MetaItems.TINKTURE.get(stack.getMetadata());
	}
}
