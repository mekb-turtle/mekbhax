package tech.mekb.mekbhax;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static boolean flyEnabled        = false;
	public static boolean speedEnabled      = false;
	public static boolean noFallEnabled     = false;
	public static boolean xrayEnabled       = false;
	public static boolean fullBrightEnabled = false;
	public static double originalGamma;
	public static final double fullbrightGamma = 1000;
	public static int tick = 0;
	public static KeyBinding flyBind        = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.fly",        InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "category.mekbhax"));
	public static KeyBinding speedBind      = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.speed",      InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "category.mekbhax"));
	public static KeyBinding noFallBind     = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.nofall",     InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "category.mekbhax"));
	public static KeyBinding xrayBind       = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.xray",       InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Y, "category.mekbhax"));
	public static KeyBinding fullBrightBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_U, "category.mekbhax"));
	public static final Logger Logger = LoggerFactory.getLogger("mekbhax");
	@Override
	public void onInitialize() {
	}
}
