package net.geekmc.turinglib.color

import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player

fun CommandSender.send(message:String) {
    sendMessage(ColorUtil.deserializer.deserialize(message))
}