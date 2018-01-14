package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.api.util.ItemStackUtil;
import com.tyhone.arcanacraft.api.util.RecipeUtil;
import com.tyhone.arcanacraft.api.util.TinktureStackUtil;

import net.minecraft.item.ItemStack;

public class RecipeInfusionAltar {
	
	ItemStack output;
	ItemStack infusionItem;
	ArrayList<ItemStack> inputs;
	ArrayList<TinktureStack> tInputs;
	
	public RecipeInfusionAltar(ItemStack output, ItemStack infusionItem, ArrayList<ItemStack> inputs, TinktureStack[] tInputs){
		
		this.output = output;
		this.infusionItem = infusionItem;
		
		ArrayList<ItemStack> inputsToSet = new ArrayList();
		for(ItemStack input : inputs) {
			inputsToSet.add(input);
		}

		ArrayList<TinktureStack> tInputsToSet = new ArrayList();
		for(TinktureStack tInput : tInputs) {
			tInputsToSet.add(tInput);
		}

		this.inputs = inputsToSet;
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

	public ItemStack getInfusionItem() {
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
		
		/*for(ItemStack input : inputsRequired){
			Arcanacraft.logger.info(input.getDisplayName() + ":" + input.getMetadata() + ":" + input.getCount());
		}*/

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
			//return true;
		}

		return inputsRequired.isEmpty();
	}
	
	/*public static boolean doInputsMatch(ArrayList<ItemStack> inputRecipe, ArrayList<ItemStack> inputActual) {
		ArrayList<ItemStack> inputsRequired = (ArrayList<ItemStack>) new ArrayList(inputRecipe).clone();

		
		for(int i = 0; i < inputActual.size(); i++) {
			ItemStack actual = inputActual.get(i);
			if(actual.isEmpty()){
				break;
			}

			int stackI = -1;

			for(int j = 0; j < inputsRequired.size(); j++) {
				ItemStack required = inputsRequired.get(j);
				if(ItemStackUtil.simpleAreStackSizeEqual(required, actual)) {
					stackI = j;
					break;
				}
			}

			if(stackI != -1){
				inputsRequired.remove(stackI);
			}
			else{
				return false;
			}
		}
		
		return inputsRequired.isEmpty();
	}*/
}
