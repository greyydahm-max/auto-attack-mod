package me.johnadept;

import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static final KeyMapping.Category CATEGORY_AUTO_ATTACK =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath("auto_attack", "auto_attack"));

    public static KeyMapping toggleAttack;
    public static KeyMapping toggleRotation;

    public static void register() {
        toggleAttack = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.auto_attack.toggleAttack",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                CATEGORY_AUTO_ATTACK
        ));
        toggleRotation = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.auto_attack.toggleRotation",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_T,
                CATEGORY_AUTO_ATTACK
        ));
    }
}
