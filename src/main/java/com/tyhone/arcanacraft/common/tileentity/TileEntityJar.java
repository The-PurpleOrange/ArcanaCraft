package com.tyhone.arcanacraft.common.tileentity;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.registries.TinktureStack;
import com.tyhone.arcanacraft.api.registries.TinktureType;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.tileentity.base.ModTileEntityBase;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityJar extends ModTileEntityBase {
	
	private final int maxFluid = 32;
	private TinktureStack tinktureStack = new TinktureStack(ModTinktureTypes.EMPTY, 0);
	
	public int getFluidLevel(){
		return tinktureStack.getAmount();
	}

	public TinktureStack getTinktureStack(){
		return tinktureStack;
	}
	
	public TinktureType getFluidType(){
		return tinktureStack.getTinktureType();
	}
	
	public int getMaxFluid(){
		return maxFluid;
	}
	
	public void setFluidType(TinktureType type){
		tinktureStack.setTinktureType(type);
		markForClean();
	}
	
	public boolean removeFluid(int amount){
		if((tinktureStack.getAmount() - amount) < 0){
			return false;
		}
		else{
			tinktureStack.modifyAmount(-amount);
			markForClean();
			return true;
		}
	}
	
	public int removeFluidPartial(int amount){
		if((tinktureStack.getAmount() - amount) < 0){
			int remainder = ((tinktureStack.getAmount() - amount) * -1);
			tinktureStack.setAmount(0);
			return remainder;
		}
		else{
			tinktureStack.modifyAmount(-amount);
			markForClean();
			return 0;
		}
	}
	
	public boolean addFluid(TinktureType type, int amount){
		if(tinktureStack.getTinktureType() == ModTinktureTypes.EMPTY || tinktureStack.getTinktureType() == type){
			if((tinktureStack.getAmount() + amount) > maxFluid){
				Arcanacraft.logger.info("Failed adding tinkture (Not enough space): " + type.getTinktureName() + ", Current Amount: "+ this.getFluidLevel());
				return false;
			}
			else{
				if(tinktureStack.getTinktureType() == ModTinktureTypes.EMPTY){
					tinktureStack.setTinktureType(type);
				}
				tinktureStack.modifyAmount(amount);
				markForClean();
				Arcanacraft.logger.info("Added tinkture: " + type.getTinktureName() + ", Current Amount: "+ this.getFluidLevel());
				return true;
			}
		}
		Arcanacraft.logger.info("Failed adding tinkture (Iincorrect type):  " + type.getTinktureName() + ", Current Amount: "+ this.getFluidLevel());
		return false;
	}
	
	public int addFluidPartial(TinktureType type, int amount){
		if(tinktureStack.getTinktureType() == ModTinktureTypes.EMPTY || tinktureStack.getTinktureType() == type){
			if(tinktureStack.getAmount() >= maxFluid){
				return amount;
			}
			else{
				markForClean();
				if((tinktureStack.getAmount() + amount) >= maxFluid){
					int amountLeft = (tinktureStack.getAmount() + amount) - maxFluid;
					tinktureStack.setAmount(maxFluid);
					return amountLeft;
				}
				else{
					tinktureStack.modifyAmount(amount);
					return 0;
				}
			}
		}
		return amount;
	}

	@Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("fluidType")){
        	tinktureStack.setTinktureType(tinktureStack.getTinktureTypeFromString(compound.getString("fluidType")));
        	
        	/*String ft = compound.getString("fluidType");
        	for(TinktureType type : TinktureManager.getTinktureTypes()){
        		if(ft == type.getFluidType()){
        			tinktureStack.setTinktureType(type);
        			break;
        		}
        	}*/
        } else {
        	tinktureStack.setTinktureType(ModTinktureTypes.EMPTY);
        }
        if(compound.hasKey("fluid")){
        	tinktureStack.setAmount(compound.getInteger("fluid"));
        }
        else{
        	tinktureStack = new TinktureStack(ModTinktureTypes.EMPTY, 0);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        
        if(!tinktureStack.isEmpty()){
        	compound.setString("fluidType", tinktureStack.getTinktureType().getTinktureName());
        	compound.setInteger("fluid", tinktureStack.getAmount());
        }
        return compound;
    }
}
