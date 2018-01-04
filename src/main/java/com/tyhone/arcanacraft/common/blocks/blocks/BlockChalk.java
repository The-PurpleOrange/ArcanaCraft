package com.tyhone.arcanacraft.common.blocks.blocks;

import java.util.List;

import com.tyhone.arcanacraft.common.blocks.base.IEnumMeta;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockEnum;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChalk extends ModBlockEnum {

	public enum EnumChalkType implements IEnumMeta, Comparable<EnumChalkType>{
		CHARCOAL,
		BONE,
		BLOOD,
		LAPIS,
		GOLD,
		MAGICITE;
		
		private int meta;
		protected static final EnumChalkType[] VARIANTS = values();
		
		private EnumChalkType(){
			meta = ordinal();
		}

		@Override
		public String toString(){
			return getName();
		}

		@Override
		public int getMeta() {
			return meta;
		}
		
		public static EnumChalkType getById(int meta) {
		    for(EnumChalkType e : values()) {
		        if(e.meta == meta) return e;
		    }
		    return null;
		}
		
		public static EnumChalkType byMeta(int meta){
			return VARIANTS[Math.abs(meta) % VARIANTS.length];
		}
	}
	
	protected static final AxisAlignedBB CHALK_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumChalkType.class);
	
	public BlockChalk() {
		super("chalk_block", EnumChalkType.VARIANTS);
        setDefaultState(getDefaultState().withProperty(VARIANT, EnumChalkType.CHARCOAL));
        //this.setCreativeTab(null);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CHALK_AABB;
    }

    @Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    } 
	
	@Override
	public IBlockState getStateFromMeta(int meta){
    	return this.getDefaultState().withProperty(VARIANT, EnumChalkType.byMeta(meta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state){

        return ((IEnumMeta) state.getValue(VARIANT)).getMeta();
    }

    @Override
    public int damageDropped(IBlockState blockState) {
        return ((IEnumMeta) blockState.getValue(VARIANT)).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> itemStacks)
    {
        for (EnumChalkType chalkVariant : EnumChalkType.VARIANTS) {
            itemStacks.add(new ItemStack(this, 1, chalkVariant.getMeta()));
        }
    }
    
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
        return (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && worldIn.isSideSolid(PosUtil.combinePos(pos, new BlockPos(0,-1,0)), EnumFacing.UP));
    	//return true;
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}
}
