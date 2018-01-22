package com.tyhone.arcanacraft.common.blocks.tiles;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.tileentity.TileEntityAlembic;
import com.tyhone.arcanacraft.common.tileentity.TileEntityWardStone;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWardStone extends ModBlockTileEntityBase{

	public BlockWardStone() {
		super("ward_stone");
	}
	

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityWardStone();
	}
}
