package com.tyhone.arcanacraft.common.rituals;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.ritual.Ritual;
import com.tyhone.arcanacraft.api.ritual.RitualType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualPlaceChalk extends Ritual{
	
    public RitualPlaceChalk(String ritualName, RitualType ritualType) {
		super(ritualName, ritualType);
	}
	
	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos Pos) {
		return false;
	}

}
