package com.tyhone.arcanacraft.api.ritual;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModRituals;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class RitualTypeBase extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<RitualTypeBase> implements IRitualType{

	private ResourceLocation registryName;
	private String ritualName;
	private List<RitualBase> ritualTypeList;
	
	public RitualTypeBase(String ritualName, List<RitualBase> ritualTypeList) {
		this.ritualName = ritualName;
		this.registryName = new ResourceLocation(Arcanacraft.MODID, ritualName);
		this.ritualTypeList = ritualTypeList;
		ModRituals.register(this);
	}

	@Override
	public String getUnlocalizedName(){
		return "ritual." + Arcanacraft.MODID + ":" + ritualName;
    }

	public String getDisplayName(){
        return I18n.translateToLocal(this.getUnlocalizedName() + ".name").trim();
    }

	@Override
	public List<RitualBase> getRitualList() {
		return this.ritualTypeList;
	}
}
