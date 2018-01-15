package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.api.util.TinktureStackUtil;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.util.RecipeUtil;

import net.minecraft.item.ItemStack;

public class RecipeInfusionAltar {
	
	ItemStack output;
	Object infusionItem;
	ArrayList<Object> inputs;
	ArrayList<TinktureStack> tInputs;
	
	public RecipeInfusionAltar(ItemStack output, Object infusionItem, ArrayList<Object> inputs, TinktureStack[] tInputs){
		
		this.output = output;
		this.infusionItem = infusionItem;

		ArrayList<TinktureStack> tInputsToSet = new ArrayList();
		for(TinktureStack tInput : tInputs) {
			tInputsToSet.add(tInput);
		}

		this.inputs = (ArrayList<Object>) inputs.clone();
		this.tInputs = tInputsToSet;
	}

	public List<ItemStack> getInputs() {
		return new ArrayList(inputs);
	}
	
	public List<TinktureStack> getTInputs() {
		return new ArrayList(tInputs);
	}

	public ItemStack getOutput() {
		return this.output;
	}

	public Object getInfusionItem() {
		return this.infusionItem;
	}
	
	public static RecipeInfusionAltar getRecipe(ItemStack infusionItem, ArrayList<ItemStack> inputs, ArrayList<TinktureStack> tInputs){
		for(RecipeInfusionAltar recipe : ArcanacraftCraftingManager.getInfusionAltarRecipes()){
			if(ItemStackUtil.simpleAreStackSizeEqual(recipe.infusionItem, infusionItem)){
				if(RecipeUtil.doInputsMatch(recipe.inputs, inputs)){
					if(doTinktureIputsMatch(recipe.tInputs, tInputs)){
						return recipe;
					}
				}
			}
		}
		return null;
	}
	
	public static boolean doTinktureIputsMatch(ArrayList<TinktureStack> inputRecipe, ArrayList<TinktureStack> inputActual){
		ArrayList<TinktureStack> inputsRequired = (ArrayList<TinktureStack>) new ArrayList(inputRecipe).clone();
		ArrayList<TinktureStack> inputsActual = TinktureStackUtil.compactList((ArrayList<TinktureStack>) inputActual.clone());

		while(!inputsRequired.isEmpty()){
			TinktureStack req = inputsRequired.get(0);
			boolean foundMatch = false;
			for(TinktureStack input : inputsActual){
				if(TinktureStackUtil.simpleIsAmountGreaterThan(input, req)){
					foundMatch = true;
					input.modifyAmount(-req.getAmount());
					break;
				}
			}
			if(foundMatch){
				inputsRequired.remove(0);
			}
			else{
				return false;
			}
		}

		return inputsRequired.isEmpty();
	}
}
