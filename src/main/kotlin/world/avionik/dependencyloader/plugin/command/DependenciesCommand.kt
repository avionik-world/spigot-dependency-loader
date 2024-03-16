package world.avionik.dependencyloader.plugin.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import java.io.File

/**
 * @author Niklas Nieberler
 */

class DependenciesCommand(
    private val dependencyFiles: List<File>
) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        sender.sendMessage("Dependencies (${dependencyFiles.size}):")
        sender.sendMessage("§8-§a " + this.dependencyFiles.joinToString("§f,§a ") { it.nameWithoutExtension })
        return true
    }

}