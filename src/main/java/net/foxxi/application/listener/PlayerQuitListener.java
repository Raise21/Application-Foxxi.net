package net.foxxi.application.listener;

import net.foxxi.application.Application;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

  private Application application;

  public PlayerQuitListener() {
    this.application = Application.getInstance();
  }

  @EventHandler
  public void expectQuit(PlayerQuitEvent event) {
    int players = Bukkit.getOnlinePlayers().size() - 1;

    for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
      application.getScoreboardManager()
          .updateOnlinePlayers(onlinePlayers, players);
    }
  }
}
