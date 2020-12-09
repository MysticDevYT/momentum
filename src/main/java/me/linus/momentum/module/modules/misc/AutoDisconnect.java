package me.linus.momentum.module.modules.misc;

import me.linus.momentum.module.Module;
import me.linus.momentum.setting.checkbox.Checkbox;
import me.linus.momentum.setting.slider.Slider;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @author linustouchtips
 * @since 11/30/2020
 */

public class AutoDisconnect extends Module {
    public AutoDisconnect() {
        super("AutoDisconnect", Category.MISC, "Automatically logs you out when you're low on health");
    }

    public static Slider health = new Slider("Health", 0.0D, 7.0D, 36.0D, 0);
    public static Checkbox noTotems = new Checkbox("No Totems", false);

    @Override
    public void setup() {
        addSetting(health);
        addSetting(noTotems);
    }

    @Override
    public void onUpdate() {
        if (nullCheck())
            return;

        int totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();

        if (totems == 0 && noTotems.getValue()) {
            this.disable();
            mc.world.sendQuittingDisconnectingPacket();
            mc.loadWorld(null);
            mc.displayGuiScreen(new GuiMainMenu());
        }

        if (mc.player.getHealth() <= health.getValue()) {
            this.disable();
            mc.world.sendQuittingDisconnectingPacket();
            mc.loadWorld(null);
            mc.displayGuiScreen(new GuiMainMenu());
        }
    }

    @Override
    public String getHUDData() {
        return String.valueOf(health.getValue());
    }
}
