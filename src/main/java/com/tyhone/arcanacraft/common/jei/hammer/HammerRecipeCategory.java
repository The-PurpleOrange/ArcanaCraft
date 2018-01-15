package com.tyhone.arcanacraft.common.jei.hammer;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.GuiUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class HammerRecipeCategory implements IRecipeCategory<HammerRecipeWrapper>{


    public static final String NAME = "arcanacraft:hammer";

    private final IDrawable background;
    
    public HammerRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_hammer"), 0, 0, 135, 56);
	}
	
	@Override
	public String getUid() {
		return NAME;
	}

	@Override
	public String getTitle() {
		return I18n.translateToLocal("container.nei."+NAME+".name");
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

		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 10, 7);
		guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));

		guiItemStacks.init(1, false, 105, 7);
		guiItemStacks.set(1, wrapper.recipe.getOutput());

	}

}
