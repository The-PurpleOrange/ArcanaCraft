package com.tyhone.arcanacraft.common.util;

import net.minecraft.util.math.BlockPos;

public class PosUtil {

	private PosUtil() {}
	
	public static BlockPos combinePos(BlockPos pos1, BlockPos pos2){
		return pos1.add(pos2.getX(), pos2.getY(), pos2.getZ());
	}
}
