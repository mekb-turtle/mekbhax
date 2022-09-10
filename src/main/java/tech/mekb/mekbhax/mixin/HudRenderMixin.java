package tech.mekb.mekbhax.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static tech.mekb.mekbhax.Main.*;

@Mixin(InGameHud.class)
public class HudRenderMixin {
    private static final int x = 12;
    private static final int y = 12;
    @Inject(method = "render", at = @At("RETURN"))
    public void onRender(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer r = mc.textRenderer;
        int h = r.fontHeight;
        int i = 0;
        // show enabled hacks
        String speedText = "";
        if (faster || fast || slow || slower) {
            speedText = " (" +
                    (faster && fast ? "Very Fast" : faster ? "Faster" : fast ? "Fast" :
                            slow ? "Slow" : slower ? "Slower" : "")
                    + ", " + getSprintMul() + "x)";
        }
        if (flyEnabled)
            r.drawWithShadow(matrices, "§aFly§7"      + speedText, x, (i++)*h+y, -1);
        if (speedEnabled)
            r.drawWithShadow(matrices, "§aSpeed§7"    + speedText, x, (i++)*h+y, -1);
        if (freeCamEnabled)
            r.drawWithShadow(matrices, "§aFree-Cam§7" + speedText, x, (i++)*h+y, -1);
        if (noFallEnabled)
            r.drawWithShadow(matrices, "§aNo Fall",                x, (i++)*h+y, -1);
        if (stepEnabled)
            r.drawWithShadow(matrices, "§aStep",                   x, (i++)*h+y, -1);
        if (xrayEnabled)
            r.drawWithShadow(matrices, "§aX-Ray",                  x, (i++)*h+y, -1);
        if (fullBrightEnabled)
            r.drawWithShadow(matrices, "§aFull-Bright",            x, (i++)*h+y, -1);
        if (antiEffectEnabled)
            r.drawWithShadow(matrices, "§aAnti-Effect",            x, (i++)*h+y, -1);
    }
}
