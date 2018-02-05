package com.tyhone.arcanacraft.common.blocks.blocks;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockAquelsa extends ModBlockBase {

    public static final PropertyInteger LEVEL = BlockFluidClassic.LEVEL;
    
	public BlockAquelsa() {
		super("aquelsa", Material.WATER);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, Integer.valueOf(7)));
	
	}
	
    @Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LEVEL});
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
    	return this.blockState.getBaseState().withProperty(LEVEL, Integer.valueOf(7));
    }
    
    @Override
	public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
}
