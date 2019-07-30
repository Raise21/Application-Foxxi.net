package net.foxxi.application.listener.inventory;

import net.foxxi.application.Application;
import net.foxxi.application.inventory.config.InventoryItem;
import net.foxxi.application.session.Session;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

  private Session session;

  public InventoryClickListener() {
    session = Application.getInstance().getSession();
  }

  @EventHandler
  public void expectClick(InventoryClickEvent event) {
    event.setCancelled(true);
    Player player = (Player) event.getWhoClicked();

    if (event.getCurrentItem() == null ||
        !event.getCurrentItem().hasItemMeta()) {
      return;
    }

    if (event.getClickedInventory().getTitle()
        .equalsIgnoreCase(session.getInventoryConfiguration()
            .getInventoryName().replace("&", "§"))) {
      for (InventoryItem inventoryItem :
          session.getInventoryConfiguration().getItemList()) {
        if (event.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(inventoryItem.getDisplayName()
                .replace("&", "§"))) {
          if (inventoryItem.getType().toLowerCase()
              .equalsIgnoreCase("gamemode0")) {
            session.changeGamemode(player, "0");
            player.closeInventory();
            break;
          } else if (inventoryItem.getType().toLowerCase()
              .equalsIgnoreCase("gamemode1")) {
            session.changeGamemode(player, "1");
            player.closeInventory();
            break;
          } else if (inventoryItem.getType().toLowerCase()
              .equalsIgnoreCase("gamemode2")) {
            session.changeGamemode(player, "2");
            player.closeInventory();
            break;
          } else if (inventoryItem.getType().toLowerCase()
              .equalsIgnoreCase("gamemode3")) {
            session.changeGamemode(player, "3");
            player.closeInventory();
            break;
          } else if (inventoryItem.getType().toLowerCase()
              .equalsIgnoreCase("storm")) {
            session.changeWeather(player, player.getWorld(),
                true, "storm");
            break;
          } else if (inventoryItem.getType().toLowerCase()
              .equalsIgnoreCase("clear")) {
            session.changeWeather(player, player.getWorld(),
                false, "clear");
            break;
          } else if (inventoryItem.getType().toLowerCase()
              .equalsIgnoreCase("day")) {
            session.changeTime(player, player.getWorld(),
                6000, "day");
            break;
          } else if (inventoryItem.getType().toLowerCase()
              .equalsIgnoreCase("night")) {
            session.changeTime(player, player.getWorld(),
                17000, "night");
            break;
          } else {
            break;
          }
        }
      }
    }
  }
}
