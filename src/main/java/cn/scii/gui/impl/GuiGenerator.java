package cn.scii.gui.impl;

import cn.scii.ScienceIndustry;
import cn.scii.block.BlocksCollection;
import cn.scii.block.machineblock.tile.TilePowerGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiGenerator extends GuiContainer {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ScienceIndustry.MOD_ID, "textures/gui/generator.png");
    private InventoryPlayer playerInv;

    public GuiGenerator(Container container, InventoryPlayer playerInv) {
        super(container);
        this.playerInv = playerInv;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(BG_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format(BlocksCollection.blocks.get(3).getTranslationKey() + ".name")
                + " | " + TilePowerGenerator.storage.getEnergyStored() + "RF"  + "/"
                + TilePowerGenerator.storage.getMaxEnergyStored() + "RF";
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
    }
}
