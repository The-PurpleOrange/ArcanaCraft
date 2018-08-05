package com.tyhone.arcanacraft.common.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class TeleportUtils {
	
	public static Entity warp(Entity entity, BlockPos warpPos, int dimSource, int dimTarget) {
		if(entity == null || entity.world.isRemote){
			return entity;
		}
		if(dimSource == dimTarget) {
			if(entity instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) entity;
				player.connection.setPlayerLocation(warpPos.getX() + 0.5D, warpPos.getY(), warpPos.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
			} else {
				entity.setLocationAndAngles(warpPos.getX() + 0.5D, warpPos.getY(), warpPos.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
			}
			
		}
		else {
			handleWarpDimension(entity, warpPos, dimSource, dimTarget);
		}
		return entity;
	}
	
	public static void warpDimension(Entity entity, BlockPos warpPos) {
		entity.setLocationAndAngles(warpPos.getX() + 0.5D, warpPos.getY(), warpPos.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
	}
	
	public static Entity handleWarpDimension(Entity entity, BlockPos pos, int dimSource, int dimTarget) {
		if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(entity, dimTarget)) return entity;
		
		MinecraftServer server = entity.getServer();
		if(entity instanceof EntityPlayerMP) {
			warpInterDimension(server, (EntityPlayerMP) entity, pos, dimSource, dimTarget);
		}
		else {
			warpInterDimension(server, entity, pos, dimSource, dimTarget);
		}
		return entity;
	}
	
	public static Entity warpInterDimension(MinecraftServer server, Entity entity, BlockPos pos, int dimSource, int dimTarget) {

        WorldServer worldSource = server.getWorld(dimSource);
        WorldServer worldTarget = server.getWorld(dimTarget);
        
		entity.dimension = dimTarget;
		
		worldSource.removeEntity(entity);
		entity.isDead = false;
		entity.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
		worldSource.updateEntityWithOptionalForce(entity, false);
		
        Entity newEntity = EntityList.newEntity(entity.getClass(), worldTarget);
        if(newEntity != null) {
        	//newEntity.copyDataFromOld(entity); //TODO: private?
        	newEntity.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
        	
            boolean flag = newEntity.forceSpawn;
            newEntity.forceSpawn = true;
            worldTarget.spawnEntity(newEntity);
            newEntity.forceSpawn = flag;
            worldTarget.updateEntityWithOptionalForce(newEntity, false);
        }
        
       entity.isDead = true;
       worldSource.resetUpdateEntityTick();
       worldTarget.resetUpdateEntityTick();
       return newEntity;
    }
	
	public static Entity warpInterDimension(MinecraftServer server, EntityPlayerMP player, BlockPos pos, int dimSource, int dimTarget) {

        WorldServer worldSource = server.getWorld(dimSource);
        WorldServer worldTarget = server.getWorld(dimTarget);
        PlayerList playerList = server.getPlayerList();
        
        player.dimension = dimTarget;
        player.connection.sendPacket(new SPacketRespawn(player.dimension, worldTarget.getDifficulty(), worldTarget.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
        playerList.updatePermissionLevel(player);
        worldSource.removeEntityDangerously(player);
        player.isDead = false;
        
        player.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, player.rotationYaw, player.rotationPitch);
        player.connection.setPlayerLocation(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, player.rotationYaw, player.rotationPitch);
        worldTarget.spawnEntity(player);
        worldTarget.updateEntityWithOptionalForce(player, false);
        player.setWorld(worldTarget);
        
        playerList.preparePlayer(player, worldSource);
        player.connection.setPlayerLocation(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, player.rotationYaw, player.rotationPitch);
        player.interactionManager.setWorld(worldTarget);
        player.connection.sendPacket(new SPacketPlayerAbilities(player.capabilities));
        
        playerList.updateTimeAndWeatherForPlayer(player, worldTarget);
        playerList.syncPlayerInventory(player);
        
        for(PotionEffect pe : player.getActivePotionEffects()) {
        	player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), pe));
        }
        
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, dimSource, dimTarget);
        player.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, player.rotationYaw, player.rotationPitch);
        
		return player;
	}
}
