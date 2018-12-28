package com.tyhone.arcanacraft.client.render.tesr;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.client.render.block.ModelChest;
import com.tyhone.arcanacraft.common.tileentity.TileEntityHeavyChest;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityHeavyChest extends TileEntitySpecialRenderer<TileEntityHeavyChest>{
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(Arcanacraft.MODID + ":textures/models/heavy_chest/heavy_chest.png");
	private final ModelChest MODEL = new ModelChest();
	
	
	public RenderTileEntityHeavyChest(RenderManager renderManager, RenderItem renderItem) {
	}


	@Override
	public void render(TileEntityHeavyChest te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		//TileEntityAlchemicArray alchemicArray = (TileEntityAlchemicArray) te;
		
		GlStateManager.enableDepth();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);
		
		ModelChest model = MODEL;
		
		if(destroyStage >= 0) {
			this.bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 4.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		}
		else {
			this.bindTexture(TEXTURE);
		}
		
		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.translate((float)x, (float)y+1F, (float)z+1F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		
		float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
		f = 1F - f;
		f = 1F - f * f * f;
		model.chestLid.rotateAngleX = -(f * ((float)Math.PI / 2F));
		
		//Render actual chest
		model.renderAll();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);
		
		if(destroyStage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}
}
