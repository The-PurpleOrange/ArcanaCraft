package com.tyhone.arcanacraft.common.jei.deconstruction_table;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;

public class DeconstructionTableRecipeCategory implements IRecipeCategory<DeconstructionTableRecipeWrapper>{


    public static final String NAME = "arcanacraft.deconstruction_table";

    private final IDrawable background;
    
    public DeconstructionTableRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_deconstruction_table"), 0, 0, 135, 54);
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
		
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		

		recipeLayout.getItemStacks().init(0, true, 24, 36);
		guiItemStacks.set(0, inputs.get(0));

		if(inputs.size()>1){
			guiItemStacks.init(2, true, 24, 0);
			guiItemStacks.set(2, inputs.get(1));
		}

		guiItemStacks.init(1, false, 84, 18);
		guiItemStacks.set(1, wrapper.recipe.getOutput());

	}

}
