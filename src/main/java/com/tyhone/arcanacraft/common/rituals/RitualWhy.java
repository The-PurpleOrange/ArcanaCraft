package com.tyhone.arcanacraft.common.rituals;

import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.api.ritual.RitualType;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualWhy extends Ritual{
	
	public RitualWhy(String ritualName, RitualType ritualType) {
		super(ritualName, ritualType);
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos pos) {
		if(!world.isRemote){
			world.spawnEntity(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
		}
		return true;
	}
	
	@Override
	public boolean preformRepeatingRitual(World world, BlockPos pos) {
		if(!world.isRemote){
			EntityChicken chicken = new EntityChicken(world);
			chicken.setPosition(pos.getX()+0.5D, pos.getY(), pos.getZ()+0.5D);
			world.spawnEntity(chicken);
		}
		return true;
	}

	@Override
	public Boolean repeatable() {
		return true;
	}
	
	@Override
	public int getMaxCooldown() {
		return 40;
	}

}
