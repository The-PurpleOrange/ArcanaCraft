package com.tyhone.arcanacraft.client.render.tesr;

import org.apache.logging.log4j.Level;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.client.util.RenderUtil;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;

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

public class RenderTileEntityPedestalSlab extends TileEntitySpecialRenderer{
	
	public RenderTileEntityPedestalSlab(RenderManager renderManager, RenderItem renderItem){	
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		TileEntityPedestal pedestal = (TileEntityPedestal) te;
		ItemStack itemStack = pedestal.getStack();
		
		if(itemStack != null && itemStack.getCount()>0){ //Check if there is an item in slot 0

			RenderUtil.renderItem(te, itemStack, x, y+0.65F, z, partialTicks, true);
			
            /*GlStateManager.pushMatrix();
            GlStateManager.translate((float)x+0.5F, (float)y+0.65F, (float)z+0.5F);

            double sysTime = Minecraft.getSystemTime()/800D;
            GlStateManager.translate(0D, Math.sin(sysTime%(2*Math.PI))*0.065, 0D);
            GlStateManager.rotate((float)(((sysTime*40D)%360)), 0, 1, 0);

            float scale = itemStack.getItem() instanceof ItemBlock ? 0.65F : 0.5F;
            GlStateManager.scale(scale, scale, scale);
            
            try{
                RenderUtil.renderItemInWorld(itemStack);
            }
            catch(Exception e){
                Arcanacraft.logger.log(Level.ERROR, "Something went wrong trying to render an item in a Pedestal Slab! The item is "+itemStack.getItem().getRegistryName()+"! - " +  e);
            }

            GlStateManager.popMatrix();*/
		}
	}

}
