package data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ShinyCharmTempStorage implements IStorage<IShinyCharmTemp>{

	/**
	 * This class is responsible for saving and reading shiny charm time data from or to server
	 */
	
	@Override
	public NBTBase writeNBT(Capability<IShinyCharmTemp> capability, IShinyCharmTemp instance, EnumFacing side) {
		NBTTagCompound shinycharmtime = new NBTTagCompound();
		shinycharmtime.setInteger("shinycharmtime", instance.getTime());
		return shinycharmtime;
	}

	@Override
	public void readNBT(Capability<IShinyCharmTemp> capability, IShinyCharmTemp instance, EnumFacing side, NBTBase nbt) {
		 int time = ((NBTTagCompound) nbt).getInteger("shinycharmtime");
		 instance.setTime(time);
    }
	
}