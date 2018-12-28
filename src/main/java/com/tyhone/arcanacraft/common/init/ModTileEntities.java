package com.tyhone.arcanacraft.common.init;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.tileentity.TileEntityAlchemicArray;
import com.tyhone.arcanacraft.common.tileentity.TileEntityAlembic;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDeconstructionTable;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDisapearingBlock;
import com.tyhone.arcanacraft.common.tileentity.TileEntityElevator;
import com.tyhone.arcanacraft.common.tileentity.TileEntityGrandRitualCircle;
import com.tyhone.arcanacraft.common.tileentity.TileEntityHeavyChest;
import com.tyhone.arcanacraft.common.tileentity.TileEntityInfusionAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityInlayTable;
import com.tyhone.arcanacraft.common.tileentity.TileEntityJar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityLensReceptacle;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestalSlab;
import com.tyhone.arcanacraft.common.tileentity.TileEntityReapearingBlock;
import com.tyhone.arcanacraft.common.tileentity.TileEntityRitualCircle;
import com.tyhone.arcanacraft.common.tileentity.TileEntitySoulAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntitySpiritSkull;
import com.tyhone.arcanacraft.common.tileentity.TileEntityTransmutationAltar;
import com.tyhone.arcanacraft.common.tileentity.TileEntityTreeTap;
import com.tyhone.arcanacraft.common.tileentity.TileEntityVacuumChest;
import com.tyhone.arcanacraft.common.tileentity.TileEntityVacuumChestEnder;
import com.tyhone.arcanacraft.common.tileentity.TileEntityWardStone;

import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Arcanacraft.MODID)
public class ModTileEntities {
	
	public static void register(){

		GameRegistry.registerTileEntity(TileEntityAlchemicArray.class, Arcanacraft.MODID + ".alchemic_array");
		GameRegistry.registerTileEntity(TileEntityRitualCircle.class, Arcanacraft.MODID + ".ritual_circle");
		GameRegistry.registerTileEntity(TileEntityGrandRitualCircle.class, Arcanacraft.MODID + ".grand_ritual_circle");
		
		GameRegistry.registerTileEntity(TileEntityLensReceptacle.class, Arcanacraft.MODID + ".lens_receptacle");
		GameRegistry.registerTileEntity(TileEntityDeconstructionTable.class, Arcanacraft.MODID + ".deconstruction_table");
		GameRegistry.registerTileEntity(TileEntityTransmutationAltar.class, Arcanacraft.MODID + ".transmutation_altar");
		GameRegistry.registerTileEntity(TileEntityInfusionAltar.class, Arcanacraft.MODID + ".infusion_altar");
		GameRegistry.registerTileEntity(TileEntitySoulAltar.class, Arcanacraft.MODID + ".soul_altar");
		GameRegistry.registerTileEntity(TileEntityJar.class, Arcanacraft.MODID + ".jar");
		GameRegistry.registerTileEntity(TileEntityAlembic.class, Arcanacraft.MODID + ".alembic");
		GameRegistry.registerTileEntity(TileEntityPedestal.class, Arcanacraft.MODID + ".pedestal");
		GameRegistry.registerTileEntity(TileEntityPedestalSlab.class, Arcanacraft.MODID + ".pedestal_slab");

		GameRegistry.registerTileEntity(TileEntityInlayTable.class, Arcanacraft.MODID + ".inlay_table");
		
		GameRegistry.registerTileEntity(TileEntityHeavyChest.class, Arcanacraft.MODID + ".heavy_chest");
		GameRegistry.registerTileEntity(TileEntityVacuumChest.class, Arcanacraft.MODID + ".vacuum_chest");
		GameRegistry.registerTileEntity(TileEntityVacuumChestEnder.class, Arcanacraft.MODID + ".vacuum_chest_ender");

		GameRegistry.registerTileEntity(TileEntityTreeTap.class, Arcanacraft.MODID + ".tree_tap");

		GameRegistry.registerTileEntity(TileEntitySpiritSkull.class, Arcanacraft.MODID + ".block_spirit_skull");

		GameRegistry.registerTileEntity(TileEntityDisapearingBlock.class, Arcanacraft.MODID + ".disapearing_block");
		GameRegistry.registerTileEntity(TileEntityReapearingBlock.class, Arcanacraft.MODID + ".reapearing_block");

		GameRegistry.registerTileEntity(TileEntityWardStone.class, Arcanacraft.MODID + ".ward_stone");
		GameRegistry.registerTileEntity(TileEntityElevator.class, Arcanacraft.MODID + ".elevator");
	}

}
