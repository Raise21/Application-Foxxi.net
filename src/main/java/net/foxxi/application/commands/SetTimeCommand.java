package net.foxxi.application.commands;

import net.foxxi.application.Application;
import net.foxxi.application.session.Session;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTimeCommand implements CommandExecutor {

  private Session session;

  public SetTimeCommand() {
    session = Application.getInstance().getSession();
  }


  @Override
  public boolean onCommand(CommandSender sender, Command command, String label,
      String[] args) {

    if (!(sender instanceof Player)) {
      sender.sendMessage("You must be a player!");
      return true;
    }

    Player player = (Player) sender;

    if (!player.hasPermission("application.command.settime")) {
      player.sendMessage(session.getMessagesConfiguration()
          .getNopermission().replace("&", "§"));
      return true;
    }

    if (args.length == 1) {
      if (args[0].equalsIgnoreCase("day")) {
        session.changeTime(player, player.getWorld(),
            6000, "day");
      } else if (args[0].equalsIgnoreCase("night")) {
        session.changeTime(player, player.getWorld(),
            17000, "night");
      }
    } else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("day")) {
        String wordName = args[1];
        if (Bukkit.getWorld(wordName) == null) {
          player.sendMessage(
              session.getMessagesConfiguration()
                  .getWorldNotExists().replace("&", "§"));
          return true;
        }

        session.changeTime(player, Bukkit.getWorld(wordName),
            6000, "day");
      } else if (args[0].equalsIgnoreCase("night")) {
        String wordName = args[1];
        if (Bukkit.getWorld(wordName) == null) {
          player.sendMessage(
              session.getMessagesConfiguration()
                  .getWorldNotExists().replace("&", "§"));
          return true;
        }
        session.changeTime(player, Bukkit.getWorld(wordName),
            6000, "night");
      }
    } else {
      player.sendMessage(session.getMessagesConfiguration()
          .getPrefix().replace("&", "§")
          + "§cBenutze: §a/settime <DAY,NIGHT> <WORLD>");
    }
    return true;
  }
}
