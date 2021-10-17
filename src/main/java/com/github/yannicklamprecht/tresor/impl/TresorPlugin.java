package com.github.yannicklamprecht.tresor.impl;

import com.github.yannicklamprecht.tresor.api.Tresor;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class TresorPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        var tresor = new SampleTresor((runnable) -> getServer().getScheduler().runTaskAsynchronously(this, runnable));

        getServer().getServicesManager().register(Tresor.class, tresor, this, ServicePriority.High);
    }
}
