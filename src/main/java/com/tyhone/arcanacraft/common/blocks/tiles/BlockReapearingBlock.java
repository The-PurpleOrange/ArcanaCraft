package com.tyhone.arcanacraft.common.blocks.tiles;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.tileentity.TileEntityReapearingBlock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockReapearingBlock extends ModBlockTileEntityBase{

	private final boolean ignoreSimilarity = false;
	
	private final static BlockPos[] ADJCACENT_BLOCKS = {
			new BlockPos(1, 0 , 0), 
			new BlockPos(-1, 0, 0), 
			new BlockPos(0, 0, 1), 
			new BlockPos(0, 0, -1), 
			new BlockPos(0, 1, 0), 
			new BlockPos(0, -1, 0)
		};
	
	public BlockReapearingBlock() {
		super("reapearing_block");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		TileEntityReapearingBlock te = new TileEntityReapearingBlock();
		return te;
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityReapearingBlock te = (TileEntityReapearingBlock) world.getTileEntity(pos);
		
		if(!world.isRemote && !te.isActive()) {
			te.setInitTimer();
			
			return true;
		}
		return true;
	}
	
	public static void activateAdjacentBlocks(World world, BlockPos pos) {
		for(BlockPos addPos : ADJCACENT_BLOCKS) {
			IBlockState blockState = world.getBlockState(pos.add(addPos));
			if(blockState.getBlock() == ModBlocks.REAPEARING_BLOCK) {
				TileEntityReapearingBlock te = (TileEntityReapearingBlock) world.getTileEntity(pos);
				te.setTimer();
			}
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess world, BlockPos pos) {

		TileEntityReapearingBlock te = (TileEntityReapearingBlock) world.getTileEntity(pos);
		if(te != null && te.isActive()){
			return NULL_AABB;
		}
		
		return super.getCollisionBoundingBox(blockState, world, pos);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.TRANSLUCENT;
    }   
	
	@Override
	public boolean isFullCube(IBlockState state){
        return false;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state){
        return false;
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side){
    	TileEntityReapearingBlock te = (TileEntityReapearingBlock) blockAccess.getTileEntity(pos);
    	Arcanacraft.log(te.toString());
    	Arcanacraft.log("Is Active: " + te.isActive());
    	if(te != null && te.isActive()){
			return false;
		}
    	
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

        if (this == ModBlocks.REAPEARING_BLOCK)
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

        return !this.ignoreSimilarity && block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
}
