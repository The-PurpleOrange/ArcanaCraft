package com.tyhone.arcanacraft.common.rituals;

import com.tyhone.arcanacraft.api.ritual.IRitual;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualSummonRain implements IRitual{

	private String ritualName = "summon_rain";
	
	@Override
	public String getRitualName() {
		return ritualName;
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos Pos) {
		if(!world.isRemote){
			world.setRainStrength(0.2f);
		}
		return true;
	}

}
