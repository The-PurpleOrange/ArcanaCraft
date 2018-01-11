package com.tyhone.arcanacraft.client.util;

import com.tyhone.arcanacraft.Arcanacraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class RenderUtil {


	public static void renderItem(TileEntity te, ItemStack itemStack, double x, double y, double z, float speed, boolean bob){
		renderItem(te, itemStack, x, y, z, speed, bob, false);
	}
	
	public static void renderItem(TileEntity te, ItemStack itemStack, double x, double y, double z, float speed, boolean bob, boolean customSpeed){

		double sysTime = (Minecraft.getSystemTime()/800D);
		
		GlStateManager.pushMatrix();
        GlStateManager.translate((float)x+0.5F, (float)y, (float)z+0.5F);
        if(bob){
        	 GlStateManager.translate(0D, Math.sin(sysTime%(2*Math.PI))*0.065, 0D);
        }
        if(customSpeed){
        	GlStateManager.rotate((float)(((sysTime*40D)%360) + (speed*2)), 0, 1, 0);
        }else{
        	GlStateManager.rotate((float)(((sysTime*40D)%360)), 0, 1, 0);
        }

        float scale = itemStack.getItem() instanceof ItemBlock ? 0.65F : 0.5F;
        GlStateManager.scale(scale, scale, scale);
        
        try{
            renderItemInWorld(itemStack);
        }
        catch(Exception e){
            Arcanacraft.logger.error("Something went wrong trying to render an item in " + te.getDisplayName() + " at " + te.getPos() + "! The item is "+itemStack.getItem().getRegistryName()+"! - " +  e);
        }
        

        GlStateManager.popMatrix();
	}
	
	public static void renderItemInWorld(ItemStack stack){
	    GlStateManager.pushMatrix();
	    GlStateManager.pushAttrib();
	    RenderHelper.enableStandardItemLighting();
	    Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.FIXED);
	    RenderHelper.disableStandardItemLighting();
	    GlStateManager.popAttrib();
	    GlStateManager.popMatrix();
	}

}
