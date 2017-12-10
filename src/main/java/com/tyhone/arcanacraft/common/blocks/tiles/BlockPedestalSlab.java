package com.tyhone.arcanacraft.common.blocks.tiles;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockTileEntityBase;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestalSlab;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPedestalSlab extends ModBlockTileEntityBase {
	
	public BlockPedestalSlab() {
		super("pedestal_slab");
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPedestalSlab();
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
            TileEntityPedestalSlab te = (TileEntityPedestalSlab) world.getTileEntity(pos);
            if (te.getStack().isEmpty()) {
                if (!player.getHeldItem(hand).isEmpty()) {
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
}
