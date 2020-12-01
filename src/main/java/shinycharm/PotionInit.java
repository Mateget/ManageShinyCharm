package shinycharm;

import java.awt.Color;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class PotionInit {

	public static final Potion shinyCharmPotion_Effect = new CustomPotion("Shiny Charm","shinycharm", false, Color.ORANGE.getRGB(), 0, 0);
	
	public static void registerPotion() {
		
		ForgeRegistries.POTIONS.register(shinyCharmPotion_Effect);
	}
	
}

