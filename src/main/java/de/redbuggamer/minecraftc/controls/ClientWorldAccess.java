package de.redbuggamer.minecraftc.controls;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class ClientWorldAccess {
    public static String getBlock(int x, int y, int z) {
        BlockState block=MinecraftClient.getInstance().world.getBlockState(new BlockPos(x,y,z));
        return block.getBlock().getTranslationKey();
    }
    public static String getBlocks(int distance) {
        double x_pos = MinecraftClient.getInstance().player.getX();
        double Y_pos = MinecraftClient.getInstance().player.getY();
        double z_pos = MinecraftClient.getInstance().player.getZ();
        //distance = (int) Math.floor(distance/2);

        String layer_x = "";
        for (int x = -distance; x<=distance; x++) {
            String layer_y = "";
            for (int y = -distance; y<=distance; y++) {
                String layer_z = "";
                for (int z = -distance; z<=distance; z++) {
                    layer_z +=getBlock((int) (x+Math.floor(x_pos)), (int) (y+Math.floor(Y_pos)), (int) (z+Math.floor((z_pos))))+"_z_";
                    //MinecraftClient.getInstance().player.setPos(x+Math.floor(x_pos),y+Math.floor(Y_pos),z+Math.floor((z_pos)));
                    //try {Thread.sleep((100));} catch (InterruptedException e) {}
                }
                layer_y += layer_z +"_y_";
            }
            layer_x += layer_y + "_x_";
        }

        return layer_x+","+Math.floor(x_pos)+","+Math.floor(Y_pos)+","+Math.floor(z_pos);
    }
    public static String getEntitys(int distance) {
        String output = "";
        Vec3i vec = new Vec3i(MinecraftClient.getInstance().player.getX(),
                MinecraftClient.getInstance().player.getY(),
                MinecraftClient.getInstance().player.getZ());
        for (Entity entity : MinecraftClient.getInstance().world.getEntities()) {
            if (entity.getBlockPos().isWithinDistance(vec,distance)) {
                if (entity != MinecraftClient.getInstance().player)
                    output +=
                         entity.getType().getTranslationKey()+","
                        +entity.getId()+","
                        +entity.getX()+","
                        +entity.getY()+","
                        +entity.getZ()+","
                        +entity.getUuidAsString()+","
                        +entity.distanceTo(MinecraftClient.getInstance().player)+"_mob_";
            }
        }
        return output;
    }
    public static void hitEntity(int id) {
        Entity entity = MinecraftClient.getInstance().world.getEntityById(id);
        MinecraftClient.getInstance().interactionManager.attackEntity(MinecraftClient.getInstance().player,entity);
        MinecraftClient.getInstance().player.swingHand(Hand.MAIN_HAND);
    }
}
