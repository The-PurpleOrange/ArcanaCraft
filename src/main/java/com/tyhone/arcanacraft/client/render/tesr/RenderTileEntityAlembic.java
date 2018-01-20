package com.tyhone.arcanacraft.client.render.tesr;

import org.lwjgl.opengl.GL11;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.client.util.RenderUtil;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.tileentity.TileEntityAlembic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class RenderTileEntityAlembic extends TileEntitySpecialRenderer{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Arcanacraft.MODID + ":textures/blocks/tinkture_fluid_block.png");
	
	public RenderTileEntityAlembic(RenderManager renderManager, RenderItem renderItem){	
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		TileEntityAlembic alembic = (TileEntityAlembic) te;


		float[] fspx = {6.5F, 3.5F, 10.5F};
		float[] fepx = {3F, 2F, 2F};

		float[] fspy = {2.5F, 7.5F, 7.5F};
		float[] fepy = {5F, 3F, 3F};

		float[] fspz = {9.5F, 5.5F, 5.5F};
		float[] fepz = {3F, 2F, 2F};
		
		float[] ipx = {8F, 4.5F, 11.5F};
		float[] ipy = {5.01F, 9.01F, 9.01F};
		float[] ipz = {11F, 6.5F, 6.5F};
		
		Object[] stack = {alembic.getStack(0), alembic.getStack(1), alembic.getStack(2)};
		
		//0 is bottom
		//1 is top left
		//2 is top right

		for(int i = 0; i < 3; i++){
			if(stack[i] != null && stack[i] instanceof TinktureStack){
				renderJarTinkture(alembic, (TinktureStack) stack[i], x, y, z, fspx[i], fspy[i], fspz[i], fepx[i], fepy[i], fepz[i]);
			}
			if(stack[i] != null && stack[i] instanceof ItemStack){
				RenderUtil.renderItem(te, (ItemStack) stack[i], x+(ipx[i] / 16F), y+(ipy[i] / 16F), z+(ipz[i] / 16F), 1F, true, i == 0 ? 0.2F : 0.15F);
			}
			if(stack[i] != null && stack[i] instanceof FluidStack){
				renderJarFluid(alembic, (FluidStack) stack[i], x, y, z, fspx[i], fspy[i], fspz[i], fepx[i], fepy[i], fepz[i]);
			}
		}
	}
	
	public void renderJarTinkture(TileEntityAlembic alembic, TinktureStack stack, double xPos, double yPos, double zPos, float x, float y, float z, float w, float h, float d){

		if(stack.getTinktureType() != ModTinktureTypes.EMPTY){
			
			int colourHex = stack.getTinktureType().getColourHex();
			
			float pf = (float) stack.getAmount() / (float) alembic.getMaxTinkture();
			
			bindTexture(TEXTURE);
			
			Tessellator tess = Tessellator.getInstance();
			BufferBuilder buffer = tess.getBuffer();
			
			GlStateManager.pushMatrix();

		    RenderHelper.disableStandardItemLighting();
		    GlStateManager.enableBlend();


		    GlStateManager.translate(xPos, yPos, zPos);
		    
			buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

			{
				float x1 = x / (float) 16;
				float x2 = x1 + (w / (float) 16);
				float y1 = y / (float) 16;
				float ym = h / (float) 16;
				float ya = ym*pf;
				float y2 = y1+ya;
				float z1 = z / (float) 16;
				float z2 = z1 + (d / (float) 16);
				
				for(EnumFacing facing : RenderUtil.DIRECTIONS){
					RenderUtil.buildQuad(buffer, x1, y1, z1, x2, y2, z2, facing, colourHex);
				}
			}
			
		    tess.draw();

		    GlStateManager.disableBlend();
		    GlStateManager.popMatrix();
		    RenderHelper.enableStandardItemLighting();
		}
	}
	
	public void renderJarFluid(TileEntityAlembic alembic, FluidStack stack, double xPos, double yPos, double zPos, float x, float y, float z, float w, float h, float d){

		if(stack.getFluid() == FluidRegistry.WATER){
			
			//int colourHex = stack.getFluid().getColor();
			int colourHex = 0x3232ff;
			
			float pf = 1F;
			
			bindTexture(TEXTURE);
			
			Tessellator tess = Tessellator.getInstance();
			BufferBuilder buffer = tess.getBuffer();
			
			GlStateManager.pushMatrix();

		    RenderHelper.disableStandardItemLighting();
		    GlStateManager.enableBlend();


		    GlStateManager.translate(xPos, yPos, zPos);
		    
			buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

			{
				float x1 = x / (float) 16;
				float x2 = x1 + (w / (float) 16);
				float y1 = y / (float) 16;
				float ym = h / (float) 16;
				float ya = ym*pf;
				float y2 = y1+ya;
				float z1 = z / (float) 16;
				float z2 = z1 + (d / (float) 16);
				
				for(EnumFacing facing : RenderUtil.DIRECTIONS){
					RenderUtil.buildQuad(buffer, x1, y1, z1, x2, y2, z2, facing, colourHex, 175);
				}
			}
			
		    tess.draw();

		    GlStateManager.disableBlend();
		    GlStateManager.popMatrix();
		    RenderHelper.enableStandardItemLighting();
		}
	}
}
