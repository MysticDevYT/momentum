package me.linus.momentum.module.modules.render;

import me.linus.momentum.event.events.packet.PacketSendEvent;
import me.linus.momentum.module.Module;
import me.linus.momentum.setting.slider.Slider;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author linustouchtips
 * @since 11/26/2020
 */

public class CustomFOV extends Module {
    public CustomFOV() {
        super("CustomFOV", Category.RENDER, "Changes your ingame FOV");
    }

    public static Slider customFOV = new Slider("FOV", 0.0D, 120.0D, 250.0D, 0);
    public static Slider itemFOV = new Slider("Item FOV", 0.0D, 120.0D, 250.0D, 0);

    @Override
    public void setup() {
        addSetting(customFOV);
        addSetting(itemFOV);
    }

    @Override
    public void onUpdate() {
        if (nullCheck())
            return;

        mc.gameSettings.fovSetting = (float) customFOV.getValue();
    }

    @SubscribeEvent
    public void eventFOV(EntityViewRenderEvent.FOVModifier FOV) {
        FOV.setFOV((float) itemFOV.getValue());
    }
}