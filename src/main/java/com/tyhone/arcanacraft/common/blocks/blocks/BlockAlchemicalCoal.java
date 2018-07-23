package com.tyhone.arcanacraft.common.blocks.blocks;

import java.util.Random;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAlchemicalCoal extends ModBlockBase{

	public BlockAlchemicalCoal() {
		super("alchemical_coal_block");
		setLightLevel(0.5f);
	}
}
