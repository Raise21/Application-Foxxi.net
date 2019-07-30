package net.foxxi.application.listener;

import net.foxxi.application.Application;
import net.foxxi.application.session.Session;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

  private Session session;

  public PlayerChatListener() {
    session = Application.getInstance().getSession();
  }

  @EventHandler
  public void expectChat(AsyncPlayerChatEvent event) {
    event.setCancelled(true);
    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
      onlinePlayer.sendMessage(session.getChatConfiguration().getChatPrefix()
          .replace("&", "§")
          .replace("%name%", event.getPlayer().getName()) + event.getMessage());
    }
  }
}
