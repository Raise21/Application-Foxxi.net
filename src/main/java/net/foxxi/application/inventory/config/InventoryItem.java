package net.foxxi.application.inventory.config;

import lombok.Data;

@Data
public class InventoryItem {
  private String material;
  private String displayName;
  private String type;
  private int shortId;
  private int amount;
  private int slot;
}
