package com.tyhone.arcanacraft.client.render.tesr;

import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.TileEntityJar;

import net.minecraft.client.Minecraft;
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
	
	private static final ResourceLocation CIRCLE_INNER = new ResourceLocation(Arcanacraft.MODID + ":textures/models/activation_circle/activation_circle_inner.png");
	private static final ResourceLocation CIRCLE_OUTER = new ResourceLocation(Arcanacraft.MODID + ":textures/models/activation_circle/activation_circle_body.png");
	
	
	public RenderTileEntityJar(RenderManager renderManager, RenderItem renderItem){	
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		TileEntityJar jar = (TileEntityJar) te;
		double fluidLevel = jar.getFluidLevel();
		double maxFluid = jar.getMaxFluid();
		String fluidType = jar.getFluidType();
		
		if(true){
			
			Tessellator tess = Tessellator.getInstance();
			BufferBuilder buffer = tess.getBuffer();
			
			GlStateManager.pushMatrix();

			//Render smooth stuff
		    RenderHelper.disableStandardItemLighting();
		    GlStateManager.enableBlend();
		    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		    /*if(Minecraft.isAmbientOcclusionEnabled()) {
		      GL11.glShadeModel(GL11.GL_SMOOTH);
		    }
		    else {
		      GL11.glShadeModel(GL11.GL_FLAT);
		    }*/

		    GlStateManager.translate(x, y+1, z);
		    
			buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
			buffer.color(255, 0, 0, 255);

		    buildQuad(buffer, 0, 0, 0, 1, 1, 1, EnumFacing.DOWN);
		    buildQuad(buffer, 0, 0, 0, 1, 1, 1, EnumFacing.UP);
		    buildQuad(buffer, 0, 0, 0, 1, 1, 1, EnumFacing.NORTH);
		    buildQuad(buffer, 0, 0, 0, 1, 1, 1, EnumFacing.SOUTH);
		    buildQuad(buffer, 0, 0, 0, 1, 1, 1, EnumFacing.EAST);
		    buildQuad(buffer, 0, 0, 0, 1, 1, 1, EnumFacing.WEST);
		    
		    tess.draw();

		    GlStateManager.disableBlend();
		    GlStateManager.popMatrix();
		    RenderHelper.enableStandardItemLighting();
		}
	}
		
	public static void buildQuad(BufferBuilder renderer, double x, double y, double z, double w, double h, double d, EnumFacing face) {

		double s = 0.1;
		double x1 = x + s;
		double x2 = x + w - s;
		double y1 = y + s;
		double y2 = y + h - s;
		double z1 = z + s;
		double z2 = z + d - s;

		switch(face) {
		case DOWN:
			renderer.pos(x1, y1, z1).endVertex();
			renderer.pos(x2, y1, z1).endVertex();
			renderer.pos(x2, y1, z2).endVertex();
			renderer.pos(x1, y1, z2).endVertex();
			break;
		case UP:
			renderer.pos(x1, y2, z1).endVertex();
			renderer.pos(x1, y2, z2).endVertex();
			renderer.pos(x2, y2, z2).endVertex();
			renderer.pos(x2, y2, z1).endVertex();
			break;
		case NORTH:
			renderer.pos(x1, y1, z1).endVertex();
			renderer.pos(x1, y2, z1).endVertex();
			renderer.pos(x2, y2, z1).endVertex();
			renderer.pos(x2, y1, z1).endVertex();
			break;
		case SOUTH:
			renderer.pos(x1, y1, z2).endVertex();
			renderer.pos(x2, y1, z2).endVertex();
			renderer.pos(x2, y2, z2).endVertex();
			renderer.pos(x1, y2, z2).endVertex();
			break;
		case WEST:
			renderer.pos(x1, y1, z1).endVertex();
			renderer.pos(x1, y1, z2).endVertex();
			renderer.pos(x1, y2, z2).endVertex();
			renderer.pos(x1, y2, z1).endVertex();
			break;
		case EAST:
			renderer.pos(x2, y1, z1).endVertex();
			renderer.pos(x2, y2, z1).endVertex();
			renderer.pos(x2, y2, z2).endVertex();
			renderer.pos(x2, y1, z2).endVertex();
			break;
		}
	}
}
