package com.tyhone.arcanacraft.client;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.ResourceLocationHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleWind extends Particle{

	
	private float sr;
	private float sg;
	private float sb;
	
	private float er;
	private float eg;
	private float eb;
	
	private float particleScaleMax;
	private float alpha = 0.5f;

	double centreX;
	double centreY;
	double yConstant;
	double centreZ;
	
	double speed;
	double size;
	
	Entity entity;
	
	
	private final ResourceLocation orbRL = ResourceLocationHelper.getModelResourceLocation("entity/orb_fx");
	
	public ParticleWind(World world, double xc, double yc, double zc, double xs, double ys, double zs, float scale, int startColour, int endColour, Entity entity, double speed, double size, int lifespan) {
		super(world, entity.posX + xc, entity.posY + yc, entity.posZ + xc, xs, ys, zs);
		
		this.entity = entity;

		centreX = entity.posX + xc;
		centreY = entity.posY + yc;
		yConstant = yc;
		centreZ = entity.posZ + zc;
		
		sr = ((startColour & 0xFF0000) >> 16)/255;
	    sg = ((startColour & 0xFF00) >> 8)/255;
	    sb = (startColour & 0xFF)/255;
	    
		er = ((endColour & 0xFF0000) >> 16)/255;
	    eg = ((endColour & 0xFF00) >> 8)/255;
	    eb = (endColour & 0xFF)/255;
		
	    this.particleRed = sr;
        this.particleGreen = sg;
        this.particleBlue = sb;
        
        this.speed = speed;
        this.size = size;
        
		particleGravity = Blocks.FIRE.blockParticleGravity;
		particleMaxAge = lifespan;

		particleScaleMax = scale;
		particleScale = 0;
		
		particleAlpha = 0;
		
		/*motionX = xs;
		motionY = ys;
		motionZ = zs;*/
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(orbRL.toString());
		setParticleTexture(sprite);
	}
	
	@Override
	public int getFXLayer(){
		return 1;
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return true;
	}
	
	@Override
	public void onUpdate() {
		
		centreX = entity.posX;
		centreY = entity.posY + yConstant;
		centreZ = entity.posZ;
		
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		float lf = (float) this.particleAge / (float) this.particleMaxAge;
		
		//move(motionX*lf, motionY*lf, motionZ*lf);

		this.posX = this.centreX + Math.cos(2D * Math.PI * ((this.particleAge) / 20D) / speed)*size;
		this.posZ = this.centreZ + Math.sin(2D * Math.PI * ((this.particleAge) / 20D) / speed)*size;
		this.posY = this.centreY;
		
		if(this.particleAge++ >= this.particleMaxAge){
			this.setExpired();
		}

		float r = (float)Math.abs((lf * er) + ((1 - lf) * sr));
		float g = (float)Math.abs((lf * eg) + ((1 - lf) * sg));
		float b = (float)Math.abs((lf * eb) + ((1 - lf) * sb));

		setRBGColorF(r, g, b);

		//float a = ((alpha * lf)*-1) + alpha;
		setAlphaF(alpha);
		
		
		float ss = 0.5f;
		if(lf>ss){
			float nlf = ((lf-ss) * this.particleScaleMax) / (1f - ss);
			float ns = this.particleScaleMax - nlf;
			this.particleScale = ns;
		}
		else if(lf<ss && particleScale < particleScaleMax){
			this.particleScale = lf * (particleScaleMax/ss);
		}
		
		//this.particleScale = this.particleScaleMax - (lf * this.particleScaleMax);
	}
}
