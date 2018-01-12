package com.tyhone.arcanacraft.common.jei;

import java.util.Collections;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;

import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

public class TinktureIngredientRenderer implements IIngredientRenderer<TinktureStack> {
	
	@Override
	public void render(Minecraft minecraft, int x, int y, TinktureStack ingredient) {
		if (ingredient != null) {

			int colourHex = ingredient.getTinktureType().getColourHex();

			int z = 0;
			int h = 6;
			int w = 6;
			
			GlStateManager.enableDepth();
			RenderHelper.enableGUIStandardItemLighting();
			
			ResourceLocation tinktureTexture = new ResourceLocation(Arcanacraft.MODID + ":textures/t.png");
			minecraft.renderEngine.bindTexture(tinktureTexture);
			
			//setGLColourFromInt(colourHex);
			
			int r = (colourHex & 0xFF0000) >> 16;
		    int g = (colourHex & 0xFF00) >> 8;
		    int b = (colourHex & 0xFF);

			Tessellator tess = Tessellator.getInstance();
			BufferBuilder buffer = tess.getBuffer();
			
			
			buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
			buffer.pos(x, y+h, z).tex(0, 1).color(r, g, b, 255).endVertex();
			buffer.pos(x+w, y+h, z).tex(0, 1).color(r, g, b, 255).endVertex();
			buffer.pos(x+w, y, z).tex(0, 1).color(r, g, b, 255).endVertex();
			buffer.pos(x, y, z).tex(0, 1).color(r, g, b, 255).endVertex();
			
			tess.draw();
			
			GlStateManager.disableBlend();
			RenderHelper.disableStandardItemLighting();
		}
	}

	private void setGLColourFromInt(int hex) {
		
	    int r = (hex & 0xFF0000) >> 16;
	    int g = (hex & 0xFF00) >> 8;
	    int b = (hex & 0xFF);
		
		GlStateManager.color(r, g, b);
	}

	@Override
	public List<String> getTooltip(Minecraft minecraft, TinktureStack ingredient, ITooltipFlag tooltipFlag) {
		String tooltip = String.format("%s%n%s", ingredient.getDisplayNameFromStack(), ingredient.getAmount()+"mt");
		return Collections.singletonList(tooltip);
	}
}
