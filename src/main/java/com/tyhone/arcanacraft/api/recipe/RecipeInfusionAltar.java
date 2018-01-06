package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.registries.TinktureStack;
import com.tyhone.arcanacraft.api.util.ItemStackUtil;
import com.tyhone.arcanacraft.api.util.TinktureStackutil;

import net.minecraft.item.ItemStack;

public class RecipeInfusionAltar {
	
	ItemStack output;
	ItemStack infusionItem;
	ArrayList<ItemStack> inputs;
	ArrayList<TinktureStack> tInputs;
	
	public RecipeInfusionAltar(ItemStack output, ItemStack infusionItem, ItemStack[] inputs, TinktureStack[] tInputs){
		
		if(inputs == null || inputs.length>5){
			Arcanacraft.logger.log(Level.ERROR ,"Invalid infusion recipe added for: " + output);
		}
		
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
				if(doInputsMatch(recipe.inputs, inputs)){
					Arcanacraft.logger.info("Inputs match");
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

		
		for(int i = 0; i < inputActual.size(); i++) {
			TinktureStack stack = inputActual.get(i);
			if(stack == null)
				break;

			int stackI = -1;

			for(int j = 0; j < inputsRequired.size(); j++) {
				TinktureStack required = inputsRequired.get(j);
				if(TinktureStackutil.simpleIsAmountGreaterThan(stack, required)) {
					stackI = j;
					break;
				}
			}

			if(stackI != -1)
				inputsRequired.remove(stackI);
			else return false;
		}

		return inputsRequired.isEmpty();
	}
	
	//TODO Fix this inputs not matching
	public static boolean doInputsMatch(ArrayList<ItemStack> inputRecipe, ArrayList<ItemStack> inputActual) {
		ArrayList<ItemStack> inputsRequired = (ArrayList<ItemStack>) new ArrayList(inputRecipe).clone();

		
		for(int i = 0; i < inputActual.size(); i++) {
			ItemStack stack = inputActual.get(i);
			if(stack == null)
				break;

			int stackI = -1;

			for(int j = 0; j < inputsRequired.size(); j++) {
				Object input = inputsRequired.get(j);
				if(ItemStackUtil.simpleAreStackSizeEqual((ItemStack) input, stack)) {
					stackI = j;
					break;
				}
			}

			if(stackI != -1)
				inputsRequired.remove(stackI);
			else return false;
		}

		return inputsRequired.isEmpty();
	}
}
