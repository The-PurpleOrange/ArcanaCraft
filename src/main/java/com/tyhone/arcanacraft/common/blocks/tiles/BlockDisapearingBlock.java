package com.tyhone.arcanacraft.common.blocks.tiles;

import java.util.List;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDisapearingBlock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDisapearingBlock extends ModBlockTileEntityBase{

	private static final int TIMER = 40;
	private final boolean IGNORE_SIMILAR = false;
	
	public BlockDisapearingBlock() {
		super("disapearing_block");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		TileEntityDisapearingBlock te = new TileEntityDisapearingBlock();
		te.setTimer(TIMER);
		return te;
	}
	
	public static int getTimer(){
		return TIMER;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }   
	
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

        if (this == ModBlocks.DISAPEARING_BLOCK)
        {
            if (blockState != iblockstate)
            {
                return true;
            }

            if (block == this)
            {
                return false;
            }
        }

        return !this.IGNORE_SIMILAR && block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
}
