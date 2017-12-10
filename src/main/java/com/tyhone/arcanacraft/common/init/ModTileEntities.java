package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModTileEntities {
	
	public static void register(){
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "pedestal");
	}

}
