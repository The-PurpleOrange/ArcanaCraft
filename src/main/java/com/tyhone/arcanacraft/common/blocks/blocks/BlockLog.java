package com.tyhone.arcanacraft.common.blocks.blocks;

import com.tyhone.arcanacraft.common.blocks.base.IEnumMeta;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockEnum;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLog extends ModBlockEnum {

	public enum EnumLogType implements IEnumMeta, Comparable<EnumLogType>{
		EVOLITE_WOOD,
		EVOLITE_SAP;
		
		private int meta;
		protected static final EnumLogType[] VARIANTS = values();
		
		private EnumLogType(){
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
		
		public static EnumLogType getById(int meta) {
		    for(EnumLogType e : values()) {
		        if(e.meta == meta) return e;
		    }
		    return null;
		}
		
		public static EnumLogType byMeta(int meta){
			return VARIANTS[Math.abs(meta) % VARIANTS.length];
		}
	}
	
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumLogType.class);
	
	public BlockLog() {
		super("log", Material.WOOD, EnumLogType.VARIANTS);
        setDefaultState(getDefaultState().withProperty(VARIANT, EnumLogType.EVOLITE_WOOD));
        this.setHardness(2f);
        this.setResistance(10f);
        this.setSoundType(SoundType.WOOD);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
    	return this.getDefaultState().withProperty(VARIANT, EnumLogType.byMeta(meta));
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
        for (EnumLogType logVarient : EnumLogType.VARIANTS) {
            itemStacks.add(new ItemStack(this, 1, logVarient.getMeta()));
        }
    }
    
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
    	//return worldIn.isSideSolid(pos, EnumFacing.UP);
    	return true;
    }
    
    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return true;
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}
}
