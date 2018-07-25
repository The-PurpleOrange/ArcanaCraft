package com.tyhone.arcanacraft.common.items.items.tools;

import com.tyhone.arcanacraft.common.items.base.ModItemBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemToolWandGrapple extends ModItemBase{

	public ItemToolWandGrapple(){
		super("tool_wand_grapple");
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote){
			RayTraceResult tracePos = player.rayTrace(40, 0);
			BlockPos placePos = tracePos.getBlockPos().offset(tracePos.sideHit);
			BlockPos placePosNormal = tracePos.getBlockPos();
			
			float camYaw = player.cameraYaw;
			float camPitch = player.cameraPitch;
			float speed = player.getAIMoveSpeed();
			EnumFacing side = tracePos.sideHit;
			
			double x = placePos.getX()+0.5D;
			double y = side == EnumFacing.UP ? placePos.getY() : placePos.getY()-1D;
			double z = placePos.getZ()+0.5D;
			
			boolean ported = false;
			if(!player.isSneaking()){
				BlockPos portBlock = new BlockPos(x, y, z);
				if(!world.getBlockState(portBlock).causesSuffocation()){
					if(!world.getBlockState(portBlock.add(0, -1, 0)).causesSuffocation()){
						player.setPositionAndUpdate(x, y, z);
						ported = true;
					}
					else if(!world.getBlockState(portBlock.add(0, 1, 0)).causesSuffocation()){
						player.setPositionAndUpdate(x, y, z);
						ported = true;
					}
				}
				else if(!world.getBlockState(portBlock.add(0, 1, 0)).causesSuffocation() && !world.getBlockState(portBlock.add(0, 2, 0)).causesSuffocation()){
					player.setPositionAndUpdate(x, y+1D, z);
					ported = true;
				}
			}else{
				BlockPos portBlock = new BlockPos(placePosNormal.getX()+0.5D, placePosNormal.getY()+1, placePosNormal.getZ()+0.5D);
				if(!world.getBlockState(portBlock).causesSuffocation() && !world.getBlockState(portBlock.add(0, 1, 0)).causesSuffocation()){
					player.setPositionAndUpdate(placePosNormal.getX()+0.5D, placePosNormal.getY()+1D, placePosNormal.getZ()+0.5D);
					ported = true;
				}
				else if(!world.getBlockState(portBlock.add(0, 1, 0)).causesSuffocation() && !world.getBlockState(portBlock.add(0, 2, 0)).causesSuffocation()){
					player.setPositionAndUpdate(placePosNormal.getX()+0.5D, placePosNormal.getY()+2D, placePosNormal.getZ()+0.5D);
					ported = true;
				}
			}
			
			if(ported){
				player.fallDistance = 0;
				player.cameraYaw = camYaw;
				player.cameraPitch = camPitch;
				player.setAIMoveSpeed(speed);
		        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
			}
		}
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}
	
	/*@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		return onItemRightClick(world, player, hand).getType();
		//return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
	}*/
}
