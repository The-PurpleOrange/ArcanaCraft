package com.tyhone.arcanacraft.common.blocks.tiles;

import java.util.Random;

import com.tyhone.arcanacraft.api.ritual.IRitualCircle;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.items.items.tools.ItemToolWarpHeart;
import com.tyhone.arcanacraft.common.tileentity.TileEntityAlchemicArray;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWarpCircle extends ModBlockBase implements IRitualCircle{
	
	public final static BlockPos[] WARP_CIRCLE_BLOCKPOS_ARRAY = {new BlockPos(1, 0, 1), new BlockPos(-1, 0, -1), new BlockPos(1, 0, -1), new BlockPos(-1, 0, 1)};
	public final static int CHALK_META = ItemMetaUtil.chalk("gold");

	protected static final AxisAlignedBB ARRAY = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	
	public BlockWarpCircle() {
		super("warp_circle", Material.CIRCUITS);
		this.setHardness(0.5f);
		this.setResistance(3f);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return ARRAY;
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return null;
    }
    
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return ItemStack.EMPTY;
    }

    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
    
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
        return (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && worldIn.isSideSolid(pos.add(0, -1, 0), EnumFacing.UP));
    }
    
    @Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos){
        if (!worldIn.isRemote){
            if(!worldIn.isSideSolid(pos.add(0, -1, 0), EnumFacing.UP)){
                worldIn.setBlockToAir(pos);
            }
        }
    }
	
}
