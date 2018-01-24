package com.tyhone.arcanacraft.common.blocks.blocks;

import java.util.Random;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.util.ItemMetaUtil;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlchemicLight extends ModBlockBase {

	public BlockAlchemicLight() {
		super("alchemic_light");
		this.setHardness(0f);
		this.setResistance(0f);
		this.setLightLevel(0.9375F);
		this.setSoundType(SoundType.SNOW);
        this.setTickRandomly(true);
	}

	protected static final AxisAlignedBB ALCHEMIC_LIGHT_AABB = new AxisAlignedBB(0.25, 0.25, 0.25, 0.75, 0.75, 0.75);

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return ALCHEMIC_LIGHT_AABB;
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return null;
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
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    } 
	
    @Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        int meta = state.getBlock().getMetaFromState(state);

        double x = pos.getX()+0.5D;
        double y = pos.getY()+0.5D;
        double z = pos.getZ()+0.5D;

        double sx = ((rand.nextDouble()-0.5D)/30D);
        double sy = ((rand.nextDouble()-0.5D)/20D)+0.03D;
        double sz = ((rand.nextDouble()-0.5D)/30D);
        
        world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, sx, sy, sz);

    }
}
