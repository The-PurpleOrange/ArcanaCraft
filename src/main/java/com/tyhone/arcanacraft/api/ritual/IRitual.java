package com.tyhone.arcanacraft.api.ritual;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IRitual {
	
	public String getUnlocalizedName();
	
	public boolean preformRitual(World world, EntityPlayer player, BlockPos pos);
}
