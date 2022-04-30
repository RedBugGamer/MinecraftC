package de.redbuggamer.minecraftc.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Utils {
    public static void BlockInteraction(BlockPos pos) {
        Vec3d vec = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
        Hand hand = Hand.MAIN_HAND;
        for (Direction side : Direction.values()) {
            PlayerInteractBlockC2SPacket packet = new PlayerInteractBlockC2SPacket(hand, new BlockHitResult(vec, side,pos,false));
            MinecraftClient.getInstance().player.networkHandler.sendPacket(packet);
        }
    }
    public static boolean InWorld() {
        return MinecraftClient.getInstance().player.world != null;
    }
    public static void sendMessage(String message,boolean actionbar) {
        if (InWorld())
        MinecraftClient.getInstance().player.sendMessage(Text.of(message),actionbar);
    }
    public static boolean getbool(String str)  {
        return str.equals("true");
    }
}
