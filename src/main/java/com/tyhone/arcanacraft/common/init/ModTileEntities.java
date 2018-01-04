package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.TileEntityInfusionAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityJar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityLensReceptacle;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestalSlab;
import com.tyhone.arcanacraft.common.tileentity.TileEntityRitualCircle;
import com.tyhone.arcanacraft.common.tileentity.TileEntitySoulAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityTransmutationAltar;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModTileEntities {
	
	public static void register(){

		GameRegistry.registerTileEntity(TileEntityRitualCircle.class, "ritual_circle");
		
		GameRegistry.registerTileEntity(TileEntityLensReceptacle.class, "lens_receptacle");
		GameRegistry.registerTileEntity(TileEntityDeconstructionTable.class, "deconstruction_table");
		GameRegistry.registerTileEntity(TileEntityTransmutationAltar.class, "transmutation_altar");
		GameRegistry.registerTileEntity(TileEntityInfusionAltar.class, "infusion_altar");
		GameRegistry.registerTileEntity(TileEntitySoulAltar.class, "soul_altar");
		GameRegistry.registerTileEntity(TileEntityJar.class, "jar");
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "pedestal");
		GameRegistry.registerTileEntity(TileEntityPedestalSlab.class, "pedestal_slab");
	}

}
