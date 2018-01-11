package com.tyhone.arcanacraft.client.render.tesr;

import org.lwjgl.opengl.GL11;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.client.util.RenderUtil;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDeconstructionTable;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityDeconstructionTable extends TileEntitySpecialRenderer{
	
	private static final ResourceLocation CIRCLE_INNER = new ResourceLocation(Arcanacraft.MODID + ":textures/models/activation_circle/activation_circle_inner.png");
	private static final ResourceLocation CIRCLE_OUTER = new ResourceLocation(Arcanacraft.MODID + ":textures/models/activation_circle/activation_circle_body.png");
	
	private final float maxR = 0.6F;
	private final float maxG = 0F;
	private final float maxB = 0.9F;
	
	public RenderTileEntityDeconstructionTable(RenderManager renderManager, RenderItem renderItem){	
	}
	
	@Override
	public void render(TileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		TileEntityDeconstructionTable te = (TileEntityDeconstructionTable) tileEntity;
		ItemStack itemStack = te.getStack();

		int progress = te.getWorkTime();
		int maxTime = (int) Math.floor((double)te.getRecipeMaxTime() * 0.8D) ;
		float fraction = 0;
		
		if(progress > 0){
			if(progress > maxTime){
				fraction = 1.0F;
			}
			else{
				fraction = (float) progress / (float) maxTime;
			}
			
			renderLines(te, x, y, z);
			
		}
		float ticks = (te.getWorld().getTotalWorldTime() + partialTicks);
        renderCircle(fraction, x, y, z, ticks);
        
		if(!itemStack.isEmpty()){ //Check if there is an item in slot 0
			
			RenderUtil.renderItem(te, itemStack, x, y+1.35F, z, te.GetRotation(), true, true);
			
			
            /*GlStateManager.pushMatrix();
            GlStateManager.translate((float)x+0.5F, (float)y+1.35F, (float)z+0.5F);
            float time = te.getWorld().getTotalWorldTime() + partialTicks;
            GlStateManager.translate(0D, Math.sin((time/16)%(2*Math.PI))*0.065, 0D);
            GlStateManager.rotate(time*2.2f, 0.0F, 1.0F, 0.0F);

            float scale = itemStack.getItem() instanceof ItemBlock ? 0.65F : 0.5F;
            GlStateManager.scale(scale, scale, scale);
            
            try{
                RenderUtil.renderItemInWorld(itemStack);
            }
            catch(Exception e){
                Arcanacraft.logger.error("Something went wrong trying to render an item in a Deconstruction Table! The item is "+itemStack.getItem().getRegistryName()+"! - " +  e);
            }
            

            GlStateManager.popMatrix();*/

		}
	}
	
	private void renderLines(TileEntityDeconstructionTable te, double x, double y, double z) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y+1, z);
		GlStateManager.disableLighting();
		GlStateManager.disableTexture2D();
        GlStateManager.color(maxR, maxG, maxB);

		/*Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer line = tessellator.getWorldRenderer();
		line.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_TEX);
		line.pos(0.5D, 0.0D, 0.5D);
		line.pos(0.5D, 1.0D, 0.5D);
		tessellator.draw();*/
        
        GL11.glBegin(GL11.GL_LINE_STRIP);

        if(te.getLens().isEmpty()){
			GL11.glVertex3d(0.09375,0,0.09375);
			GL11.glVertex3d(0.5,0.3,0.5);
			GL11.glVertex3d(0.09375,0,0.90625);
			GL11.glVertex3d(0.5,0.3,0.5);
			GL11.glVertex3d(0.90625,0,0.09375);
			GL11.glVertex3d(0.5,0.3,0.5);
			GL11.glVertex3d(0.90625,0,0.90625);
			GL11.glVertex3d(0.5,0.3,0.5);
        }else{
			GL11.glVertex3d(0.09375,0,0.09375);
			GL11.glVertex3d(0.5,0.7,0.5);
			GL11.glVertex3d(0.09375,0,0.90625);
			GL11.glVertex3d(0.5,0.7,0.5);
			GL11.glVertex3d(0.90625,0,0.09375);
			GL11.glVertex3d(0.5,0.7,0.5);
			GL11.glVertex3d(0.90625,0,0.90625);
			GL11.glVertex3d(0.5,0.7,0.5);
			GL11.glVertex3d(0.5,0.7,0.5);
			GL11.glVertex3d(0.5,0.3,0.5);
        }
		//you will want to modify these offsets.
		
		GL11.glEnd();
        
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
	}

	public void renderCircle(float fraction, double x, double y, double z, float ticks){
		
		float r = fraction * maxR;
		float g = fraction * maxG;
		float b = fraction * maxB;
		
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate(x+0.5, y+0.94, z+0.5);
        GlStateManager.scale(0.35, 0.35, 0.35);
        GlStateManager.rotate(ticks, 0.0F, 1.0F, 0.0F);
        GlStateManager.color(r, g, b, 1F);

		bindTexture(CIRCLE_INNER);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder wrInner = tessellator.getBuffer();
		wrInner.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		wrInner.pos(-1.0D, 0.1D, 1.0D).tex(0, 0).endVertex();
		wrInner.pos(1.0D, 0.1D, 1.0D).tex(1, 0).endVertex();
		wrInner.pos(1.0D, 0.1D, -1.0D).tex(1, 1).endVertex();
		wrInner.pos(-1.0D, 0.1D, -1.D).tex(0, 1).endVertex();
		tessellator.draw();
        GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.translate(x+0.5, y+0.94, z+0.5);
        GlStateManager.scale(0.35, 0.35, 0.35);
		GlStateManager.rotate(ticks*-1, 0.0F, 1.0F, 0.0F);
        GlStateManager.color(r, g, b, 1F);

		bindTexture(CIRCLE_OUTER);
		BufferBuilder wrBody = tessellator.getBuffer();
		wrBody.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		wrBody.pos(-1.0D, 0.1D, 1.0D).tex(0, 0).endVertex();
		wrBody.pos(1.0D, 0.1D, 1.0D).tex(1, 0).endVertex();
		wrBody.pos(1.0D, 0.1D, -1.0D).tex(1, 1).endVertex();
		wrBody.pos(-1.0D, 0.1D, -1.D).tex(0, 1).endVertex();
		tessellator.draw();
		GlStateManager.enableLighting();
        GlStateManager.popMatrix();
	}

}
