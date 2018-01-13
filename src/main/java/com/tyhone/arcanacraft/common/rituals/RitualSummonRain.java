package com.tyhone.arcanacraft.common.rituals;

import com.tyhone.arcanacraft.api.ritual.RitualBase;
import com.tyhone.arcanacraft.api.ritual.RitualTypeBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualSummonRain extends RitualBase{
	
	public RitualSummonRain(String ritualName, RitualTypeBase ritualType) {
		super(ritualName, ritualType);
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos Pos) {
		if(!world.isRemote){
			world.getWorldInfo().setRaining(true);
		}
		return true;
	}

}
