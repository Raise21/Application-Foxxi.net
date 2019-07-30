package net.foxxi.application.inventory;

import java.util.concurrent.CompletableFuture;
import net.foxxi.application.Application;
import net.foxxi.application.inventory.config.InventoryItem;
import net.foxxi.application.session.Session;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ApplicationInventory {

  private Session session;
  private Inventory inventory;

  private ApplicationInventory() {
    session = Application.getInstance().getSession();
    inventory = Bukkit.createInventory(null,
        session.getInventoryConfiguration().getSize(),
        session.getInventoryConfiguration()
            .getInventoryName().replace("&", "§"));

    for (InventoryItem inventoryItem :
        session.getInventoryConfiguration().getItemList()) {
      ItemStack stack = new ItemStack(Material.valueOf(inventoryItem.getMaterial()));
      stack.setAmount(inventoryItem.getAmount());
      stack.setDurability((short) inventoryItem.getShortId());
      inventoryItem.setType(inventoryItem.getType());
      ItemMeta meta = stack.getItemMeta();
      meta.setDisplayName(inventoryItem.getDisplayName()
          .replace("&", "§"));
      stack.setItemMeta(meta);
      inventory.setItem(inventoryItem.getSlot(), stack);
    }
  }

  public void open(Player player) {
    CompletableFuture.supplyAsync(() -> player.openInventory(inventory));
  }

  public static ApplicationInventory create() {
    return new ApplicationInventory();
  }
}
