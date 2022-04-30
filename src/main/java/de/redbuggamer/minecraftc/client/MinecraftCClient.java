package de.redbuggamer.minecraftc.client;

import com.mojang.brigadier.CommandDispatcher;
import de.redbuggamer.minecraftc.Websocket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class MinecraftCClient implements ClientModInitializer {
    private Websocket ws;
    public static boolean listening;
    public static boolean handleErrors;

    @Override
    public void onInitializeClient() {
        this.handleErrors = true;
        this.ws= new Websocket();
        this.ws.start();
        ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("reconnect").executes(context -> {
            if (!listening) {
                this.listening =true;
                reconnect();
                MinecraftClient.getInstance().player.sendMessage(Text.of("Relaunching Websocket"),false);
            } else {
                MinecraftClient.getInstance().player.sendMessage(Text.of("§4Websocket is already listening"),false);
            }
            return 1;
        }));


    }
    public void reconnect() {
        this.ws.stopThread();
        this.ws = new Websocket();
        this.ws.start();
    }

}
