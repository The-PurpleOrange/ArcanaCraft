package com.tyhone.arcanacraft.api.ritual;

import com.tyhone.arcanacraft.Arcanacraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualBase implements IRitual{

	@Override
	public String getRitualName() {
		// TODO Auto-generated method stub
		return "base - ERROR";
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos pos) {
		Arcanacraft.logger.info("TRIED RITUAL IN BASE CLASS");
		return false;
	}

}
