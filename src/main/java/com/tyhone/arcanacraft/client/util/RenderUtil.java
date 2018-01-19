package com.tyhone.arcanacraft.client.util;

import com.tyhone.arcanacraft.Arcanacraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class RenderUtil {


	public static final EnumFacing[] DIRECTIONS = {EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST};
	
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
	
	public static void buildQuad(BufferBuilder renderer, float x, float y, float z, float w, float h, float d, EnumFacing face, int hex, float a) {

		/*float x = x;
		float w = x + w;
		float x = y;
		float h = y + h;
		float z = z;
		float d = z + d;*/
		
		double minU = 0;
		double maxU = 1;
		double minV = 0;
		double maxV = 1;


		int r = (hex & 0xFF0000) >> 16;
	    int g = (hex & 0xFF00) >> 8;
	    int b = (hex & 0xFF);
	    
	    
		switch(face) {
		case DOWN:
			renderer.pos(x, y, z).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(w, y, z).tex(maxU, minV).color(r, g, b, 255).endVertex();
			renderer.pos(w, y, d).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x, y, d).tex(minU, maxV).color(r, g, b, 255).endVertex();
			break;
		case UP:
			renderer.pos(x, h, z).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(x, h, d).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(w, h, d).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(w, h, z).tex(maxU, minV).color(r, g, b, 255).endVertex();
			break;
		case NORTH:
			renderer.pos(x, y, z).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x, h, z).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(w, h, z).tex(maxU, minV).color(r, g, b, 255).endVertex();
			renderer.pos(w, y, z).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			break;
		case SOUTH:
			renderer.pos(x, y, d).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(w, y, d).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(w, h, d).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(x, h, d).tex(maxU, minV).color(r, g, b, 255).endVertex();
			break;
		case WEST:
			renderer.pos(x, y, z).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x, y, d).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x, h, d).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(x, h, z).tex(maxU, minV).color(r, g, b, 255).endVertex();
			break;
		case EAST:
			renderer.pos(w, y, z).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(w, h, z).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(w, h, d).tex(maxU, minV).color(r, g, b, 255).endVertex();
			renderer.pos(w, y, d).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			break;
		}
	}

}
