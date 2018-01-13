package com.tyhone.arcanacraft.client.render;

import java.awt.Color;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureRegistry;
import com.tyhone.arcanacraft.common.items.items.ItemTinkture;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class TinktureColour implements IItemColor{

	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		switch (tintIndex){
			case 0: {
				return ItemTinkture.getColourFromNBT(stack); // .getColour(stack);
			}
			case 1: {
				return Color.WHITE.getRGB();
			}
			default: {
				return Color.WHITE.getRGB();
			}
		}
	}
}
