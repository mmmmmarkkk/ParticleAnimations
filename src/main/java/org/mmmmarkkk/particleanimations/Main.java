package org.mmmmarkkk.particleanimations;

import lombok.Getter;
import lombok.extern.java.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

@Log
@Getter
public final class Main extends JavaPlugin implements Listener {

    private static Main plugin;
    private AnimationManager animationManager;
    private Set<AnimationObject> animations = new HashSet<>();

    @Override
    public void onEnable() {
        plugin = this;
        animationManager = new AnimationManager();
        Bukkit.getPluginManager().registerEvents(this, this);
        this.initConfig();
        this.initAnimations();
    }

    public void initConfig() {
        FileConfiguration config = this.getConfig();
        this.saveDefaultConfig();
        ConfigurationSection animationsConfig = config.getConfigurationSection("animations");

        for (String animationKey : animationsConfig.getKeys(false)) {
            try {
                ConfigurationSection animationConfig = animationsConfig.getConfigurationSection(animationKey);
                int duration = animationConfig.getInt("duration");
                double speed = animationConfig.getDouble("speed");
                int size = animationConfig.getInt("size");
                int particleCount = animationConfig.getInt("particle-count");

                log.info("Проверка числа партиклов..");
                while (!NumUtils.isFavorable(particleCount, size)) {
                    particleCount++;
                }

                Particle particle = Particle.valueOf(animationConfig.getString("particle-type"));

                List<AnimationType> animationTypes = animationConfig.getStringList("shape").stream()
                        .map(AnimationType::valueOf)
                        .collect(Collectors.toList());

                List<String> cords = animationConfig.getStringList("cords");
                List<Location> locations = cords.stream()
                        .map(c -> {
                            String[] xyz = c.split(" ");
                            return new Location(this.getServer().getWorld("world"), Double.parseDouble(xyz[0]), Double.parseDouble(xyz[1]), Double.parseDouble(xyz[2]));
                        })
                        .collect(Collectors.toList());

                AnimationObject animationObject = new AnimationObject(animationKey, duration, speed, size, particleCount, particle, animationTypes, locations);
                animations.add(animationObject);

            } catch (Exception e) {
                log.warning("Ошибка при инициализации анимации: " + animationKey);
            }
        }
    }

    public void initAnimations() {
        for (AnimationObject animationObject : animations) {
            animationObject.startAnimation();
        }
    }

    @Override
    public void onDisable() {
    }

    public static Main getPlugin() {
        return plugin;
    }
}
