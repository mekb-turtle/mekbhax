package tech.mekb.mekbhax;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main implements ModInitializer {
	public static final double speedH = 0.6;
	public static final double speedV = 0.5;
	public static final double speedB = 0.1;
	public static final double fastMul = 2.0f;
	public static final double fasterMul = 5.0f;
	public static final double slowMul = 0.35f;
	public static final double slowerMul = 0.1f;
	static public double getSprintMul() {
		double sprintMul_ = 1.0;
		if (fast)   sprintMul_ *= fastMul;
		if (faster) sprintMul_ *= fasterMul;
		if (slow)   sprintMul_ *= slowMul;
		if (slower) sprintMul_ *= slowerMul;
		return sprintMul_;
	}
	public static boolean flyEnabled        = false;
	public static boolean speedEnabled      = false;
	public static boolean noFallEnabled     = true;
	public static boolean stepEnabled       = true;
	public static boolean xrayEnabled       = false;
    public static boolean fullBrightEnabled = false;
	public static boolean freeCamEnabled    = false;
	public static boolean antiEffectEnabled = false;
	public static boolean noSlowEnabled     = false;
	public static boolean antiHumanEnabled  = false; // https://youtube.com/watch?v=WEMOCFe4EFE
	public static float originalStep;
	public static double originalGamma;
    public static boolean fast   = false;
    public static boolean faster = false;
	public static boolean slow   = false;
	public static boolean slower = false;
	public static final double fullBrightGamma = 10;
    public static Vec3d freeCamPos = new Vec3d(0, 0, 0);
	public static int tick = 0;
	private static final String bindCategory = "category.mekbhax";
	public static List<Block> xrayBlocks = List.of(
			Blocks.SPAWNER,
			Blocks.NETHERITE_BLOCK,
			Blocks.DIAMOND_BLOCK,
			Blocks.DIAMOND_ORE,
			Blocks.DEEPSLATE_DIAMOND_ORE,
			Blocks.IRON_BLOCK,
			Blocks.IRON_ORE,
			Blocks.DEEPSLATE_IRON_ORE,
			Blocks.EMERALD_BLOCK,
			Blocks.EMERALD_ORE,
			Blocks.DEEPSLATE_EMERALD_ORE,
			Blocks.NETHERITE_BLOCK,
			Blocks.ANCIENT_DEBRIS,
			Blocks.LAVA,
			Blocks.WATER,
			Blocks.OBSIDIAN,
			Blocks.MAGMA_BLOCK,
			Blocks.GLOWSTONE,
			Blocks.NETHER_GOLD_ORE,
			Blocks.LAVA,
			Blocks.STONE_BRICKS,
			Blocks.CRACKED_STONE_BRICKS,
			Blocks.NETHER_BRICKS,
			Blocks.POLISHED_BLACKSTONE_BRICKS,
			Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS
	);
	public static KeyBinding flyBind        = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.fly",        InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G,       bindCategory));
	public static KeyBinding speedBind      = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.speed",      InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H,       bindCategory));
	public static KeyBinding freeCamBind    = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.freecam",    InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K,       bindCategory));
	public static KeyBinding noFallBind     = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.nofall",     InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_U,       bindCategory));
	public static KeyBinding stepBind       = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.step",       InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J,       bindCategory));
	public static KeyBinding xrayBind       = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.xray",       InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O,       bindCategory));
	public static KeyBinding fullBrightBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Y,       bindCategory));
	public static KeyBinding slowBind       = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.slow",       InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT,    bindCategory));
	public static KeyBinding fastBind       = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.fast",       InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT,   bindCategory));
	public static KeyBinding flyAscendBind  = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.ascend",     InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UP,      bindCategory));
	public static KeyBinding flyDescendBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.descend",    InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_DOWN,    bindCategory));
	public static KeyBinding antiEffectBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.antieffect", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_L,       bindCategory));
	public static KeyBinding noSlowBind     = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.noslow",     InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K,       bindCategory));
	public static KeyBinding antiHumanBind  = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.mekbhax.antihuman",  InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, bindCategory));
	public static final Logger Logger = LoggerFactory.getLogger("mekbhax");
	@Override
	public void onInitialize() {
	}
}
