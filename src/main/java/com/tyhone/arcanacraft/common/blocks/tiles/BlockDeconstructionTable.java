package com.tyhone.arcanacraft.common.blocks.tiles;

import java.util.Random;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.tileentity.TileEntityDeconstructionTable;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDeconstructionTable extends ModBlockTileEntityBase{

	public BlockDeconstructionTable() {
		super("deconstruction_table");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDeconstructionTable();
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
        	TileEntityDeconstructionTable te = (TileEntityDeconstructionTable) world.getTileEntity(pos);
            if (te.getStack().isEmpty()) {
                if (!player.getHeldItem(hand).isEmpty()) {
                	if(ItemStackUtil.simpleAreStacksEqual(player.getHeldItem(hand), new ItemStack(ModBlocks.LENS_RECEPTACLE)) && side == EnumFacing.UP){
                		return false;
                	}
                	ItemStack itemStack = player.getHeldItem(hand).copy();
                	itemStack.setCount(1);
                    te.setStack(itemStack);
                    if(player.getHeldItem(hand).getCount()>1){
                    	player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount() - 1);
                    } else{
                    	player.setHeldItem(hand, ItemStack.EMPTY);
                    }
                    player.openContainer.detectAndSendChanges();
                }
            } else {
                ItemStack stack = te.getStack();
                te.setStack(ItemStack.EMPTY);
                if (!player.inventory.addItemStackToInventory(stack)) {
                    EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                    world.spawnEntity(entityItem);
                } else {
                    player.openContainer.detectAndSendChanges();
                }
            }
        }
        
        return true;
    }
	 
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand){
		TileEntityDeconstructionTable te = (TileEntityDeconstructionTable) world.getTileEntity(pos);
		
		for(int i = 0; i < 8; i++){
			if(rand.nextFloat() < te.getPercentage()){
	            spawnEffectParticle(world, pos, rand);
			}
		}
		if(te.getPercentage() > 0.8F){
			for(int i = 0; i < 8; i++){
	            spawnEffectParticle(world, pos, rand);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	private void spawnEffectParticle(World world, BlockPos pos, Random rand){
        double px = (double)pos.getX() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.5D;
        double py = (double)((float)pos.getY() + 0.0625F - ((rand.nextFloat() - 0.5D)/3));
        double pz = (double)pos.getZ() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.5D;

        double mx = ((rand.nextFloat()-0.5D)/50D);
        double mz = ((rand.nextFloat()-0.5D)/50D);
        
        world.spawnParticle(EnumParticleTypes.REDSTONE, px, py+1.25F, pz, mx, 0.01, mz);
	}
	
}
