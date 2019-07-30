package net.foxxi.application.commands;

import net.foxxi.application.Application;
import net.foxxi.application.session.Session;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWeatherCommand implements CommandExecutor {

  private Session session;

  public SetWeatherCommand() {
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

    if (!player.hasPermission("application.command.setweather")) {
      player.sendMessage(session.getMessagesConfiguration()
          .getNopermission().replace("&", "§"));
      return true;
    }

    if (args.length == 1) {
      if (args[0].equalsIgnoreCase("storm")) {
        session.changeWeather(player, player.getWorld(),
            true, "storm");
      } else if (args[0].equalsIgnoreCase("clear")) {
        session.changeWeather(player, player.getWorld(),
            false, "clear");
      }
    } else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("storm")) {
        String wordName = args[1];
        if (Bukkit.getWorld(wordName) == null) {
          player.sendMessage(
              session.getMessagesConfiguration()
                  .getWorldNotExists().replace("&", "§"));
          return true;
        }

        session.changeWeather(player, player.getWorld(),
            true, "storm");
      } else if (args[0].equalsIgnoreCase("clear")) {
        String wordName = args[1];
        if (Bukkit.getWorld(wordName) == null) {
          player.sendMessage(
              session.getMessagesConfiguration()
                  .getWorldNotExists().replace("&", "§"));
          return true;
        }

        session.changeWeather(player, player.getWorld(),
            false, "clear");
      }
    } else {
      player.sendMessage(session.getMessagesConfiguration()
          .getPrefix().replace("&", "§")
          + "§cBenutze: §a/setweather <STORM,CLEAR> <WORLD>");
    }

    return true;
  }

}
