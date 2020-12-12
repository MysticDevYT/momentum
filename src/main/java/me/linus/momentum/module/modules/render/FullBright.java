package me.linus.momentum.module.modules.render;

import me.linus.momentum.module.Module;
import me.linus.momentum.setting.mode.Mode;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

/**
 * @author linustouchtips
 * @since 11/27/2020
 */

public class FullBright extends Module {
    public FullBright() {
        super("FullBright", Category.RENDER, "Adjusts light levels");
    }

    private static Mode mode = new Mode("Mode", "Gamma", "Vanilla");

    @Override
    public void setup() {
        addSetting(mode);
    }

    private float oldBright;

    @Override
    public void onUpdate() {
        PotionEffect effect = new PotionEffect(MobEffects.NIGHT_VISION, 80950, 1, false, false);

        if (mode.getValue() == 1)
            mc.player.addPotionEffect(new PotionEffect(effect));
    }

    @Override
    public void onEnable() {
        if (nullCheck())
            return;

        oldBright = mc.gameSettings.gammaSetting;

        if (mode.getValue() == 0)
            mc.gameSettings.gammaSetting = +100;
    }

    @Override
    public void onDisable() {
        mc.player.removePotionEffect(MobEffects.NIGHT_VISION);

        if (mode.getValue() == 0)
            mc.gameSettings.gammaSetting = oldBright;
    }
}
