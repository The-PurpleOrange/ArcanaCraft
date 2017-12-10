package com.tyhone.arcanacraft.common.util;

import com.tyhone.arcanacraft.Arcanacraft;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {
	
	private ResourceLocationHelper() {}
	
	public static ResourceLocation getResourceLocation(String path){
		return new ResourceLocation(Arcanacraft.MODID, path);
	}
	
	public static ModelResourceLocation getModelResourceLocation(String path){
		return new ModelResourceLocation(Arcanacraft.MODID + ":" + path);
	}
}
