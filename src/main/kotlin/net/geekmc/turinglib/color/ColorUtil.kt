package net.geekmc.turinglib.color

import net.geekmc.turinglib.TuringLib
import net.geekmc.turinglib.taml.Taml
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

fun String.toComponent(): Component {
    var str = this
    // 防止&&被替换
    str.replace("&&", "{§}")
    for ((k, v) in ColorUtil.colorMap) {
        str = str.replace(k, v)
    }
    str.replace("{§}", "&")
    return ColorUtil.miniMessage.deserialize(str)
}

object ColorUtil {

    val miniMessage = MiniMessage.miniMessage()

    // Custom key -> MiniMessage tag
    // &a -> <#111111> for example
    val colorMap = mutableMapOf<String, String>()

    fun enable() {

        val taml = Taml(TuringLib.INSTANCE.getResource("CustomColors.yml")!!)
        val colors: List<String> = taml["colors", listOf()]
        for (str in colors) {
            val split = str.split("@")
            colorMap[split[0]] = "<${split[1]}>"
        }

    }

}