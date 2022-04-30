package de.redbuggamer.minecraftc.controls;

import de.redbuggamer.minecraftc.client.MinecraftCClient;
import de.redbuggamer.minecraftc.utils.Utils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Movement {
    public static int delay=12;
    public static String forward(int blocks,boolean sprint) {
        double x = MinecraftClient.getInstance().player.getX();
        double y = MinecraftClient.getInstance().player.getY();
        double z = MinecraftClient.getInstance().player.getZ();
        Vec3d startpos = new Vec3d(x,y,z);
        if (sprint) KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keySprint.getDefaultKey(),true);
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyForward.getDefaultKey(),true);
        x = MinecraftClient.getInstance().player.getX();
        y = MinecraftClient.getInstance().player.getY();
        z = MinecraftClient.getInstance().player.getZ();
        Vec3d currpos = new Vec3d(x,y,z);
         for (int i = 0;startpos.distanceTo(currpos) < blocks-0.4 && i <blocks*40;i++) {
             x = MinecraftClient.getInstance().player.getX();
             y = MinecraftClient.getInstance().player.getY();
             z = MinecraftClient.getInstance().player.getZ();
             currpos = new Vec3d(x,y,z);
             try {
                 Thread.sleep(delay);
                } catch (InterruptedException e) {}
            }
        if (sprint) KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keySprint.getDefaultKey(), false);
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyForward.getDefaultKey(),false);
        x = MinecraftClient.getInstance().player.getX();
        y = MinecraftClient.getInstance().player.getY();
        z = MinecraftClient.getInstance().player.getZ();
        Vec3d endpos = new Vec3d(x,y,z);
        if (startpos.distanceTo(endpos)> (float) blocks-2 && MinecraftCClient.handleErrors) {
            return String.valueOf(startpos.distanceTo(endpos));
        }else{
            return "error,pathblocked";
        }
    }
    public static String left(int blocks) {
        double x = MinecraftClient.getInstance().player.getX();
        double y = MinecraftClient.getInstance().player.getY();
        double z = MinecraftClient.getInstance().player.getZ();
        Vec3d startpos = new Vec3d(x,y,z);
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyLeft.getDefaultKey(),true);
                x = MinecraftClient.getInstance().player.getX();
                y = MinecraftClient.getInstance().player.getY();
                z = MinecraftClient.getInstance().player.getZ();
                Vec3d currpos = new Vec3d(x,y,z);
                for (int i = 0;startpos.distanceTo(currpos) < blocks-0.4 && i <blocks*40;i++) {
                    x = MinecraftClient.getInstance().player.getX();
                    y = MinecraftClient.getInstance().player.getY();
                    z = MinecraftClient.getInstance().player.getZ();
                    currpos = new Vec3d(x,y,z);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyLeft.getDefaultKey(),false);
        x = MinecraftClient.getInstance().player.getX();
        y = MinecraftClient.getInstance().player.getY();
        z = MinecraftClient.getInstance().player.getZ();
        Vec3d endpos = new Vec3d(x,y,z);

        if (startpos.distanceTo(endpos)> (float) blocks-2 && MinecraftCClient.handleErrors) {
            return String.valueOf(startpos.distanceTo(endpos));
        }else{
            return "error,pathblocked";
        }
    }
    public static String right(int blocks) {
        double x = MinecraftClient.getInstance().player.getX();
        double y = MinecraftClient.getInstance().player.getY();
        double z = MinecraftClient.getInstance().player.getZ();
        Vec3d startpos = new Vec3d(x,y,z);
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyRight.getDefaultKey(),true);
                x = MinecraftClient.getInstance().player.getX();
                y = MinecraftClient.getInstance().player.getY();
                z = MinecraftClient.getInstance().player.getZ();
                Vec3d currpos = new Vec3d(x,y,z);
                for (int i = 0;startpos.distanceTo(currpos) < blocks-0.4 && i <blocks*40;i++) {
                    x = MinecraftClient.getInstance().player.getX();
                    y = MinecraftClient.getInstance().player.getY();
                    z = MinecraftClient.getInstance().player.getZ();
                    currpos = new Vec3d(x,y,z);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyRight.getDefaultKey(),false);


        x = MinecraftClient.getInstance().player.getX();
        y = MinecraftClient.getInstance().player.getY();
        z = MinecraftClient.getInstance().player.getZ();
        Vec3d endpos = new Vec3d(x,y,z);

        if (startpos.distanceTo(endpos)> (float) blocks-2 && MinecraftCClient.handleErrors) {
            return String.valueOf(startpos.distanceTo(endpos));
        }else{
            return "error,pathblocked";
        }
    }
    public static String back(int blocks) {
        double x = MinecraftClient.getInstance().player.getX();
        double y = MinecraftClient.getInstance().player.getY();
        double z = MinecraftClient.getInstance().player.getZ();
        Vec3d startpos = new Vec3d(x,y,z);
        KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyBack.getDefaultKey(),true);
         x = MinecraftClient.getInstance().player.getX();
         y = MinecraftClient.getInstance().player.getY();
         z = MinecraftClient.getInstance().player.getZ();
         Vec3d currpos = new Vec3d(x,y,z);
                for (int i = 0;startpos.distanceTo(currpos) < blocks-0.4 && i <blocks*40;i++) {
                    x = MinecraftClient.getInstance().player.getX();
                    y = MinecraftClient.getInstance().player.getY();
                    z = MinecraftClient.getInstance().player.getZ();
                    currpos = new Vec3d(x,y,z);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyBack.getDefaultKey(),false);
        x = MinecraftClient.getInstance().player.getX();
        y = MinecraftClient.getInstance().player.getY();
        z = MinecraftClient.getInstance().player.getZ();
        Vec3d endpos = new Vec3d(x,y,z);

        if (startpos.distanceTo(endpos)> (float) blocks-2 && MinecraftCClient.handleErrors) {
            return String.valueOf(startpos.distanceTo(endpos));
        }else{
            return "error,pathblocked";
        }
    }
    public static String jump(int times) {
                for (int i = 1;i<=times;i++) {
                    KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyJump.getDefaultKey(), true);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                    KeyBinding.setKeyPressed(MinecraftClient.getInstance().options.keyJump.getDefaultKey(), false);
                    try {
                        Thread.sleep(900);} catch (InterruptedException e) {}
                }
        return "None";
    }
    public static String setrotation(int yaw, int pitch) {
        MinecraftClient.getInstance().player.setYaw(yaw);
        MinecraftClient.getInstance().player.setPitch(pitch);
        return "None";
    }
    public static String getposition() {
        String x = String.valueOf(MinecraftClient.getInstance().player.getX());
        String y = String.valueOf(MinecraftClient.getInstance().player.getY());
        String z = String.valueOf(MinecraftClient.getInstance().player.getZ());
        return x+","+y+","+z;
    }
    public static String getrotation() {
        String yaw = String.valueOf(MinecraftClient.getInstance().player.getYaw());
        String pitch = String.valueOf(MinecraftClient.getInstance().player.getPitch());
        return yaw+","+pitch;
    }
}
