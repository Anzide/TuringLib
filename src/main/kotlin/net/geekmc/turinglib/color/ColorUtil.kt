package net.geekmc.turinglib.color

import net.geekmc.turing.Turing
import net.geekmc.turinglib.TuringLib
import net.geekmc.turinglib.taml.Taml
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.geekmc.turinglib.util.GlobalEvent
import net.geekmc.turinglib.util.resolvePath
import kotlin.io.path.Path

fun String.toComponent(): Component {
    return ColorUtil.castStringToComponent(this)
}

object ColorUtil {

    val miniMessage = MiniMessage.miniMessage()

    // Custom key -> MiniMessage tag
    // &a -> <#111111> for example
    val colorMap = mutableMapOf<String, String>()

    fun init() {

        val taml = Taml(TuringLib.INSTANCE.resolvePath("CustomColors.yml"))
        val colors: List<String> = taml["colors", listOf()]
        for (str in colors) {
            val split = str.split("@")
            colorMap[split[0]] = "<${split[1]}>"
        }

        taml["test"]=123
        taml.save()

    }

    fun castStringToComponent(string: String): Component {
        // 防止&&被替换
        var str = string
        str = str.replace("&&", "{§}")
        for ((k, v) in ColorUtil.colorMap) {
            str = str.replace(k, v)
        }
        str = str.replace("{§}", "&")
        return miniMessage.deserialize(str)
    }

}