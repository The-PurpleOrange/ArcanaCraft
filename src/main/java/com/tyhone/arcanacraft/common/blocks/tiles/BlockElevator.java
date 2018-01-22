package com.tyhone.arcanacraft.common.blocks.tiles;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.tileentity.TileEntityElevator;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockElevator extends ModBlockTileEntityBase{

	public BlockElevator() {
		super("elevator");
	}
	

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityElevator();
	}
	

}
