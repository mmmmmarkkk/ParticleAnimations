package org.mmmmarkkk.particleanimations.animations;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class SphereAnimation implements Animation{

    @Override
    public void play(Location location, int size, int particleCount, Particle particle) {
        World world = location.getWorld();

        for (int i = 0; i < particleCount; i++) {
            double u = Math.random();
            double v = Math.random();

            double theta = 2 * Math.PI * u;
            double phi = Math.acos(2 * v - 1);

            double x = size * Math.sin(phi) * Math.cos(theta);
            double y = size * Math.sin(phi) * Math.sin(theta);
            double z = size * Math.cos(phi);

            Location particleLocation = location.clone().add(x, y, z);
            world.spawnParticle(particle, particleLocation, 1, 0, 0, 0, 0);
        }
    }
}
