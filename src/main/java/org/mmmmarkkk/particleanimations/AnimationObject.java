package org.mmmmarkkk.particleanimations;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitTask;
import org.mmmmarkkk.particleanimations.animations.Animation;

import java.util.ArrayList;
import java.util.List;

@Data
public class AnimationObject {

    private String name;
    private int duration;
    private double speed;
    private int size;
    private int particleCount;
    private Particle particle;
    private List<AnimationType> animations;
    private List<Location> locations;
    private List<BukkitTask> animationTasks;
    private long startTime;

    public AnimationObject(String name, int duration, double speed, int size, int particleCount, Particle particle, List<AnimationType> animations, List<Location> locations) {
        this.name = name;
        this.duration = duration;
        this.speed = speed;
        this.size = size;
        this.particleCount = particleCount;
        this.particle = particle;
        this.animations = animations;
        this.locations = locations;
    }

    public void startAnimation() {
        animationTasks = new ArrayList<>();
        startTime = System.currentTimeMillis();

        for (AnimationType animationType : animations) {
            for (Location location : locations) {
                BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getPlugin(), () -> {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    if (elapsedTime >= duration * 1000L) {
                        stopAnimation();
                        return;
                    }

                    AnimationManager animationManager = Main.getPlugin().getAnimationManager();
                    Animation animation = animationManager.getAnimation(animationType);
                    animation.play(location, size, particleCount, particle);
                }, 0, 1);
                animationTasks.add(task);
            }
        }
    }

    public void stopAnimation() {
        if (animationTasks != null) {
            for (BukkitTask task : animationTasks) {
                task.cancel();
            }
            animationTasks.clear();
            startTime = 0;
        }
    }
}
