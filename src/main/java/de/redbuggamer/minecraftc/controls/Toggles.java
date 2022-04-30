package de.redbuggamer.minecraftc.controls;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class Toggles {
    private static boolean getbool(String str)  {
        return str.equals("true");
    }
    public static void sneaking(String s) {
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keySneak.getDefaultKey(), getbool(s));
    }
    public static void jumping(String s) {
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyJump.getDefaultKey(), getbool(s));
    }
    public static void left(String s) {
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyLeft.getDefaultKey(), getbool(s));
    }
    public static void right(String s) {
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyRight.getDefaultKey(), getbool(s));
    }
    public static void forward(String s) {
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyForward.getDefaultKey(), getbool(s));
    }
    public static void back(String s) {
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyBack.getDefaultKey(), getbool(s));
    }
}
