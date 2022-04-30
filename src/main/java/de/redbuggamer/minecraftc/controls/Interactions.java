package de.redbuggamer.minecraftc.controls;

import de.redbuggamer.minecraftc.utils.Utils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class Interactions {
    public static String blockinteraction(BlockPos pos) {
        Utils.BlockInteraction(pos);
        MinecraftClient.getInstance().player.swingHand(Hand.MAIN_HAND);
        return "true";
    }
    public static String selectslot(int slot) {
        //ClickSlotC2SPacket packet = new ClickSlotC2SPacket(123,123,slot,1,SlotActionType.SWAP,);
        //MinecraftClient.getInstance().player.networkHandler.sendPacket(packet);
        return "None";
    }
    public static void sendChatMessage(String s) {
        ChatMessageC2SPacket packet = new ChatMessageC2SPacket(s);
        MinecraftClient.getInstance().player.networkHandler.sendPacket(packet);
    }
}
