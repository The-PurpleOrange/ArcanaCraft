package com.tyhone.arcanacraft.common.util;

import com.tyhone.arcanacraft.Arcanacraft;

import net.minecraft.util.ResourceLocation;

public class GuiUtils {
	
	public static ResourceLocation getGuiLocation(String file){
		return new ResourceLocation(Arcanacraft.MODID, "textures/gui/"+file+".png");
	}
}
