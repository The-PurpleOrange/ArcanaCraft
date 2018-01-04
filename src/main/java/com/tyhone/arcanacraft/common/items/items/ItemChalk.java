package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.reference.Names;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemChalk extends ModItemBase{

	public ItemChalk() {
		super("chalk", Names.MetaItems.CHALK);
		setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote){
        	boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
		    BlockPos blockpos = flag ? pos : pos.offset(facing);
		
		    if (player.canPlayerEdit(blockpos, facing, player.getHeldItem(hand))){

		        Block block = worldIn.getBlockState(blockpos).getBlock();
		        
		        Block chalkType = ModBlocks.CHALK_BLOCK;
		
		        if (chalkType.canPlaceBlockAt(worldIn, blockpos)) //else if (chalkType.canPlaceBlockAt(worldIn, blockpos))
		        {
		        	worldIn.setBlockState(blockpos, chalkType.getStateFromMeta(player.getHeldItem(hand).getMetadata()));
		        	
		            return EnumActionResult.SUCCESS;
		        }
		    }
        }
        return EnumActionResult.FAIL;
    }
}
