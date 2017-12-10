package com.tyhone.arcanacraft.client.render.tesr;

import org.apache.logging.log4j.Level;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.TileEntityLensReceptacle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class RenderTileEntityLensReceptacle extends TileEntitySpecialRenderer{

	public RenderTileEntityLensReceptacle(RenderManager renderManager, RenderItem renderItem) {
	}

	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		TileEntityLensReceptacle receptacle = (TileEntityLensReceptacle) te;
		ItemStack itemStack = receptacle.getStack();
		
		if(itemStack != null && itemStack.getCount()>0){ //Check if there is an item in slot 0
			
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)x+0.5F, (float)y+0.7F, (float)z+0.5F);

            double sysTime = Minecraft.getSystemTime()/800D;
	        GlStateManager.rotate(90F, 1, 0, 0);
            GlStateManager.rotate((float)(((sysTime*10D)%360)), 0, 0, 1);

            float scale = itemStack.getItem() instanceof ItemBlock ? 0.65F : 0.5F;
            GlStateManager.scale(scale, scale, scale);
            
            try{
    	        //GlStateManager.color(1F, 1F, 1F, 1F);
                //Minecraft.getMinecraft().getRenderItem().renderItem(itemStack, TransformType.FIXED);
                renderItemInWorld(itemStack);
            }
            catch(Exception e){
                Arcanacraft.logger.log(Level.ERROR, "Something went wrong trying to render an item in a Pedestal Table! The item is "+itemStack.getItem().getRegistryName()+"! - " +  e);
            }

            GlStateManager.popMatrix();
		}
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
