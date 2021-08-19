package de.rinonline.korinrpg.Helper.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RINButtonRunestone extends GuiButton {
  public ResourceLocation buttonTexture;
  
  private int ID = 0;
  
  public RINButtonRunestone(int id, int width, int height, String displayString) {
    super(id, width, height, displayString);
    this.buttonTexture = new ResourceLocation("korinregions:textures/gui/gui_runestone.png");
    this.width = 16;
    this.height = 16;
  }
  
  public void drawButton(Minecraft minecraft, int xCoord, int yCoord) {
    if (this.visible) {
      FontRenderer fontrenderer = minecraft.fontRenderer;
      minecraft.getTextureManager().bindTexture(this.buttonTexture);
      this.field_146123_n = (xCoord >= this.xPosition && yCoord >= this.yPosition && xCoord < this.xPosition + this.width && yCoord < this.yPosition + this.height);
      int k = getHoverState(this.field_146123_n);
      GL11.glEnable(3042);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if (k == 1) {
        this.ID = 0;
      } else if (k == 2) {
        this.ID = 64;
      } 
      drawButton(this.xPosition, this.yPosition);
      mouseDragged(minecraft, xCoord, yCoord);
      int l = 14737632;
      if (this.packedFGColour != 0) {
        l = this.packedFGColour;
      } else if (!this.enabled) {
        l = 10526880;
      } else if (this.field_146123_n) {
        l = 16777120;
      } 
      if (k == 2) {
        int x2 = 12;
        int x1 = (int)(this.displayString.length() * 3.5D);
        drawRect(this.xPosition - x1 + x2, this.yPosition + 5, this.xPosition + x1 + x2, this.yPosition + 17, -2147483648);
        drawCenteredString(fontrenderer, this.displayString, this.xPosition + x2, this.yPosition + 7, l);
      } 
    } 
  }
  
  public void drawButton(int x, int y) {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.instance;
    tessellator.startDrawingQuads();
    tessellator.addVertexWithUV(x, (y + 16), this.zLevel, ((this.ID + 0) * f), (64.0F * f1));
    tessellator.addVertexWithUV((x + 16), (y + 16), this.zLevel, ((this.ID + 64) * f), (64.0F * f1));
    tessellator.addVertexWithUV((x + 16), (y + 0), this.zLevel, ((this.ID + 64) * f), (0.0F * f1));
    tessellator.addVertexWithUV((x + 0), (y + 0), this.zLevel, ((this.ID + 0) * f), (0.0F * f1));
    tessellator.draw();
  }
}
