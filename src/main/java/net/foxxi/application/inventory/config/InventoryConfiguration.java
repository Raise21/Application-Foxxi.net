package net.foxxi.application.inventory.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.Data;
import org.bukkit.Bukkit;

@Data
public class InventoryConfiguration {

  private static transient Gson GSON = new GsonBuilder()
      .setPrettyPrinting()
      .disableHtmlEscaping()
      .create();

  private transient File file;

  public InventoryConfiguration(File file) {
    this.file = file;
  }

  private String inventoryName = "&a&lApplication";
  private int size = 27;
  private List<InventoryItem> itemList = new ArrayList<>();

  public void defaultInventory(InventoryItem item1,
      InventoryItem item2,
      InventoryItem item3,
      InventoryItem item4,
      InventoryItem item5) {

    item1.setMaterial("GOLDEN_APPLE");
    item1.setShortId((short) 1);
    item1.setAmount(1);
    item1.setDisplayName("&eGAMEMODE");
    item1.setSlot(0);
    item1.setType("OTHER");

    item2.setMaterial("DIAMOND_HELMET");
    item2.setShortId((short) 0);
    item2.setAmount(1);
    item2.setDisplayName("&bGamemode 0");
    item2.setSlot(9);
    item2.setType("GAMEMODE0");

    item3.setMaterial("DIAMOND_CHESTPLATE");
    item3.setShortId((short) 0);
    item3.setAmount(1);
    item3.setDisplayName("&bGamemode 1");
    item3.setSlot(10);
    item3.setType("GAMEMODE1");

    item4.setMaterial("DIAMOND_LEGGINGS");
    item4.setShortId((short) 0);
    item4.setAmount(1);
    item4.setDisplayName("&bGamemode 2");
    item4.setSlot(11);
    item4.setType("GAMEMODE2");

    item5.setMaterial("DIAMOND_BOOTS");
    item5.setShortId((short) 0);
    item5.setAmount(1);
    item5.setDisplayName("&bGamemode 3");
    item5.setSlot(12);
    item5.setType("GAMEMODE3");

    itemList.add(item1);
    itemList.add(item2);
    itemList.add(item3);
    itemList.add(item4);
    itemList.add(item5);
  }


  public void save() {
    try {
      Files.write(file.toPath(), GSON.toJson(this).getBytes());
    } catch (IOException exception) {
      Bukkit.getLogger().log(Level.WARNING, exception.toString());
    }
  }
}
