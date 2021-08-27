package de.rinonline.korinrpg;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import de.rinonline.korinrpg.Helper.Gui.InterfaceGUI;
import de.rinonline.korinrpg.Helper.Gui.RegionEventHandler;
import de.rinonline.korinrpg.Network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "korinregions", version = "4.2")
public class RINMAIN {
  public static final String MODID = "korinregions";
  
  public static final String name = "Kingdom of RIN | Regions";
  
  public static final String VERSION = "4.2";
  
  public static ModInteropProxy modInterop;
  
  public static RINMAIN instance;
  
  public static Configuration config;
  
  @SidedProxy(clientSide = "de.rinonline.korinrpg.BasisClientProxy", serverSide = "de.rinonline.korinrpg.BasisCommonProxy")
  public static BasisCommonProxy proxy;
  
  public static BasisClientProxy proxyclient;
  
  public RINMAIN() {
    instance = this;
  }
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent preEvent) {
    config = new Configuration(preEvent.getSuggestedConfigurationFile());
    ConfigurationMoD.loadConfig();
    if (Loader.isModLoaded("VillageNames")) {
      try {
        modInterop = Class.forName("de.rinonline.korinrpg.ActiveModInteropProxy").<ModInteropProxy>asSubclass(ModInteropProxy.class).newInstance();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } 
    } else {
      modInterop = new DummyModInteropProxy();
    } 
    NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
  }
  
  @EventHandler
  public void init(FMLInitializationEvent event) {
    new ItemRegistry();
    PacketDispatcher.registerPackets();
    RegionEventHandler events = new RegionEventHandler();
    FMLCommonHandler.instance().bus().register(events);
    MinecraftForge.EVENT_BUS.register(events);
  }
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent postEvent) {
    if (FMLCommonHandler.instance().getEffectiveSide().isClient())
      MinecraftForge.EVENT_BUS.register(new InterfaceGUI(Minecraft.getMinecraft())); 
  }
  
  @EventHandler
  public void serverLoad(FMLServerStartingEvent event) {
    event.registerServerCommand(new CommandDisplayText());
    event.registerServerCommand(new CommandSetDisplayPoint());
  }
}
