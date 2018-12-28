package com.tyhone.arcanacraft.common.items.items.tools;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.items.base.ModItemBase;
import com.tyhone.arcanacraft.common.util.PlayerUtils;

import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemToolWandDebug extends ModItemBase{

	public ItemToolWandDebug(){
		super("tool_wand_debug");
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(world.isRemote){
			RayTraceResult tracePos = player.rayTrace(40, 0);
			BlockPos placePos = tracePos.getBlockPos();

			PlayerUtils.sendPlayerMessage(player, world, world.getBlockState(placePos).toString());
			ImmutableMap<IProperty<?>, Comparable<?>> map = world.getBlockState(placePos).getProperties();
			ImmutableSet<IProperty<?>> key = map.keySet();
			for(int i = 0; i < map.size(); i++) {
				PlayerUtils.sendPlayerMessage(player, world, "Key " + i + ": " + key.asList().get(i)+ ":" + map.get(key.asList().get(i)).toString());
			}
			Arcanacraft.log(map.toString());
		}
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}
}
