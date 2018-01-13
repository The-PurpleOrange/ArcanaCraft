package com.tyhone.arcanacraft.common.rituals;

import java.util.Random;

import com.tyhone.arcanacraft.api.ritual.RitualBase;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrandRitualSummonCreeper extends RitualBase{
	
    public GrandRitualSummonCreeper(String ritualName) {
		super(ritualName);
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
