package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.TileEntityInfusionAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityLensReceptacle;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestalSlab;
import com.tyhone.arcanacraft.common.tileentity.TileEntitySoulAltar;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModTileEntities {
	
	public static void register(){
		GameRegistry.registerTileEntity(TileEntityLensReceptacle.class, "lens_receptacle");
		GameRegistry.registerTileEntity(TileEntityDeconstructionTable.class, "deconstruction_table");
		GameRegistry.registerTileEntity(TileEntityInfusionAltar.class, "infusion_altar");
		GameRegistry.registerTileEntity(TileEntitySoulAltar.class, "soul_altar");
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "pedestal");
		GameRegistry.registerTileEntity(TileEntityPedestalSlab.class, "pedestal_slab");
	}

}
