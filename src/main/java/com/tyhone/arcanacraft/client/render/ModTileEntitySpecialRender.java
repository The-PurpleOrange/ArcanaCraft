package com.tyhone.arcanacraft.client.render;

import com.tyhone.arcanacraft.client.render.tesr.RenderTileEntityDeconstructionTable;
import com.tyhone.arcanacraft.client.render.tesr.RenderTileEntityInfusionAltar;
import com.tyhone.arcanacraft.client.render.tesr.RenderTileEntityLensReceptacle;
import com.tyhone.arcanacraft.client.render.tesr.RenderTileEntityPedestal;
import com.tyhone.arcanacraft.client.render.tesr.RenderTileEntityPedestalSlab;
import com.tyhone.arcanacraft.client.render.tesr.RenderTileEntitySoulAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.TileEntityInfusionAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityLensReceptacle;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestalSlab;
import com.tyhone.arcanacraft.common.tileentity.TileEntitySoulAltar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModTileEntitySpecialRender {
	public static void preInit(){
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();


		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLensReceptacle.class, new RenderTileEntityLensReceptacle(renderManager, renderItem));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDeconstructionTable.class, new RenderTileEntityDeconstructionTable(renderManager, renderItem));

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfusionAltar.class, new RenderTileEntityInfusionAltar(renderManager, renderItem));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulAltar.class, new RenderTileEntitySoulAltar(renderManager, renderItem));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new RenderTileEntityPedestal(renderManager, renderItem));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestalSlab.class, new RenderTileEntityPedestalSlab(renderManager, renderItem));
	}
}
