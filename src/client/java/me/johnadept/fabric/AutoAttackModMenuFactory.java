package me.johnadept.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.johnadept.config.ConfigScreen;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.Screen;

public class AutoAttackModMenuFactory implements ModMenuApi {

    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
            ConfigScreen configScreen = new ConfigScreen();
            return parent -> (Screen) configScreen.createScreen(parent);
        }
        return parent -> null;
    }
}
