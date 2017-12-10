package com.tyhone.arcanacraft.common.blocks.tiles;

import com.tyhone.arcanacraft.common.blocks.base.ModBlockBase;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.tileentity.TileEntityPedestal;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPedestal extends ModBlockBase implements ITileEntityProvider{

	// Used for visuals only
	//public static final IProperty<Boolean> IS_HANDLES = PropertyBool.create("is_handles");
	
	public BlockPedestal() {
		super("pedestal");
        //setDefaultState(blockState.getBaseState().withProperty(IS_HANDLES, false));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPedestal();
	}
	
	/*@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos){
		return state.withProperty(IS_HANDLES, false);
	}
	
	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, IS_HANDLES);
	}*/
	
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
            TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(pos);
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
                // There is a stack in the pedestal. In this case we remove it and try to put it in the
                // players inventory if there is room
                ItemStack stack = te.getStack();
                te.setStack(ItemStack.EMPTY);
                if (!player.inventory.addItemStackToInventory(stack)) {
                    // Not possible. Throw item in the world
                    EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                    world.spawnEntity(entityItem);
                } else {
                    player.openContainer.detectAndSendChanges();
                }
            }
        }

        // Return true also on the client to make sure that MC knows we handled this and will not try to place
        // a block on the client
        return true;
    }
	
}
