package com.tyhone.arcanacraft.common.blocks.tiles;

import java.util.Random;

import com.tyhone.arcanacraft.api.ritual.IRitualBuilder;
import com.tyhone.arcanacraft.api.ritual.IRitualCircle;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.tileentity.TileEntityRitualCircle;

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

public class BlockRitualCircle extends ModBlockTileEntityBase implements IRitualCircle{

	
	protected static final AxisAlignedBB ARRAY = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	
	public BlockRitualCircle() {
		super("ritual_circle", Material.CIRCUITS);
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
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRitualCircle();
	}
	
	@Override
	public int getMetaFromState(IBlockState state){
		return 0;
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(player.getHeldItem(hand).getItem() instanceof IRitualBuilder){
			return false;
		}
		if (!world.isRemote) {
			TileEntityRitualCircle te = (TileEntityRitualCircle) world.getTileEntity(pos);
			te.onActivated(player, world);
		}
        return true;
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
