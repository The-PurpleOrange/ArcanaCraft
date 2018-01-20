package com.tyhone.arcanacraft.client.render.tesr;

import org.lwjgl.opengl.GL11;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.client.util.RenderUtil;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.tileentity.TileEntityJar;

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

public class RenderTileEntityJar extends TileEntitySpecialRenderer{
	

	private static final ResourceLocation TEXTURE = new ResourceLocation(Arcanacraft.MODID + ":textures/blocks/tinkture_fluid_block.png");
	
	public RenderTileEntityJar(RenderManager renderManager, RenderItem renderItem) {
	}

	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		TileEntityJar jar = (TileEntityJar) te;
		TinktureStack tinktureStack = jar.getTinktureStack();
		
		if(tinktureStack.getTinktureType() != ModTinktureTypes.EMPTY){
			
			int hex = tinktureStack.getTinktureType().getColourHex();
			
			float pf = (float) jar.getTinktureAmount() / (float) jar.getMaxFluid();
			
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
				float y1 = 0 + p;
				float ym = 1 - (p*5); 
				float ya = ym-y1;
				float y2 = (pf*ya) + p;
				float z1 = 0 + s;
				float z2 = 0 + 1 - s;
				
				for(EnumFacing facing : RenderUtil.DIRECTIONS){
					RenderUtil.buildQuad(buffer, x1, y1, z1, x2, y2, z2, facing, hex);
				}
			}
			
			
		    
		    
		    tess.draw();

		    GlStateManager.disableBlend();
		    GlStateManager.popMatrix();
		    RenderHelper.enableStandardItemLighting();
		}
	}
		
	/*public static void buildQuad(BufferBuilder renderer, float x, float y, float z, float w, float h, float d, EnumFacing face, int hex, float a) {

		
		float p = (float) 1/ (float) 16;
		
		float s = p*4;
		float x1 = x + s;
		float x2 = x + w - s;
		float y1 = y + p;
		float ym = h - (p*5); 
		float ya = ym-y1;
		float y2 = (a*ya) + p;
		float z1 = z + s;
		float z2 = z + d - s;
		
		double minU = 0;
		double maxU = 1;
		double minV = 0;
		double maxV = 1;


		int r = (hex & 0xFF0000) >> 16;
	    int g = (hex & 0xFF00) >> 8;
	    int b = (hex & 0xFF);
	    
	    
		switch(face) {
		case DOWN:
			renderer.pos(x1, y1, z1).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y1, z1).tex(maxU, minV).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y1, z2).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x1, y1, z2).tex(minU, maxV).color(r, g, b, 255).endVertex();
			break;
		case UP:
			renderer.pos(x1, y2, z1).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(x1, y2, z2).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y2, z2).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y2, z1).tex(maxU, minV).color(r, g, b, 255).endVertex();
			break;
		case NORTH:
			renderer.pos(x1, y1, z1).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x1, y2, z1).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y2, z1).tex(maxU, minV).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y1, z1).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			break;
		case SOUTH:
			renderer.pos(x1, y1, z2).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y1, z2).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y2, z2).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(x1, y2, z2).tex(maxU, minV).color(r, g, b, 255).endVertex();
			break;
		case WEST:
			renderer.pos(x1, y1, z1).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x1, y1, z2).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x1, y2, z2).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(x1, y2, z1).tex(maxU, minV).color(r, g, b, 255).endVertex();
			break;
		case EAST:
			renderer.pos(x2, y1, z1).tex(minU, maxV).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y2, z1).tex(minU, minU).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y2, z2).tex(maxU, minV).color(r, g, b, 255).endVertex();
			renderer.pos(x2, y1, z2).tex(maxU, maxV).color(r, g, b, 255).endVertex();
			break;
		}
	}*/
}
