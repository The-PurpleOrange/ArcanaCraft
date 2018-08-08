package com.tyhone.arcanacraft.client.render.tesr;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.TileEntityRitualCircle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RenderTileEntityRitualCircle extends TileEntitySpecialRenderer{

	private static final ResourceLocation CIRCLE_INNER = new ResourceLocation(Arcanacraft.MODID + ":textures/models/activation_circle/activation_circle_inner.png");
	private static final ResourceLocation CIRCLE_OUTER = new ResourceLocation(Arcanacraft.MODID + ":textures/models/activation_circle/activation_circle_body.png");
	
	
	public RenderTileEntityRitualCircle(RenderManager renderManager, RenderItem renderItem){	
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		TileEntityRitualCircle ritualCircle = (TileEntityRitualCircle) te;
		if(ritualCircle.isCasting()) {
			spawnCastingParticles(te.getWorld(), te.getPos());
		}
		
        renderCircle(te, x, y, z, partialTicks);
	}
	
	public void renderCircle(TileEntity te, double x, double y, double z, float partialTicks){
		GlStateManager.pushMatrix();
		GlStateManager.translate(x+0.5, y+0.04, z+0.5);
        GlStateManager.scale(0.75, 0.75, 0.75);
        GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks), 0.0F, 1.0F, 0.0F);
        GlStateManager.color(0.8F, 0.7F, 0F, 1F);
		GlStateManager.disableLighting();

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
		GlStateManager.translate(x+0.5, y+0.03, z+0.5);
        GlStateManager.scale(0.75, 0.75, 0.75);
		GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks)*-1, 0.0F, 1.0F, 0.0F);
        GlStateManager.color(0.8F, 0.7F, 0F, 1F);

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

	
	public void spawnCastingParticles(World world, BlockPos pos) {
		Random rand = new Random();

		if(rand.nextInt(192) == 0 && !Minecraft.getMinecraft().isGamePaused()) {
			double x = pos.getX() + 0.5D + ((rand.nextFloat() - 0.5D) * 1.2);
	        double y = pos.getY() + 0.0625F;
	        double z = pos.getZ() + 0.5D + ((rand.nextFloat() - 0.5D) * 1.2);
	        
	        world.spawnParticle(EnumParticleTypes.SPELL_WITCH, x, y, z, 0, 0.2, 0);
		}
	}
}
