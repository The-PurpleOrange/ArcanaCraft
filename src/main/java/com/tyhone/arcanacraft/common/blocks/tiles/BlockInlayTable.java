package com.tyhone.arcanacraft.common.blocks.tiles;

import java.util.Random;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.init.ModItems;
import com.tyhone.arcanacraft.common.tileentity.TileEntityInlayTable;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInlayTable extends ModBlockTileEntityBase{

	public BlockInlayTable() {
		super("inlay_table");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityInlayTable();
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
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		if(player.getActiveHand() == EnumHand.MAIN_HAND && player.getHeldItem(EnumHand.MAIN_HAND).getItem() == ModItems.HAMMER) {
			if(!world.isRemote) {
	        	TileEntityInlayTable te = (TileEntityInlayTable) world.getTileEntity(pos);
	        	te.hammered();
			}/*else {
				Random rand = new Random();
				for(int i = 0; i < 8; i++){
		            spawnEffectParticle(world, pos, rand);
				}
			}*/
		}
		else {
			super.onBlockClicked(world, pos, player);
		}
	}
	
	 @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && hand == EnumHand.MAIN_HAND) {
        	TileEntityInlayTable te = (TileEntityInlayTable) world.getTileEntity(pos);
        	
        	if(player.isSneaking() || player.getHeldItem(hand).isEmpty()) {
        		ItemStack itemStack = te.removeItem();
        		if(!itemStack.isEmpty()){
        			if (!player.inventory.addItemStackToInventory(itemStack)) {
                        EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), itemStack);
                        world.spawnEntity(entityItem);
                    } else {
                        player.openContainer.detectAndSendChanges();
                    }
        		}
        	}
        	else if (!player.getHeldItem(hand).isEmpty()){
        		ItemStack itemStack = player.getHeldItem(hand).copy();
        		itemStack.setCount(1);
        		if (te.addItem(itemStack)) {
	        		player.getHeldItem(hand).shrink(1);
	                player.openContainer.detectAndSendChanges();
        		}
        	}
        }
        
        return true;
    }

	@SideOnly(Side.CLIENT)
	public static void spawnEffectParticle(World world, BlockPos pos, Random rand){
        double px = (double)pos.getX() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.5D;
        double py = (double)((float)pos.getY() + 0.0625F - ((rand.nextFloat() - 0.5D)/3));
        double pz = (double)pos.getZ() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.5D;

        double mx = ((rand.nextFloat()-0.5D)/50D);
        double mz = ((rand.nextFloat()-0.5D)/50D);
        
        world.spawnParticle(EnumParticleTypes.REDSTONE, px, py+1.25F, pz, mx, 0.01, mz);
        Arcanacraft.log("poof");
	}
	
}
