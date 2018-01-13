package com.tyhone.arcanacraft.common.rituals;

import com.tyhone.arcanacraft.api.ritual.RitualBase;
import com.tyhone.arcanacraft.api.ritual.RitualTypeBase;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrandRitualSummonCreeper extends RitualBase{
	
    public GrandRitualSummonCreeper(String ritualName, RitualTypeBase ritualType) {
		super(ritualName, ritualType);
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos pos) {
		if(!world.isRemote){
			EntityChicken creeper = new EntityChicken(world);
			creeper.setPosition(pos.getX(), pos.getY(), pos.getZ());
			world.spawnEntity(creeper);
		}
		return true;
	}

}
