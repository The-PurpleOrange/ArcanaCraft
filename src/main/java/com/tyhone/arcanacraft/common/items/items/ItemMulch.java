package com.tyhone.arcanacraft.common.items.items;

import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMulch extends ModItemBase{

	public ItemMulch() {
		super("mulch");
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		
		ItemStack itemstack = player.getHeldItem(hand);
		
		if (applyBonemeal(itemstack, worldIn, pos, player, hand)){
            if (!worldIn.isRemote){
                worldIn.playEvent(2005, pos, 0);
            }

            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
	
	public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target, EntityPlayer player, @javax.annotation.Nullable EnumHand hand)
    {
        IBlockState iblockstate = worldIn.getBlockState(target);

        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, target, iblockstate, stack, hand);
        if (hook != 0) return hook > 0;

        if (iblockstate.getBlock() instanceof IGrowable){
            IGrowable igrowable = (IGrowable)iblockstate.getBlock();

            if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)){
                if (!worldIn.isRemote){
                    if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate)){
                        igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
                    }

                    stack.shrink(1);
                }

                return true;
            }
        }

        return false;
    }
}
