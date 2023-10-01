package de.rinonline.korinrpg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.common.FMLCommonHandler;
import de.rinonline.korinrpg.Network.PacketDispatcher;
import de.rinonline.korinrpg.Network.sendVillagePacket;

public class ConfigurationMoD {

    public static int textX = 0;

    public static int textY = 0;

    public static int HTML_COLOR = 16119039;

    public static int DisplayTime = 400;

    public static String[] valuesB;

    public static String[] valuesW;

    public static boolean useWhitelist;

    public static boolean displayerBiomeAgain;

    public static String[] TownNames;

    public static double ScaleSize = 1.0D;

    public static boolean canTeleportToVillages;

    public static boolean MapDisapearAfterTeleport;

    public static boolean useMapCooldown;

    public static int TeleportCooldown;

    public static int teleportBlockprice;

    private static int Size;

    private static int[] ValueList;

    static final String CATEGORY_2 = "general" + "." + "CustomCords (sended by Server, do NOT Edit that)";

    public static void loadConfig() {
        FMLCommonHandler.instance()
            .bus()
            .register(RINMAIN.instance);
        String CATEGORY_1 = "general" + "." + "Kingdome of RIN | Regions";
        RINMAIN.config.addCustomCategoryComment(CATEGORY_1, "General");
        String CATEGORY_3 = "general" + "." + "Kingdome of RIN | Regions (Interface)";
        RINMAIN.config.addCustomCategoryComment(CATEGORY_3, "General");
        String CATEGORY_2 = "general" + "." + "Kingdome of RIN | Regions (Teleport)";
        RINMAIN.config.addCustomCategoryComment(CATEGORY_2, "General");
        MapDisapearAfterTeleport = RINMAIN.config
            .getBoolean("MapDisapearAfterTeleport", CATEGORY_2, false, "Enable this to let the Map dispper after use.");
        useMapCooldown = RINMAIN.config
            .getBoolean("useMapCooldown", CATEGORY_2, true, "Enable this to use the Cooldownsystem for Teleports.");
        canTeleportToVillages = RINMAIN.config
            .getBoolean("canTeleportToVillages", CATEGORY_2, true, "Enable it to let the Player use Runestones.");
        TeleportCooldown = RINMAIN.config.get(CATEGORY_2, "Teleportcooldown in secounds", 10)
            .getInt() * 40;
        textX = RINMAIN.config.get(CATEGORY_3, "X offset of the text", 0)
            .getInt();
        textY = RINMAIN.config.get(CATEGORY_3, "Y offset of the text", 0)
            .getInt();
        HTML_COLOR = RINMAIN.config.get(CATEGORY_3, "Colorcode for the Display Text ", 16119039)
            .getInt();
        DisplayTime = RINMAIN.config.get(CATEGORY_3, "the Time in Secounds which the Text is shown", 4)
            .getInt() * 40;
        ScaleSize = RINMAIN.config.get(CATEGORY_3, "Scale the Text be Factor", 5)
            .getDouble();
        valuesB = RINMAIN.config.getStringList(
            "BiomeBlackList",
            "Biome",
            new String[] { (BiomeGenBase.getBiomeGenArray()[7]).biomeName,
                (BiomeGenBase.getBiomeGenArray()[11]).biomeName, (BiomeGenBase.getBiomeGenArray()[16]).biomeName,
                (BiomeGenBase.getBiomeGenArray()[3]).biomeName, (BiomeGenBase.getBiomeGenArray()[25]).biomeName,
                (BiomeGenBase.getBiomeGenArray()[26]).biomeName },
            "Blacklisted Strings");
        valuesW = RINMAIN.config.getStringList(
            "BiomeWhiteList",
            "Biome",
            new String[] { (BiomeGenBase.getBiomeGenArray()[7]).biomeName,
                (BiomeGenBase.getBiomeGenArray()[11]).biomeName, (BiomeGenBase.getBiomeGenArray()[16]).biomeName,
                (BiomeGenBase.getBiomeGenArray()[3]).biomeName, (BiomeGenBase.getBiomeGenArray()[25]).biomeName,
                (BiomeGenBase.getBiomeGenArray()[26]).biomeName },
            "Whitlisted Strings (only if Enabled!)");
        useWhitelist = RINMAIN.config
            .getBoolean("Whitelisting", CATEGORY_1, false, "Enable Whitelist for the Names shown on the Screen");
        displayerBiomeAgain = RINMAIN.config.getBoolean(
            "displayerBiomeAgain",
            CATEGORY_1,
            false,
            "Enable this to let the Biome display again and again.");
        TownNames = RINMAIN.config.getStringList(
            "TownNames",
            "List",
            new String[] { "Demonsummit", "Fallhold", "Ebonholde", "Lakeville", "Nevermoor", "Goldcrest", "Highbury",
                "Sleetdale", "Brittletide", "Sleekborn", "Swiftspell", "Silkbay", "Doghall", "Glimmerharbor",
                "Muddrift", "Ghostgrasp", "Roseharbor", "Sleetstar", "Deadwallow", "Shimmervault", "Mageshore",
                "Mistville", "Snowgrasp", "Feardenn", "Sleekborn", "Mistcoast", "Baygulch", "Oxkeep", "Stagcrest",
                "Whitspire", "Sunfalls", "Mutehaven", "Northburgh", "Newgarde", "Quickhold", "Stillfall", "Whitcross",
                "Silveracre", "Whiteview", "Grimefair", "Nightburgh", "Kilgrove", "Highfield", "Lakeharbor",
                "Coldhallow", "Swampshield", "Deepstrand", "Whiteshire", "Whitereach", "Rockhand", "Deadstorm",
                "Sleetholde", "Quickwind", "Nightfield", "Scorchwood", "Rosehaven", "Southbay" },
            "Standard Names for Villages.");
        Size = RINMAIN.config.get(CATEGORY_2, "CurrentPoints", 0)
            .getInt();
        if (RINMAIN.config.hasChanged()) RINMAIN.config.save();
    }

    public static void addPoint(int[] intlist, String string) {
        Size = RINMAIN.config.get(CATEGORY_2, "CurrentPoints", 0)
            .getInt();
        Size++;
        RINMAIN.config.get(CATEGORY_2, "cords_" + Size, intlist)
            .getIntList();
        RINMAIN.config.get(CATEGORY_2, "cords_" + Size + "name", string)
            .getString();
        RINMAIN.config.get(CATEGORY_2, "CurrentPoints", 0)
            .set(Size);
        if (RINMAIN.config.hasChanged()) RINMAIN.config.save();
    }

    public static void checkPlayerPos(EntityPlayer player) {
        Size = RINMAIN.config.get(CATEGORY_2, "CurrentPoints", 0)
            .getInt();
        for (int i = 1; i <= Size; i++) {
            ValueList = RINMAIN.config.get(CATEGORY_2, "cords_" + i, new int[] { 1, 12, 2, 2 })
                .getIntList();
            if (player.getDistance(ValueList[0], ValueList[1], ValueList[2]) <= ValueList[3]) PacketDispatcher.sendTo(
                new sendVillagePacket(
                    RINMAIN.config.get(CATEGORY_2, "cords_" + i + "name", "none")
                        .getString(),
                    new ChunkCoordinates(ValueList[0], ValueList[1], ValueList[2]),
                    ValueList[3]),
                (EntityPlayerMP) player);
        }
        if (RINMAIN.config.hasChanged()) RINMAIN.config.save();
    }
}
