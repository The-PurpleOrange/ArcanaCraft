package com.tyhone.arcanacraft.common.jei.hammer;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;

public class HammerRecipeCategory implements IRecipeCategory<HammerRecipeWrapper>{


    public static final String NAME = "arcanacraft.hammer";

    private final IDrawable background;
    
    public HammerRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_hammer"), 0, 0, 135, 80);
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
	public void setRecipe(IRecipeLayout recipeLayout, HammerRecipeWrapper wrapper, IIngredients ingredients) {
		recipeLayout.getItemStacks().init(0, true, 10, 31);
        recipeLayout.getItemStacks().set(0, wrapper.recipe.getInput());

        recipeLayout.getItemStacks().init(1, false, 105, 31);
        recipeLayout.getItemStacks().set(1, wrapper.recipe.getOutput());

	}

}
