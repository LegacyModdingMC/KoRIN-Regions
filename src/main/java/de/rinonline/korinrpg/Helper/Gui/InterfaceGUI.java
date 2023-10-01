package de.rinonline.korinrpg.Helper.Gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.rinonline.korinrpg.ConfigurationMoD;
import de.rinonline.korinrpg.Network.PacketDispatcher;
import de.rinonline.korinrpg.Network.sendTextpopRegions;
import de.rinonline.korinrpg.nbt.RINPlayer;

public class InterfaceGUI extends GuiScreen {

    private Minecraft mc;

    private static boolean inVillage = false;

    private static ArrayList<String> DISPLAYSTRING = new ArrayList<String>();

    private static String BIOMEOLD = "none";

    private static String DISPLAYSTRINGVILLAGEOLD = "none";

    private static int time;

    private static int f1 = 0, maxtime = ConfigurationMoD.DisplayTime;

    private static int VillagePosX;

    private static int VillagePosY;

    private static int VillagePosZ;

    private static int radius;

    public static int playtime = 200;

    public InterfaceGUI(Minecraft mc) {
        this.mc = mc;
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onRenderExperienceBar(RenderGameOverlayEvent.Pre event) {}

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderExperienceBar(RenderGameOverlayEvent.Chat event) {
        if (playtime > 0) {
            --playtime;
        } else {
            EntityClientPlayerMP entityClientPlayerMP = this.mc.thePlayer;
            if (!DISPLAYSTRING.isEmpty()) {
                if (DISPLAYSTRING.size() >= 0) if (!updateTime()) {
                    ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
                    int sw = res.getScaledWidth();
                    int sh = res.getScaledHeight();
                    FontRenderer fontRender = this.mc.fontRenderer;
                    int xPos = (int) (sw / (2 * ConfigurationMoD.ScaleSize));
                    int yPos = (int) (sh / (1.6 * ConfigurationMoD.ScaleSize));
                    int startend = maxtime / 3;
                    int endstart = maxtime - startend;
                    if (time < startend) {
                        double dd = 255 * time / startend;
                        f1 = (int) dd;
                    } else if (time > endstart) {
                        double dd = 255 * (time - endstart) / startend;
                        f1 = (int) (255 - dd);
                    } else {
                        f1 = 255;
                    }
                    GL11.glScaled(
                        1 * ConfigurationMoD.ScaleSize,
                        1 * ConfigurationMoD.ScaleSize,
                        1 * ConfigurationMoD.ScaleSize);
                    GL11.glEnable(GL11.GL_BLEND);
                    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                    if (f1 >= 15) {
                        fontRender.drawStringWithShadow(
                            DISPLAYSTRING.get(0),
                            (int) (xPos - DISPLAYSTRING.get(0)
                                .length() * ((ConfigurationMoD.ScaleSize / 2))) + ConfigurationMoD.textX,
                            yPos + ConfigurationMoD.textY,
                            ConfigurationMoD.HTML_COLOR | (f1 << 24));
                    }
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glScaled(
                        1 / ConfigurationMoD.ScaleSize,
                        1 / ConfigurationMoD.ScaleSize,
                        1 / ConfigurationMoD.ScaleSize);
                } else {
                    DISPLAYSTRING.remove(0);
                }
            } else if (inVillage && entityClientPlayerMP.getDistance(VillagePosX, VillagePosY, VillagePosZ) > 50.0D) {
                inVillage = false;
            } else {
                updateBiome((EntityPlayer) entityClientPlayerMP);
            }
        }
    }

    private void updateBiome(EntityPlayer player) {
        String str = (player.worldObj.getBiomeGenForCoords((int) player.posX, (int) player.posZ)).biomeName;
        if (!(RINPlayer.get(player)).DiscoverdBiomeList.contains(str)) {
            boolean tempb = true;
            int i;
            for (i = 0; i < ConfigurationMoD.valuesB.length; i++) {
                if (ConfigurationMoD.valuesB[i].equals(str)) tempb = false;
            }
            if (ConfigurationMoD.useWhitelist) for (i = 0; i < ConfigurationMoD.valuesW.length; i++) {
                if (ConfigurationMoD.valuesB[i].equals(str)) tempb = true;
            }
            if (tempb && BIOMEOLD != str) {
                DISPLAYSTRING.add(str);
                BIOMEOLD = str;
                if (!ConfigurationMoD.displayerBiomeAgain) PacketDispatcher.sendToServer(new sendTextpopRegions(str));
            }
        }
    }

    private boolean updateTime() {
        time++;
        if (time >= maxtime) {
            f1 = 0;
            time = 0;
            return true;
        }
        return false;
    }

    public static void setDISPLAYSTRING(String dISPLAYSTRING) {
        DISPLAYSTRING.add(dISPLAYSTRING);
    }

    public static void setDISPLAYSTRINGVILLAGE(String str, int x, int y, int z, int rad) {
        if (!inVillage && DISPLAYSTRINGVILLAGEOLD != str) {
            DISPLAYSTRING.add(str);
            DISPLAYSTRINGVILLAGEOLD = str;
            inVillage = true;
            VillagePosX = x;
            VillagePosY = y;
            VillagePosZ = z;
            radius = rad;
        }
    }
}
