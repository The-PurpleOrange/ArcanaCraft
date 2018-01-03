package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.api.item.IEssenceVessel;
import com.tyhone.arcanacraft.api.registries.Registry;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class ItemTinkture extends ModItemBase implements IEssenceVessel{
	
	public ItemTinkture() {
		super("tinkture", Registry.getTinktureNames());
	}

	@Override
	public double getFluid() {
		return 4D;
	}

	@Override
	public String getFluidType(ItemStack stack) {
		return Registry.getTinktureNameFromMeta(stack.getMetadata());
	}
	
	/*@Override
	public int getColorFromItemStack(ItemStack itemStack, int renderPass) {
		int willAmount = NBTItemHelper.getInt(itemStack, "holdingWill", 0);
		float howFull = ((float)willAmount / (float)this.willStoneMax);
		return renderPass == 1 ? Color.HSBtoRGB(0.13F,  howFull, 1F) : 0xFFFFFF;
	}*/
	
	/*@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Registry.getTinktureHexFromMeta(stack.getMetadata());
		

        //return MathHelper.hsvToRGB(Math.max(0.0F, (float) (1.0F - getDurabilityForDisplay(stack))) / 3.0F, 1.0F, 1.0F);
	}*/
}
