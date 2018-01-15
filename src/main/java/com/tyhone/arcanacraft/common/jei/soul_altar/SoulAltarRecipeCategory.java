package com.tyhone.arcanacraft.common.jei.soul_altar;

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
import net.minecraft.util.text.translation.I18n;

public class SoulAltarRecipeCategory implements IRecipeCategory<SoulAltarRecipeWrapper>{


    public static final String NAME = "arcanacraft:soul_altar";

    private final IDrawable background;
    
    public SoulAltarRecipeCategory(IGuiHelper helper){
		this.background = helper.createDrawable(GuiUtils.getGuiLocation("gui_soul_altar"), 0, 0, 135, 73);
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
	public void setRecipe(IRecipeLayout recipeLayout, SoulAltarRecipeWrapper wrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
		
		guiItemStacks.init(0, true, 24, 22);
		guiItemStacks.set(0, inputs.get(0));

		if(inputs.size()>=2){
			guiItemStacks.init(2, true, 24, 0);
			guiItemStacks.set(2, inputs.get(1));
		}

		if(inputs.size()>=3){
			guiItemStacks.init(3, true, 38, 44);
			guiItemStacks.set(3, inputs.get(2));
		}

		if(inputs.size()>=4){
			guiItemStacks.init(4, true, 3, 19);
			guiItemStacks.set(4, inputs.get(3));
		}

		if(inputs.size()>=5){
			guiItemStacks.init(5, true, 45, 19);
			guiItemStacks.set(5, inputs.get(4));
		}

		if(inputs.size()==6){
			guiItemStacks.init(6, true, 10, 44);
			guiItemStacks.set(6, inputs.get(5));
		}
        
        guiItemStacks.init(1, false, 107, 24);
        guiItemStacks.set(1, wrapper.recipe.getOutput());

	}

}
