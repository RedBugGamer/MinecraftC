package de.redbuggamer.minecraftc;

import de.redbuggamer.minecraftc.client.MinecraftCClient;
import de.redbuggamer.minecraftc.controls.ClientWorldAccess;
import de.redbuggamer.minecraftc.controls.Interactions;
import de.redbuggamer.minecraftc.controls.Movement;
import de.redbuggamer.minecraftc.controls.Toggles;
import de.redbuggamer.minecraftc.utils.Utils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Websocket extends Thread{
    private ServerSocket server;
    private Socket client;
    private boolean running;
    public void stopThread() {
        this.running = false;
    }

    public void reconnect() {
        while (this.server==null) {
            try {
            this.server = new ServerSocket(1234);
            Thread.sleep(1000);
            } catch (IOException | InterruptedException ignored) {}
        }
    }
    public void run() {
        System.out.println("Starting Websocket");
        reconnect();

        try {
            this.client=this.server.accept();
            Utils.sendMessage("Connection from "+client.getInetAddress(),true);
            MinecraftCClient.listening = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.running= true;
        while (this.running && this.client != null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), false);
                String fromClient = in.readLine();
                if (fromClient != null) Utils.sendMessage("Incoming: "+fromClient,true);
                String returnvalue = "None";
                if (fromClient != null) {

                    String[] array = fromClient.split("\\.");
                    PlayerEntity player = MinecraftClient.getInstance().player;

                    switch (array[0]) {
                        case "player": {
                            switch (array[1]) {
                                case "movement": {
                                    switch (array[2]) {
                                        case "forward": {
                                            returnvalue = Movement.forward(Integer.parseInt(array[3]), array[4] == "true");
                                            break;
                                        }
                                        case "left": {
                                            returnvalue = Movement.left(Integer.parseInt(array[3]));
                                            break;
                                        }
                                        case "right": {
                                            returnvalue = Movement.right(Integer.parseInt(array[3]));
                                            break;
                                        }
                                        case "back": {
                                            returnvalue = Movement.back(Integer.parseInt(array[3]));
                                            break;
                                        }
                                        case "jump": {
                                            returnvalue = Movement.jump(Integer.parseInt(array[3]));
                                            break;
                                        }
                                        case "setrotation": {
                                            returnvalue = Movement.setrotation(Integer.parseInt(array[3]),Integer.parseInt(array[4]));
                                            break;
                                        }
                                        case "getposition": {
                                            returnvalue = Movement.getposition();
                                            break;
                                        }
                                        case "getrotation": {
                                            returnvalue = Movement.getrotation();
                                            break;
                                        }
                                    }
                                    break;
                                }
                                case "blockinteraction": {
                                    BlockPos pos = new BlockPos(Integer.parseInt(array[2]),Integer.parseInt(array[3]),Integer.parseInt(array[4]));
                                    Interactions.blockinteraction(pos);
                                    break;
                                }
                                case "toggle": {
                                    switch (array[2]) {
                                        case "sneak": {
                                            Toggles.sneaking(array[3]);
                                            break;
                                        }
                                        case "jump": {
                                            Toggles.jumping(array[3]);
                                            break;
                                        }
                                        case "left": {
                                            Toggles.left(array[3]);
                                            break;
                                        }
                                        case "right": {
                                            Toggles.right(array[3]);
                                            break;
                                        }
                                        case "forward": {
                                            Toggles.forward(array[3]);
                                            break;
                                        }
                                        case "back": {
                                            Toggles.back(array[3]);
                                            break;
                                        }
                                        case "errorhandling": {
                                            MinecraftCClient.handleErrors=Utils.getbool(array[3]);
                                            break;
                                        }
                                        default: {
                                            Utils.sendMessage("§4Error at: " + fromClient,false);
                                            System.out.println("Och ne...4");
                                        }
                                    }
                                    break;
                                }
                                case "chatmessage": {
                                    Interactions.sendChatMessage(array[2].replace("_dot_","."));
                                    break;
                                }
                                default: {
                                    Utils.sendMessage("§4Error at: " + fromClient,false);
                                    System.out.println("Och ne...3");
                                    break;
                                }
                            }
                            break;
                        }
                        case "client": {
                            switch (array[1]) {
                                default: {
                                    Utils.sendMessage("§4Error at: " + fromClient,false);
                                    System.out.println("Och ne...2");
                                }
                            }
                            break;
                        }
                        case "world": {
                            switch (array[1]) {
                                case "getBlock": {
                                    returnvalue = ClientWorldAccess.getBlock(Integer.parseInt(array[2]),Integer.parseInt(array[3]),Integer.parseInt(array[4]));
                                    break;
                                }
                                case "getBlocks" :{
                                    returnvalue = ClientWorldAccess.getBlocks(Integer.parseInt(array[2]));
                                    break;
                                }
                                case "getEntitys": {
                                    returnvalue = ClientWorldAccess.getEntitys(Integer.parseInt(array[2]));
                                    break;
                                }
                                case "hitEntity": {
                                    ClientWorldAccess.hitEntity(Integer.parseInt(array[2]));
                                    break;
                                }
                                default: {
                                    Utils.sendMessage("§4Error at: " + fromClient,false);
                                    System.out.println("Och ne...2");
                                }
                            }
                            break;
                        }
                        default: {
                            Utils.sendMessage("§4Error at: " + fromClient,false);
                            System.out.println("Och ne...1");
                        }
                    }

                out.println(returnvalue+"\r\n");
                out.flush();
                if (fromClient != null) {
                    System.out.println("Operation done");
                }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Utils.sendMessage("Thread stopped",false);
        try {
            this.server.close();
            this.client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
