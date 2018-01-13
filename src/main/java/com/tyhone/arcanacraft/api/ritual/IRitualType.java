package com.tyhone.arcanacraft.api.ritual;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IRitualType {
	
	public String getUnlocalizedName();
	
	public List<RitualBase> getRitualList();
}
