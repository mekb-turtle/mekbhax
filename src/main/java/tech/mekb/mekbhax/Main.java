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
	public static boolean stepEnabled       = false;
	public static boolean xrayEnabled       = false;
	public static boolean fullBrightEnabled = false;
	public static float originalStep;
	public static double originalGamma;
	public static boolean slow = false;
	public static boolean fast = false;
	public static final double fullBrightGamma = 10;
	public static int tick = 0;
	private static final String bindCategory = "category.mekbhax";
	public static KeyBinding flyBind        = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.fly",        InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G,     bindCategory));
	public static KeyBinding speedBind      = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.speed",      InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H,     bindCategory));
	public static KeyBinding noFallBind     = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.nofall",     InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_U,     bindCategory));
	public static KeyBinding stepBind       = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.step",       InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J,     bindCategory));
	public static KeyBinding xrayBind       = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.xray",       InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O,     bindCategory));
	public static KeyBinding fullBrightBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Y,     bindCategory));
	public static KeyBinding slowBind       = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.slow",       InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT,  bindCategory));
	public static KeyBinding fastBind       = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.fast",       InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT, bindCategory));
	public static KeyBinding flyAscendBind  = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.ascend",     InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UP,    bindCategory));
	public static KeyBinding flyDescendBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.descend",    InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_DOWN,  bindCategory));
	public static final Logger Logger = LoggerFactory.getLogger("mekbhax");
	@Override
	public void onInitialize() {
	}
}
