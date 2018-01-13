package com.tyhone.arcanacraft.api.ritual;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.init.ModRituals;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class RitualBase extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<RitualBase> implements IRitual{

	private ResourceLocation registryName;
	private String ritualName;
	private RitualTypeBase ritualType;
	
	public RitualBase(String ritualName, RitualTypeBase ritualType){ //ModRituals.RITUAL_TYPE_STANDARD){
		this.ritualName = ritualName;
		this.registryName = new ResourceLocation(Arcanacraft.MODID, ritualName);
		this.ritualType = ritualType;
		ModRituals.register(this);
	}

	@Override
	public String getUnlocalizedName(){
		return "ritual." + Arcanacraft.MODID + ":" + ritualName;
    }

	public String getDisplayName(){
        return I18n.translateToLocal(this.getUnlocalizedName() + ".name").trim();
    }
	
	public RitualTypeBase getRitualType(){
		return ritualType;
	}

	@Override
	public boolean preformRitual(World world, EntityPlayer player, BlockPos pos) {
		Arcanacraft.logger.error("TRIED TO PREFORM RITUAL IN BASE CLASS");
		return false;
	}
}
