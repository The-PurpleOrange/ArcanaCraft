package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestalSlab;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModTileEntities {
	
	public static void register(){
		GameRegistry.registerTileEntity(TileEntityDeconstructionTable.class, "deconstruction_table");
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "pedestal");
		GameRegistry.registerTileEntity(TileEntityPedestalSlab.class, "pedestal_slab");
	}

}
