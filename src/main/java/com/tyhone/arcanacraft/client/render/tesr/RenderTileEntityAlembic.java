package com.tyhone.arcanacraft.client.render.tesr;

import org.lwjgl.opengl.GL11;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.client.util.RenderUtil;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.tileentity.TileEntityAlembic;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityAlembic extends TileEntitySpecialRenderer{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Arcanacraft.MODID + ":textures/blocks/tinkture_fluid_block.png");
	
	public RenderTileEntityAlembic(RenderManager renderManager, RenderItem renderItem){	
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		TileEntityAlembic alembic = (TileEntityAlembic) te;

		Object stack0 = alembic.getStack(0);
		if(stack0 != null){
			//Arcanacraft.log("Stack0: " + stack0.toString());
		}
		Object stack1 = alembic.getStack(1);
		if(stack1 != null){
			//Arcanacraft.log("Stack1: " + stack1.toString());
		}
		Object stack2 = alembic.getStack(2);
		if(stack2 != null){
			//Arcanacraft.log("Stack2: " + stack2.toString());
		}

		/*if(stack0 != null && stack0 instanceof TinktureStack){
			Arcanacraft.log("DRAWING TINKTURESTACK");
			TinktureStack tinktureStack = (TinktureStack) stack0;
			

			//Arcanacraft.log("Unlocalized Name: " + tinktureStack.getUnlocalizedName());
			//Arcanacraft.log("Hex: " + tinktureStack.getTinktureType().getColourHex());
			if(tinktureStack.getTinktureType() != ModTinktureTypes.EMPTY){
				
				int hex = tinktureStack.getTinktureType().getColourHex();
				
				float pf = (float) tinktureStack.getAmount() / (float) alembic.getMaxTinkture();
				
				bindTexture(TEXTURE);
				
				Tessellator tess = Tessellator.getInstance();
				BufferBuilder buffer = tess.getBuffer();
				
				GlStateManager.pushMatrix();
	
			    RenderHelper.disableStandardItemLighting();
			    GlStateManager.enableBlend();
	
	
			    GlStateManager.translate(x, y, z);
			    
				buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
				
				{
					float p = (float) 1/ (float) 16;
					
					float s = p*4;
					float x1 = 0 + s;
					float x2 = 0 + 1 - s;
					float y1 = p*2;
					float ym = p*3; 
					float ya = ym*pf;
					float y2 = y1+ya;
					float z1 = 0 + s;
					float z2 = 0 + 1 - s;
					
					for(EnumFacing facing : RenderUtil.DIRECTIONS){
						RenderUtil.buildQuad(buffer, x1, y1, z1, x2, y2, z2, facing, hex, pf);
					}
				}
				
				
			    
			    
			    tess.draw();
	
			    GlStateManager.disableBlend();
			    GlStateManager.popMatrix();
			    RenderHelper.enableStandardItemLighting();
			}
		}*/
	}
}
