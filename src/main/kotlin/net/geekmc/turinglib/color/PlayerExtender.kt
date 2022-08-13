package net.geekmc.turinglib.color

import net.minestom.server.entity.Player

fun Player.send(message:String) {
    sendMessage(ColorUtil.deserializer.deserialize(message))
}