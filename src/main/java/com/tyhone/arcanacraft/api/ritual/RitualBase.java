package com.tyhone.arcanacraft.api.ritual;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModRituals;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualBase extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<RitualBase> implements IRitual{

	private ResourceLocation registryName;
	private String ritualName;
	
	public RitualBase(String ritualName) {
		this.ritualName = ritualName;
		this.registryName = new ResourceLocation(Arcanacraft.MODID, ritualName);
		ModRituals.register(this);
	}
	
	@Override
	public String getLocalizedName(){
		return ritualName;
	}

	public String getUnlocalizedName(){
		return Arcanacraft.MODID + "." + ritualName;
    }

	public String getDisplayName(){
		return Arcanacraft.MODID + "." + ritualName + ".name";
    }

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos pos) {
		Arcanacraft.logger.error("TRIED TO PREFORM RITUAL IN BASE CLASS");
		return false;
	}

}
