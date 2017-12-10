package com.tyhone.arcanacraft.client.render;

import com.tyhone.arcanacraft.client.render.tesr.RenderTileEntityDeconstructionTable;
import com.tyhone.arcanacraft.client.render.tesr.RenderTileEntityPedestal;
import com.tyhone.arcanacraft.client.render.tesr.RenderTileEntityPedestalSlab;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestalSlab;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModTileEntitySpecialRender {
	public static void preInit(){
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();


		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDeconstructionTable.class, new RenderTileEntityDeconstructionTable(renderManager, renderItem));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new RenderTileEntityPedestal(renderManager, renderItem));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestalSlab.class, new RenderTileEntityPedestalSlab(renderManager, renderItem));
	}
}
