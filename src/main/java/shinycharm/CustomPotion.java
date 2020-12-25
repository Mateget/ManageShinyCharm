package shinycharm;

import com.pixelmonmod.pixelmon.Pixelmon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class CustomPotion extends Potion{
	
	private final ResourceLocation iconTexture;

	public CustomPotion(String displayName,String entryname, boolean isBadPotion, int color, int iconIndexX, int iconIndexY) {
		super(isBadPotion,color);
		setPotionName(displayName);
		setIconIndex(iconIndexX, iconIndexY);
		setRegistryName(entryname);
		iconTexture = new ResourceLocation(Pixelmon.MODID, "textures/gui/inventory/shiny_charm.png");
	}
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
    	if (mc.currentScreen != null){
            mc.getTextureManager().bindTexture(iconTexture);
            Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
        }
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
    {
        mc.getTextureManager().bindTexture(iconTexture);
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }
    
    @Override
    public boolean hasStatusIcon() {
    	// TODO Auto-generated method stub
    	return false;
    }

}
