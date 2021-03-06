package com.tyhone.arcanacraft.common.rituals;

import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.api.ritual.RitualType;
import com.tyhone.arcanacraft.common.entity.EntityHomunculus;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrandRitualCreateChicken extends Ritual{
	
    public GrandRitualCreateChicken(String ritualName, RitualType ritualType) {
		super(ritualName, ritualType);
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos pos) {
		if(!world.isRemote){
			EntityChicken chicken = new EntityChicken(world);
			chicken.setPosition(pos.getX(), pos.getY(), pos.getZ());
			world.spawnEntity(chicken);
		}
		return true;
	}

}
