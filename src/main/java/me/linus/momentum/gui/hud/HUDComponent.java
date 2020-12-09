package me.linus.momentum.gui.hud;

import me.linus.momentum.mixin.MixinInterface;
import me.linus.momentum.module.Module;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class HUDComponent<T extends Module> implements MixinInterface {

    private final String name;
    protected int x;
    protected int y;
    protected int width = 10;
    protected int height = mc.fontRenderer.FONT_HEIGHT + 3;
    protected final T module;

    private boolean dragging = false;
    private int dragX = 0;
    private int dragY = 0;
    private boolean enabled;
    public int colors;

    public HUDComponent(String name, int x, int y, T module) {
        this.name = name;
        this.x = x;
        enabled = false;
        this.y = y;
        this.module = module;
    }

    public void renderInGui(int mouseX, int mouseY) {
        if (dragging) {
            x = dragX + mouseX;
            y = dragY + mouseY;
        }

        mouseHovered(mouseX, mouseY);

        GuiScreen.drawRect(x - 1, y - 1, x + width, y + height, colors);
        render();
    }

    public void render() {
        if (mc != null) {
            int screenWidth = new ScaledResolution(mc).getScaledWidth();
            int screenHeight = new ScaledResolution(mc).getScaledHeight();

            if (width < 0) {
                if (x > screenWidth) x = screenWidth;
                if (x + width < 0) x = -width;
            } else {
                if (x < 0) x = 0;
                if (x + width > screenWidth) x = screenWidth - width;
            }

            if (y < 0) y = 0;
            if (y + height > screenHeight) y = screenHeight - height;
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if(width < 0) {
            if(button == 0 && mouseX < x && mouseX > x + width && mouseY > y && mouseY < y + height){
                dragX = x - mouseX;
                dragY = y - mouseY;
                dragging = true;
            }
        } else {
            if(button == 0 && mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height){
                dragX = x - mouseX;
                dragY = y - mouseY;
                dragging = true;
            }
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (state == 0) dragging = false;
    }

    public void onGuiClosed(){
        dragging = false;
    }

    public void mouseHovered(int mouseX, int mouseY){}

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void toggle() {
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean in) {
        enabled = in;
    }
}
