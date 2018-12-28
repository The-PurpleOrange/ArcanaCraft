package com.tyhone.arcanacraft.client.render.tesr;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.client.util.RenderUtil;
import com.tyhone.arcanacraft.common.tileentity.TileEntityInlayTable;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class RenderTileEntityInlayTable extends TileEntitySpecialRenderer{
	
	private final float[] rot = {0, 28, 143, 222, 96, 170, 50, 330};
	
	public RenderTileEntityInlayTable(RenderManager renderManager, RenderItem renderItem){	
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		TileEntityInlayTable table = (TileEntityInlayTable) te;
		ItemStack[] itemStack = table.getStack();
		
		for(int i = 0; i < itemStack.length; i++) {
			//Arcanacraft.log("Rendering: " + itemStack[i]);
			
			if(!itemStack[i].isEmpty()) {
				//Arcanacraft.log("Rendering: " + itemStack[i]);
				GlStateManager.pushMatrix();
		        GlStateManager.translate((float)x+0.5F, (float)y + 1.03F + 0.03*i, (float)z+0.5F);

		        GlStateManager.rotate(90F, 1, 0, 0);
		        GlStateManager.rotate(rot[i], 0, 0, 1);
	
		        float newScale = itemStack[i].getItem() instanceof ItemBlock ? (0.5F*1.3F) : 0.5F;
		        GlStateManager.scale(newScale, newScale, newScale);
		        
		        try{
		            RenderUtil.renderItemInWorld(itemStack[i]);
		        }
		        catch(Exception e){
		            Arcanacraft.logger.error("Something went wrong trying to render an item in " + te.getDisplayName() + " at " + te.getPos() + "! The item is "+itemStack[i].getItem().getRegistryName()+"! - " +  e);
		        }
		        
	
		        GlStateManager.popMatrix();
			}
		}
		
		
		/*if(itemStack != null && itemStack.getCount()>0){ //Check if there is an item in slot 0

			RenderUtil.renderItem(te, itemStack, x+0.5F, y+1.35F, z+0.5F, partialTicks, true);
			
			
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)x+0.5F, (float)y+1.35F, (float)z+0.5F);

            double sysTime = Minecraft.getSystemTime()/800D;
            GlStateManager.translate(0D, Math.sin(sysTime%(2*Math.PI))*0.065, 0D);
            GlStateManager.rotate((float)(((sysTime*40D)%360)), 0, 1, 0);

            float scale = itemStack.getItem() instanceof ItemBlock ? 0.65F : 0.5F;
            GlStateManager.scale(scale, scale, scale);
            
            try{
                RenderUtil.renderItemInWorld(itemStack);
            }
            catch(Exception e){
                Arcanacraft.logger.log(Level.ERROR, "Something went wrong trying to render an item in a Pedestal! The item is "+itemStack.getItem().getRegistryName()+"! - " +  e);
            }

            GlStateManager.popMatrix();
		}*/
	}

}
