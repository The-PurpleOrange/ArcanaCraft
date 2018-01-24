package com.tyhone.arcanacraft.common.items.items.tools;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModBlocks;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.PosUtil;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemToolWandIllumination extends ModItemBase{

	public ItemToolWandIllumination(){
		super("tool_wand_illumination");
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote){
			RayTraceResult tracePos = player.rayTrace(40, 0);
			BlockPos placePos = tracePos.getBlockPos().offset(tracePos.sideHit);
			
			boolean ported = false;
			if(!player.isSneaking()){
				if(world.getBlockState(placePos).getBlock().isReplaceable(world, placePos)){
					world.setBlockState(placePos, ModBlocks.ALCHEMIC_LIGHT.getDefaultState());
				}
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
