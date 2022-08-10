package net.geekmc.turinglib.command

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.utils.callback.CommandCallback
import world.cepi.kstom.Manager


object DefaultCommandSettings {

    fun enable() {
        Manager.command.unknownCommandCallback =
            CommandCallback { sender: CommandSender, cmd: String ->
                sender.sendMessage("未知命令: $cmd")
            }

    }
}