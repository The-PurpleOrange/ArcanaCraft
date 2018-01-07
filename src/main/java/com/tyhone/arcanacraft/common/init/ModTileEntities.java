package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.TileEntityAlchemicArray;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.TileEntityInfusionAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityJar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityLensReceptacle;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestalSlab;
import com.tyhone.arcanacraft.common.tileentity.TileEntityRitualCircle;
import com.tyhone.arcanacraft.common.tileentity.TileEntitySoulAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntitySpiritSkull;
import com.tyhone.arcanacraft.common.tileentity.TileEntityTransmutationAltar;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModTileEntities {
	
	public static void register(){

		GameRegistry.registerTileEntity(TileEntityAlchemicArray.class, Arcanacraft.MODID + ".alchemic_array");
		GameRegistry.registerTileEntity(TileEntityRitualCircle.class, Arcanacraft.MODID + ".ritual_circle");
		
		GameRegistry.registerTileEntity(TileEntityLensReceptacle.class, Arcanacraft.MODID + ".lens_receptacle");
		GameRegistry.registerTileEntity(TileEntityDeconstructionTable.class, Arcanacraft.MODID + ".deconstruction_table");
		GameRegistry.registerTileEntity(TileEntityTransmutationAltar.class, Arcanacraft.MODID + ".transmutation_altar");
		GameRegistry.registerTileEntity(TileEntityInfusionAltar.class, Arcanacraft.MODID + ".infusion_altar");
		GameRegistry.registerTileEntity(TileEntitySoulAltar.class, Arcanacraft.MODID + ".soul_altar");
		GameRegistry.registerTileEntity(TileEntityJar.class, Arcanacraft.MODID + ".jar");
		GameRegistry.registerTileEntity(TileEntityPedestal.class, Arcanacraft.MODID + ".pedestal");
		GameRegistry.registerTileEntity(TileEntityPedestalSlab.class, Arcanacraft.MODID + ".pedestal_slab");
		

		GameRegistry.registerTileEntity(TileEntitySpiritSkull.class, Arcanacraft.MODID + ".block_spirit_skull");
	}

}
