package org.mmmmarkkk.particleanimations.animations;

import lombok.extern.java.Log;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

@Log
public class CubeAnimation implements Animation {

    @Override
    public void play(Location location, int size, int particleCount, Particle particle) {
        World world = location.getWorld();

        double minY = location.getY() - (double) size / 2;
        double minX = location.getX() - (double) size / 2;
        double minZ = location.getZ() - (double) size / 2;
        double maxY = minY + size;
        double maxX = minX + size;
        double maxZ = minZ + size;

        double epsilon = 0.001;
        double step = (double) size / Math.round(Math.sqrt((double) particleCount / 6));

        for (double y = minY; y <= maxY; y += step) {
            for (double x = minX; x <= maxX; x += step) {
                for (double z = minZ; z <= maxZ; z += step) {
                    boolean isOnEdge = Math.abs(x - minX) < epsilon || Math.abs(x - maxX) < epsilon ||
                            Math.abs(y - minY) < epsilon || Math.abs(y - maxY) < epsilon ||
                            Math.abs(z - minZ) < epsilon || Math.abs(z - maxZ) < epsilon;
                    if (isOnEdge) {
                        Location particleLocation = new Location(world, x, y, z);
                        world.spawnParticle(particle, particleLocation, 1);
                    }
                }
            }
        }
    }
}
