package com.tyhone.arcanacraft.client;

import com.tyhone.arcanacraft.common.util.ResourceLocationHelper;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStitcherFX {

	@SubscribeEvent
	public void stitcherEventPre(TextureStitchEvent.Pre event){
		ResourceLocation orbRL = ResourceLocationHelper.getModelResourceLocation("entity/orb_fx");
		event.getMap().registerSprite(orbRL);

		ResourceLocation starRL = ResourceLocationHelper.getModelResourceLocation("entity/star_fx");
		event.getMap().registerSprite(starRL);
	}
}
