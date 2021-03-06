package com.tyhone.arcanacraft.common.jei.alchemic_array;

import java.util.List;

import com.tyhone.arcanacraft.Arcanacraft;
import com.tyhone.arcanacraft.common.util.GuiUtils;
import com.tyhone.arcanacraft.common.util.OreStack;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class AlchemicArrayRecipeCategory implements IRecipeCategory<AlchemicArrayRecipeWrapper>{


    public static final String NAME = "arcanacraft:alchemic_array";

    private final IDrawable background;
    
    public AlchemicArrayRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_alchemic_array"), 0, 0, 135, 54);
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
	public void setRecipe(IRecipeLayout recipeLayout, AlchemicArrayRecipeWrapper wrapper, IIngredients ingredients) {
		
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		
		int i = 0;
		breakPlacing:
		for(int h = 0; h<3; h++){
			for(int w = 0; w<4; w++){
				if(i>=inputs.size()){
					break breakPlacing;
				}
				guiItemStacks.init(i, true, 6+(w*18), (h*18));
				guiItemStacks.set(i, inputs.get(i));
				i++;
			}
		}
		
		guiItemStacks.init(i, false, 107, 19);
		guiItemStacks.set(i, wrapper.recipe.getOutput());

	}

}
