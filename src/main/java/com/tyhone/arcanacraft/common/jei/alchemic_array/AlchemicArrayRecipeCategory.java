package com.tyhone.arcanacraft.common.jei.alchemic_array;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;

public class AlchemicArrayRecipeCategory implements IRecipeCategory<AlchemicArrayRecipeWrapper>{


    public static final String NAME = "arcanacraft.alchemic_array";

    private final IDrawable background;
    
    public AlchemicArrayRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_alchemic_array"), 0, 0, 135, 80);
	}
	
	@Override
	public String getUid() {
		return NAME;
	}

	@Override
	public String getTitle() {
		return ("container.nei."+NAME+".name");
	}

	@Override
	public String getModName() {
		return Arcanacraft.MODNAME;
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AlchemicArrayRecipeWrapper wrapper, IIngredients ingredients) {
		List<ItemStack> inputs = wrapper.recipe.getInputs();
		
		int i = 0;
		for(int h = 0; h<3; h++){
			for(int w = 0; w<4; w++){
				recipeLayout.getItemStacks().init(i, true, 6+(w*18), 6+(h*18));
				i++;
			}
		}
		
		recipeLayout.getItemStacks().set(0, inputs.get(0));
		if(inputs.size()<=12){
			int k = 0;
			for(ItemStack input : inputs){
				recipeLayout.getItemStacks().set(k, inputs.get(k));
				k++;
			}
		}
        
        recipeLayout.getItemStacks().init(i, false, 107, 25);
        recipeLayout.getItemStacks().set(i, wrapper.recipe.getOutput());

	}

}
