package com.tyhone.arcanacraft.common.blocks.base;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockTileEntityBase extends ModBlockBase implements ITileEntityProvider{
	
	
	public ModBlockTileEntityBase(String regName) {
		super(regName);
	}
	
	public ModBlockTileEntityBase(String regName, Material material) {
		super(regName, material);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}
}
