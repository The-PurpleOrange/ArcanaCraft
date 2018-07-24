package com.tyhone.arcanacraft.common.blocks.blocks;

import java.util.Random;

import com.tyhone.arcanacraft.common.blocks.base.IEnumMeta;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockEnum;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
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
	
	public static enum EnumAxis implements IStringSerializable{
		X("x"),
		Y("y"),
		Z("z"),
		NONE("none");
		
		private final String name;
		
		private EnumAxis(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		public static BlockLog.EnumAxis fromFacingAxis(EnumFacing.Axis axis){
			switch (axis) {
			case X: return X;
			case Y: return Y;
			case Z: return Z;
			default: return NONE;
			}
		}
		
		@Override
		public String getName() {
			return this.name;
		}
	}
	
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumLogType.class);
    public static final PropertyEnum<BlockLog.EnumAxis> LOG_AXIS = PropertyEnum.<BlockLog.EnumAxis>create("axis", BlockLog.EnumAxis.class);
	
	public BlockLog() {
		super("log", Material.WOOD, EnumLogType.VARIANTS);
        setDefaultState(getDefaultState().withProperty(VARIANT, EnumLogType.EVOLITE_WOOD).withProperty(LOG_AXIS,  EnumAxis.Y));
        this.setHardness(2f);
        this.setResistance(10f);
        this.setSoundType(SoundType.WOOD);
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getStateFromMeta(meta).withProperty(LOG_AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		
		IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumLogType.byMeta((meta & 3) % 4));
		
		switch (meta & 12) {
			case 0:
				iblockstate = iblockstate.withProperty(LOG_AXIS,  EnumAxis.Y);
				break;
			case 4:
				iblockstate = iblockstate.withProperty(LOG_AXIS,  EnumAxis.X);
				break;
			case 8:
				iblockstate = iblockstate.withProperty(LOG_AXIS,  EnumAxis.Z);
				break;
			default:
				iblockstate = iblockstate.withProperty(LOG_AXIS,  EnumAxis.NONE);
		}
		
		return iblockstate;
    	//return this.getDefaultState().withProperty(VARIANT, EnumLogType.byMeta(meta));
    }
    
    @SuppressWarnings("incomplete-switch")
	@Override
    public int getMetaFromState(IBlockState state){

    	int i = 0;
    	i = i | ((EnumLogType)state.getValue(VARIANT)).getMeta();
    	
    	switch(state.getValue(LOG_AXIS)) {
	    	case X:
	    		i |= 4;
	    		break;
	    	case Z:
	    		i |= 8;
	    		break;
	    	case NONE:
	    		i |= 12;
    	}
    	
    	return i;
    	
        //return ((IEnumMeta) state.getValue(VARIANT)).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> itemStacks)
    {
        for (EnumLogType logVarient : EnumLogType.VARIANTS) {
            itemStacks.add(new ItemStack(this, 1, logVarient.getMeta()));
        }
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    	return Item.getItemFromBlock(this.getDefaultState().getBlock());
    }
    
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,int fortune) {
    	//List<ItemStack> items = new ArrayList<>();
    	drops.add(new ItemStack(ModBlocks.LOG, 1));
    	if(state.getValue(VARIANT) == EnumLogType.EVOLITE_SAP) {
    		
    		int amount = new Random().nextInt(3);
    		drops.add(new ItemStack(ModItems.ITEM, amount + 1, ItemMetaUtil.item("evolite_sap")));
    	}
    	//super.getDrops(drops, world, pos, state, fortune);
    }
    
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
    	return true;
    }
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    	if(worldIn.isAreaLoaded(pos.add(-5,  -5,  -5), pos.add(5,  5,  5))) {
    		for(BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4))) {
    			IBlockState iblockstate = worldIn.getBlockState(blockpos);
    			
    			if(iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
    				iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
    			}
    		}
    	}
    }
    
    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return true;
    }
    
    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos) {
    	return true;
    }
    
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT, LOG_AXIS});
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumLogType)state.getValue(VARIANT)).getMeta());
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumLogType)state.getValue(VARIANT)).getMeta();
	}
}
