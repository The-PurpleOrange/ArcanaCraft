package com.tyhone.arcanacraft.common.entity;

import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIHomunculusAttack extends EntityAIAttackMelee{
    private int raiseArmTicks;
    private EntityHomunculus entity;

	public EntityAIHomunculusAttack(EntityHomunculus entity, double speed, boolean longMemory){
		super(entity, speed, longMemory);
		this.entity = entity;
	}
	
	@Override
	public void startExecuting(){
		super.startExecuting();
		this.raiseArmTicks = 0;
	}
	
	@Override
	public void resetTask(){
		super.resetTask();
		this.entity.setArmsRaised(false);
	}
	
	@Override
	public void updateTask(){
		super.updateTask();
		this.raiseArmTicks++;
		
		if(this.raiseArmTicks >= 5 && this.attackTick < 10){
			this.entity.setArmsRaised(true);
		} else{
			this.entity.setArmsRaised(false);
		}
	}
}
