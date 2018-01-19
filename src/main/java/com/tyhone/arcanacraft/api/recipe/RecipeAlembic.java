package com.tyhone.arcanacraft.api.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.api.tinkture.TinktureStack;
import com.tyhone.arcanacraft.api.util.TinktureStackUtil;
import com.tyhone.arcanacraft.common.util.ItemStackUtil;
import com.tyhone.arcanacraft.common.util.RecipeUtil;
import com.tyhone.arcanacraft.common.util.WildStack;

import net.minecraft.item.ItemStack;

public class RecipeAlembic {
	
	Object output;
	Object bottomObject;
	Object object1;
	Object object2;
	
	public RecipeAlembic(Object output, @Nullable Object bottomObject, Object object1, @Nullable Object object2){
		
		this.output = output;
		this.bottomObject = bottomObject;
		this.object1 = object1;
		this.object2 = object2;
	}

	public Object getObject1() {
		return this.object1;
	}

	public Object getObject2() {
		return this.object2;
	}

	public Object getOutput() {
		return this.output;
	}

	public Object getBottomObject() {
		return this.bottomObject;
	}
	
	public static RecipeAlembic getRecipe(Object out, @Nullable Object bo, Object obj1, @Nullable Object obj2){
		
		for(RecipeAlembic recipe : ArcanacraftCraftingManager.getAlembicRecipes()){
			boolean matches = true;
			if(!WildStack.compareObjectType(out, recipe.getOutput())){
				matches = false;
			}
			if(!WildStack.compareObjectType(bo, recipe.getBottomObject())){
				matches = false;
			}
			if(!WildStack.compareObjectType(obj1, recipe.getObject1())){
				matches = false;
			}
			if(!WildStack.compareObjectType(obj2, recipe.getObject2())){
				matches = false;
			}
			if(matches){
				return recipe;
			}
		}
		return null;
	}
}
