package me.linus.momentum.module.modules.movement;

import me.linus.momentum.module.Module;
import me.linus.momentum.setting.mode.Mode;
import me.linus.momentum.setting.slider.Slider;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * @author linustouchtips
 * @since 12/03/2020
 */

public class Step extends Module {
    public Step() {
        super("Step", Category.MOVEMENT, "Increases player step height");
    }

    private static Mode mode = new Mode("Mode", "Normal", "Spider", "Vanilla");
    public static Slider height = new Slider("Height", 0.0D, 2.0D, 5.0D, 0);

    @Override
    public void setup() {
        addSetting(mode);
        addSetting(height);
    }

    @Override
    public void onEnable() {
        if (nullCheck())
            return;

        if (mode.getValue() == 2)
        mc.player.stepHeight = (float) height.getValue();
    }

    @Override
    public void onDisable() {
        mc.player.stepHeight = 0.5f;
    }

    @Override
    public void onUpdate() {
        if (mode.getValue() == 0) {
            if (nullCheck())
                return;

            if (!this.mc.player.collidedHorizontally)
                return;

            if (!mc.player.onGround || mc.player.isOnLadder() || mc.player.isInWater() || mc.player.isInLava() || mc.player.movementInput.jump || mc.player.noClip)
                return;

            if (mc.player.moveForward == 0.0f && mc.player.moveStrafing == 0.0f)
                return;

            final double stepNormal = getCollision();
            if (stepNormal < 0.0 || stepNormal > 2.0)
                return;

            if (stepNormal == 2.0) {
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.42, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.78, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.63, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.51, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.9, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.21, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.45, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.43, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + 2.0, this.mc.player.posZ);
            }

            if (stepNormal == 1.5) {
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.41999998688698, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.7531999805212, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.00133597911214, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.16610926093821, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.24918707874468, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.1707870772188, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + 1.0, this.mc.player.posZ);
            }

            if (stepNormal == 1.0) {
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.41999998688698, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket(new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.7531999805212, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + 1.0, this.mc.player.posZ);
            }

        } else {
            if (mc.player.collidedHorizontally) {
                mc.player.motionY = 0.2;
                float baseHeight = 0.15F;

                if (mc.player.motionX < (double) -baseHeight)
                    mc.player.motionX = -baseHeight;

                if (mc.player.motionX > (double) baseHeight)
                    mc.player.motionX = baseHeight;

                 if (mc.player.motionZ < (double) (-baseHeight))
                    mc.player.motionZ = (-baseHeight);

                if (mc.player.motionZ > (double) baseHeight)
                    mc.player.motionZ = baseHeight;

                mc.player.fallDistance = 0;

                if (mc.player.motionY < -0.15D) {
                    mc.player.motionY = (-0.15D);
                }
            }
        }
    }

    public double getCollision() {
        this.mc.player.stepHeight = 0.5f;
        double maxY = -1.0;
        final AxisAlignedBB grow = this.mc.player.getEntityBoundingBox().offset(0.0, 0.05, 0.0).grow(0.05);
        if (!this.mc.world.getCollisionBoxes(mc.player, grow.offset(0.0, 2.0, 0.0)).isEmpty()) {
            return 100.0;
        }

        for (final AxisAlignedBB aabb : this.mc.world.getCollisionBoxes(mc.player, grow)) {
            if (aabb.maxY > maxY) {
                maxY = aabb.maxY;
            }
        }

        return maxY - this.mc.player.posY;
    }

    @Override
    public String getHUDData() {
        return mode.getMode(mode.getValue());
    }
}
