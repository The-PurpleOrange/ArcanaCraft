package com.tyhone.arcanacraft.common.rituals;

import java.util.Random;

import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.api.ritual.RitualType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualSummonLightning extends Ritual{
	
	public RitualSummonLightning(String ritualName, RitualType ritualType) {
		super(ritualName, ritualType);
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos Pos) {
		if(!world.isRemote){
			world.getWorldInfo().setThundering(true);
		}
		return true;
	}

}
