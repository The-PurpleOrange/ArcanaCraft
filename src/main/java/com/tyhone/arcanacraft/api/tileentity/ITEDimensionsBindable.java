package com.tyhone.arcanacraft.api.tileentity;

import com.tyhone.arcanacraft.Arcanacraft;

import net.minecraft.util.math.BlockPos;

public interface ITEDimensionsBindable {

	public void setDimensions(BlockPos pos1, BlockPos pos2);
	
	public BlockPos getDimension(int index);
	
	public BlockPos[] getDimensionArray();
	
}
