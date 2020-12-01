package shinycharm;

import com.pixelmonmod.pixelmon.Pixelmon;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class CustomPotion extends Potion{

	public CustomPotion(String displayName,String entryname, boolean isBadPotion, int color, int iconIndexX, int iconIndexY) {
		super(isBadPotion,color);
		setPotionName(displayName);
		setIconIndex(iconIndexX, iconIndexY);
		setRegistryName(entryname);
	}
	
	@Override
	public boolean hasStatusIcon() {
		// TODO Auto-generated method stub
		//System.out.println("[rsc] Texture Load");
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Pixelmon.MODID + ":textures/gui/inventory/shiny_charm.png"));
		return false;
	}
	
}
