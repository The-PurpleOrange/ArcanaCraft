package com.tyhone.arcanacraft.api.ritual;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModRituals;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualBase implements IRitual{

	private String ritualName;
	
	public RitualBase(String ritualName) {
		this.ritualName = ritualName;
		ModRituals.register(this);
	}
	
	@Override
	public String getRitualName() {
		return ritualName;
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos pos) {
		Arcanacraft.logger.error("TRIED TO PREFORM RITUAL IN BASE CLASS");
		return false;
	}

}
