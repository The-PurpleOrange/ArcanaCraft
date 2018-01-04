package com.tyhone.arcanacraft.common.blocks.blocks;

import com.tyhone.arcanacraft.common.blocks.base.IEnumMeta;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockEnum;
import com.tyhone.arcanacraft.common.blocks.blocks.BlockAlchemicStone.EnumAlchemicStoneType;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlchemicStone extends ModBlockEnum {

	public enum EnumAlchemicStoneType implements IEnumMeta, Comparable<EnumAlchemicStoneType>{
		UNIFORM,
		SMOOTH,
		TILE,
		SMOOTH_TILE;
		
		private int meta;
		protected static final EnumAlchemicStoneType[] VARIANTS = values();
		
		private EnumAlchemicStoneType(){
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
		
		public static EnumAlchemicStoneType getById(int meta) {
		    for(EnumAlchemicStoneType e : values()) {
		        if(e.meta == meta) return e;
		    }
		    return null;
		}
		
		public static EnumAlchemicStoneType byMeta(int meta){
			return VARIANTS[Math.abs(meta) % VARIANTS.length];
		}
	}
	
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumAlchemicStoneType.class);
	
	public BlockAlchemicStone() {
		super("alchemic_stone", EnumAlchemicStoneType.VARIANTS);
        setDefaultState(getDefaultState().withProperty(VARIANT, EnumAlchemicStoneType.UNIFORM));
        //this.setCreativeTab(null);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
    	return this.getDefaultState().withProperty(VARIANT, EnumAlchemicStoneType.byMeta(meta));
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
        for (EnumAlchemicStoneType alchemicStoneVariant : EnumAlchemicStoneType.VARIANTS) {
            itemStacks.add(new ItemStack(this, 1, alchemicStoneVariant.getMeta()));
        }
    }
    
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
    	//return worldIn.isSideSolid(pos, EnumFacing.UP);
    	return true;
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}
}
