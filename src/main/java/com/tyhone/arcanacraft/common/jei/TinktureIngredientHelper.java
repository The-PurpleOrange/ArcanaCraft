package com.tyhone.arcanacraft.common.jei;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;

import mezz.jei.api.ingredients.IIngredientHelper;

public class TinktureIngredientHelper implements IIngredientHelper<TinktureStack>{

	@Override
	public List<TinktureStack> expandSubtypes(List<TinktureStack> ingredients) {
		return ingredients;
	}

	@Override
	public TinktureStack getMatch(Iterable<TinktureStack> ingredients, TinktureStack ingredientToMatch) {
		for(TinktureStack tinktureStack : ingredients){
			if(tinktureStack.getTinktureType() == ingredientToMatch.getTinktureType()){
				return tinktureStack;
			}
		}
		return null;
	}

	@Override
	public String getDisplayName(TinktureStack ingredient) {
		return ingredient.getTinktureName();
	}

	@Override
	public String getUniqueId(TinktureStack ingredient) {
		return ingredient.getTinktureName();
	}

	@Override
	public String getWildcardId(TinktureStack ingredient) {
		return ingredient.getTinktureName();
	}

	@Override
	public String getModId(TinktureStack ingredient) {
		return Arcanacraft.MODID;
	}

	@Override
	public Iterable<Color> getColors(TinktureStack ingredient) {
		return Collections.singleton(Color.BLACK);
	}

	@Override
	public String getResourceId(TinktureStack ingredient) {
		return ingredient.getTinktureName();
	}

	@Override
	public TinktureStack copyIngredient(TinktureStack ingredient) {
		return ingredient.copy();
	}

	@Override
	public String getErrorInfo(TinktureStack ingredient) {
		return "Tinkture Ingredient Error: " + ingredient.getTinktureName();
	}
}