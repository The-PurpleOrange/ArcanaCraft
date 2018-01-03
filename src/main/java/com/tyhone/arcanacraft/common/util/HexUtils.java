package com.tyhone.arcanacraft.common.util;

public class HexUtils {

	public int[] getRGB(int hex){
		
		int r = (hex >> 16) & 0xFF;
		int g = (hex >> 8) & 0xFF;
		int b = (hex >> 0) & 0xFF;
		
		return new int[]{r, g, b};
	}
	
}
