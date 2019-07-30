package net.foxxi.application.scoreboard.config;

import lombok.Data;

@Data
public class Line {
  private String text;
  private String team;
  private String entry;
  private int slot;
}
