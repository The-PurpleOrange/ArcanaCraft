package com.tyhone.arcanacraft.common.jei.alembic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tyhone.arcanacraft.api.recipe.RecipeAlembic;
import com.tyhone.arcanacraft.api.recipe.RecipeInfusionAltar;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.common.init.ModTinktureTypes;
import com.tyhone.arcanacraft.common.util.NBTItemStack;
import com.tyhone.arcanacraft.common.util.OreStack;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class AlembicRecipeWrapper implements IRecipeWrapper{

	public final RecipeAlembic recipe;
	
	public AlembicRecipeWrapper(RecipeAlembic recipe){
		this.recipe = recipe;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {

		List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();
		List<TinktureStack> tInputs = new ArrayList<TinktureStack>();
		List<FluidStack> fInputs = new ArrayList<FluidStack>();

		
		//Bottom Object
		Object bottomObject = this.recipe.getBottomObject();
		if(bottomObject instanceof ItemStack){
			inputs.add(Arrays.asList((ItemStack) bottomObject));
			
			tInputs.add(null);
			fInputs.add(null);
		}
		else if(bottomObject instanceof OreStack){
			inputs.add(OreStack.getOreDictionaryEntriesForOreStack((OreStack) bottomObject));
			
			tInputs.add(null);
			fInputs.add(null);
		}
		else if(bottomObject instanceof NBTItemStack){
			ItemStack stack = ((NBTItemStack) bottomObject).getStack();
			stack.setTagCompound(((NBTItemStack) bottomObject).getNBT());
			inputs.add(Arrays.asList(stack));
			
			tInputs.add(null);
			fInputs.add(null);
		}
		else if(bottomObject instanceof TinktureStack){
			tInputs.add((TinktureStack) bottomObject);
			
			inputs.add(null);
			fInputs.add(null);
		}
		else if(bottomObject instanceof FluidStack){
			fInputs.add((FluidStack) bottomObject);
			
			inputs.add(null);
			tInputs.add(null);
		}else{
			inputs.add(null);
			tInputs.add(null);
			fInputs.add(null);
		}
		
		//Bottom Object
		Object obj1 = this.recipe.getObject1();
		if(obj1 instanceof ItemStack){
			inputs.add(Arrays.asList((ItemStack) obj1));
			
			tInputs.add(null);
			fInputs.add(null);
		}
		else if(obj1 instanceof OreStack){
			inputs.add(OreStack.getOreDictionaryEntriesForOreStack((OreStack) obj1));
			
			tInputs.add(null);
			fInputs.add(null);
		}
		else if(obj1 instanceof NBTItemStack){
			ItemStack stack = ((NBTItemStack) obj1).getStack();
			stack.setTagCompound(((NBTItemStack) obj1).getNBT());
			inputs.add(Arrays.asList(stack));
			
			tInputs.add(null);
			fInputs.add(null);
		}
		else if(obj1 instanceof TinktureStack){
			tInputs.add((TinktureStack) obj1);
			
			inputs.add(null);
			fInputs.add(null);
		}
		else if(obj1 instanceof FluidStack){
			fInputs.add((FluidStack) obj1);
			
			inputs.add(null);
			tInputs.add(null);
		}else{
			inputs.add(null);
			tInputs.add(null);
			fInputs.add(null);
		}
		
		//Bottom Object
		Object obj2 = this.recipe.getObject2();
		if(obj2 instanceof ItemStack){
			inputs.add(Arrays.asList((ItemStack) obj2));
			
			tInputs.add(null);
			fInputs.add(null);
		}
		else if(obj2 instanceof OreStack){
			inputs.add(OreStack.getOreDictionaryEntriesForOreStack((OreStack) obj2));
			
			tInputs.add(null);
			fInputs.add(null);
		}
		else if(obj2 instanceof NBTItemStack){
			ItemStack stack = ((NBTItemStack) obj2).getStack();
			stack.setTagCompound(((NBTItemStack) obj2).getNBT());
			inputs.add(Arrays.asList(stack));
			
			tInputs.add(null);
			fInputs.add(null);
		}
		else if(obj2 instanceof TinktureStack){
			tInputs.add((TinktureStack) obj2);
			
			inputs.add(null);
			fInputs.add(null);
		}
		else if(obj2 instanceof FluidStack){
			fInputs.add((FluidStack) obj2);
			
			inputs.add(null);
			tInputs.add(null);
		}else{
			inputs.add(null);
			tInputs.add(null);
			fInputs.add(null);
		}

		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setInputs(TinktureStack.class, tInputs);
		ingredients.setInputs(FluidStack.class, fInputs);
		
		//Outputs
		

		Object output = this.recipe.getOutput();
		if(output instanceof ItemStack){
			ingredients.setOutput(ItemStack.class, this.recipe.getOutput());
		}
		else if(output instanceof NBTItemStack){
			ItemStack stack = ((NBTItemStack) output).getStack();
			stack.setTagCompound(((NBTItemStack) output).getNBT());
			ingredients.setOutput(ItemStack.class, stack);
		}
		else if(output instanceof TinktureStack){
			ingredients.setOutput(TinktureStack.class, this.recipe.getOutput());
		}
		else if(output instanceof FluidStack){
			ingredients.setOutput(FluidStack.class, this.recipe.getOutput());
		}
	}

}
