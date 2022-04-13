package de.rinonline.korinrpg.nbt;

import de.rinonline.korinrpg.Helper.Gui.InventoryRINPlayer;
import de.rinonline.korinrpg.Helper.Gui.RuneStoneGUI;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class RINPlayer implements IExtendedEntityProperties {
  public static final String RIN_PROP_NAME_REGIONS = "koregions";
  
  private final EntityPlayer player;
  
  private int timer;
  
  private int maxtime = 20;
  
  public ArrayList<String> DiscoverdBiomeList = new ArrayList<String>();
  
  public static int MapCooldown = 0;
  
  public ArrayList<ChunkCoordinates> DiscoverdCordsList = new ArrayList<ChunkCoordinates>();
  
  public ArrayList<String> DiscoverdNameList = new ArrayList<String>();
  
  public InventoryRINPlayer inventory = new InventoryRINPlayer();
  
  public RINPlayer(EntityPlayer player) {
    this.player = player;
  }
  
  public static final void register(EntityPlayer player) {
    player.registerExtendedProperties(RIN_PROP_NAME_REGIONS, new RINPlayer(player));
  }
  
  public static final RINPlayer get(EntityPlayer player) {
    return (RINPlayer)player.getExtendedProperties(RIN_PROP_NAME_REGIONS);
  }
  
  public void copy(RINPlayer props) {
    this.DiscoverdCordsList = props.DiscoverdCordsList;
    this.DiscoverdNameList = props.DiscoverdNameList;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound properties = new NBTTagCompound();
    properties.setInteger("size", this.DiscoverdCordsList.size());
    int i;
    for (i = 0; i < this.DiscoverdCordsList.size(); i++) {
      ChunkCoordinates cords = this.DiscoverdCordsList.get(i);
      properties.setInteger("position_" + i + "_posX", cords.posX);
      properties.setInteger("position_" + i + "_posY", cords.posY);
      properties.setInteger("position_" + i + "_posZ", cords.posZ);
      properties.setString("position_" + i + "_Name", this.DiscoverdNameList.get(i));
    } 
    properties.setInteger("size_biomename", this.DiscoverdBiomeList.size());
    for (i = 0; i < this.DiscoverdBiomeList.size(); i++)
      properties.setString("biomename" + i + "_Name", this.DiscoverdBiomeList.get(i)); 
    this.inventory.writeToNBT(properties);
    properties.setInteger("MapCooldown", MapCooldown);
    compound.setTag(RIN_PROP_NAME_REGIONS, (NBTBase)properties);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound properties = (NBTTagCompound)compound.getTag(RIN_PROP_NAME_REGIONS);
    this.DiscoverdCordsList = new ArrayList<ChunkCoordinates>();
    int tempsize = properties.getInteger("size");
    this.DiscoverdNameList = new ArrayList<String>();
    for (int i = 0; i < tempsize; i++) {
      int tempx = properties.getInteger("position_" + i + "_posX");
      int tempy = properties.getInteger("position_" + i + "_posY");
      int tempz = properties.getInteger("position_" + i + "_posZ");
      if(tempx != 0 && tempy != 0 && tempz != 0) {
      ChunkCoordinates cords = new ChunkCoordinates(tempx, tempy, tempz);
      this.DiscoverdCordsList.add(cords);
      this.DiscoverdNameList.add(properties.getString("position_" + i + "_Name"));
      } 
     } 
    this.DiscoverdBiomeList = new ArrayList<String>();
    int tempsize2 = properties.getInteger("size_biomename");
    for (int j = 0; j < tempsize2; j++)
      this.DiscoverdBiomeList.add(properties.getString("biomename" + j + "_Name")); 
    RuneStoneGUI.strlist = this.DiscoverdNameList;
    RuneStoneGUI.cordlist = this.DiscoverdCordsList;
    this.inventory.readFromNBT(properties);
    MapCooldown = properties.getInteger("MapCooldown");
  }
  
  public void init(Entity entity, World world) {
    this.DiscoverdCordsList = new ArrayList<ChunkCoordinates>();
    this.DiscoverdNameList = new ArrayList<String>();
    this.inventory = new InventoryRINPlayer();
    RuneStoneGUI.strlist = this.DiscoverdNameList;
    RuneStoneGUI.cordlist = this.DiscoverdCordsList;
  }
  
  public void addTeleportCords(ChunkCoordinates cords, String str) {
    if (!this.DiscoverdNameList.contains(str)) {
      this.DiscoverdCordsList.add(cords);
      this.DiscoverdNameList.add(str);
    } 
  }
  
  public ArrayList<ChunkCoordinates> getDiscoverdCordsList() {
    return this.DiscoverdCordsList;
  }
  
  public void setDiscoverdCordsList(ArrayList<ChunkCoordinates> discoverdCordsList) {
    this.DiscoverdCordsList = discoverdCordsList;
  }
  
  public ArrayList<String> getDiscoverdNameList() {
    return this.DiscoverdNameList;
  }
  
  public void setDiscoverdNameList(ArrayList<String> discoverdNameList) {
    this.DiscoverdNameList = discoverdNameList;
  }
}
