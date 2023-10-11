package org.mmmmarkkk.particleanimations;

import org.mmmmarkkk.particleanimations.animations.*;

import java.util.HashMap;
import java.util.Map;

public class AnimationManager {

    private final Map<AnimationType, Animation> animations = new HashMap<>();

    public AnimationManager() {
        animations.put(AnimationType.CUBE, new CubeAnimation());
        animations.put(AnimationType.SPHERE, new SphereAnimation());
        animations.put(AnimationType.RECTANGLE, new RectangleAnimation());
    }

    public Animation getAnimation(AnimationType type) {
        return animations.get(type);
    }


}
