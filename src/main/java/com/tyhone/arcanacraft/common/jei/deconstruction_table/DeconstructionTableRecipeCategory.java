package com.tyhone.arcanacraft.common.jei.deconstruction_table;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;

public class DeconstructionTableRecipeCategory implements IRecipeCategory<DeconstructionTableRecipeWrapper>{


    public static final String NAME = "arcanacraft.deconstruction_table";

    private final IDrawable background;
    
    public DeconstructionTableRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_deconstruction_table"), 0, 0, 135, 80);
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
	public void setRecipe(IRecipeLayout recipeLayout, DeconstructionTableRecipeWrapper wrapper, IIngredients ingredients) {
		recipeLayout.getItemStacks().init(0, true, 24, 49);
        recipeLayout.getItemStacks().set(0, wrapper.recipe.getInput());
        
		recipeLayout.getItemStacks().init(1, true, 24, 13);
		if(wrapper.recipe.hasLens()){
			recipeLayout.getItemStacks().set(1, wrapper.recipe.getLens());
		}

        recipeLayout.getItemStacks().init(2, false, 84, 31);
        recipeLayout.getItemStacks().set(2, wrapper.recipe.getOutput());

	}

}
