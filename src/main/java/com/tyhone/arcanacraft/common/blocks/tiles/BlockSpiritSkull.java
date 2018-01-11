package com.tyhone.arcanacraft.common.blocks.tiles;

import java.util.Random;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.tileentity.TileEntitySpiritSkull;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpiritSkull extends ModBlockTileEntityBase{

	//public static final PropertyDirection FACING = BlockDirectional.FACING;
    public static final PropertyBool NODROP = PropertyBool.create("nodrop");
    /*private static final Predicate<BlockWorldState> IS_WITHER_SKELETON = new Predicate<BlockWorldState>()
    {
        public boolean apply(@Nullable BlockWorldState p_apply_1_)
        {
            return p_apply_1_.getBlockState() != null && p_apply_1_.getBlockState().getBlock() == Blocks.SKULL && p_apply_1_.getTileEntity() instanceof TileEntitySkull && ((TileEntitySkull)p_apply_1_.getTileEntity()).getSkullType() == 1;
        }
    };*/
    //protected static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
    //protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
    //protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
    //protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
    //protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
	
	public BlockSpiritSkull() {
		super("spirit_skull_block");
        //this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(NODROP, Boolean.valueOf(false)));
		// TODO Auto-generated constructor stub
	}
	
	/*public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch ((EnumFacing)state.getValue(FACING))
        {
            case UP:
            default:
                return DEFAULT_AABB;
            case NORTH:
                return NORTH_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            case EAST:
                return EAST_AABB;
        }
    }*/
	

    /*public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(NODROP, Boolean.valueOf(false));
    }*/
    

    @Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntitySpiritSkull();
    }
    
    @Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModItems.SPIRIT_SKULL, 1);
    }

    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.SPIRIT_SKULL;
    }
    
    @Override
	public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune)
    {
        drops.add(new ItemStack(ModItems.SPIRIT_SKULL, 1));
    }
    
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
        return (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && worldIn.isSideSolid(pos.add(0, -1, 0), EnumFacing.UP));
    }
    
    @Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos){
        if (!worldIn.isRemote){
            if(!worldIn.isSideSolid(pos.add(0, -1, 0), EnumFacing.UP)){
                this.dropBlockAsItem(worldIn, pos, state, 0);
                worldIn.setBlockToAir(pos);
            }
        }
    }

}
