package de.xenodev.utils;

import de.xenodev.xBuilding;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class BoardBuilder {

    public static void createBoard(Player player) {
        var manager = player.getServer().getScoreboardManager();
        if (manager != null && player.getScoreboard().equals(manager.getMainScoreboard())) {
            player.setScoreboard(manager.getNewScoreboard());
        }
        Scoreboard scoreboard = player.getScoreboard();
        Objective objective = scoreboard.registerNewObjective("main", "main", "§3§lT§beam§3§lM§bega§3§lB§byte");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§7§o").setScore(9);
        objective.getScore("§7» §fWeltname:").setScore(8);
        objective.getScore(updateTeam(scoreboard, "world", "§e§l" + player.getWorld().getName().toUpperCase(), "", ChatColor.AQUA)).setScore(7);
        objective.getScore("§4").setScore(6);
        objective.getScore("§7» §fBauzeit:").setScore(5);
        objective.getScore(updateTeam(scoreboard, "time", "§a§l" + new TimeBuilder(player.getWorld().getName()).changeTime(), "", ChatColor.YELLOW)).setScore(4);
        objective.getScore("§3").setScore(3);
        objective.getScore("§7» §fUnsere Website").setScore(2);
        objective.getScore("§d§lclan-tmb.de").setScore(1);
    }

    public static void updateBoard() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    Scoreboard scoreboard = player.getScoreboard();
                    Objective objective = scoreboard.getObjective("main");

                    objective.getScore(updateTeam(scoreboard, "world", "§e§l" + player.getWorld().getName().toUpperCase(), "", ChatColor.AQUA)).setScore(7);
                    objective.getScore(updateTeam(scoreboard, "time", "§a§l" + new TimeBuilder(player.getWorld().getName()).changeTime(), "", ChatColor.YELLOW)).setScore(4);
                }
            }

        }.runTaskTimerAsynchronously(xBuilding.getInstance(), 0, 20L*10);
    }

    public static Team getTeam(Scoreboard board, String Team, String prefix, String suffix) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setAllowFriendlyFire(false);
        team.setCanSeeFriendlyInvisibles(true);

        return team;
    }

    public static String updateTeam(Scoreboard board, String Team, String prefix, String suffix, ChatColor entry) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());

        return entry.toString();
    }

}
