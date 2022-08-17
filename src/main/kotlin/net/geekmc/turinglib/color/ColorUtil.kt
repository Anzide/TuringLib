package net.geekmc.turinglib.color

import net.geekmc.turinglib.TuringLib
import net.geekmc.turinglib.taml.Taml
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.geekmc.turinglib.util.resolvePath
import net.minestom.server.entity.PlayerSkin
import org.yaml.snakeyaml.Yaml

fun String.toComponent(): Component {
    return ColorUtil.castStringToComponent(this)
}

object ColorUtil {

    val miniMessage = MiniMessage.miniMessage()

    // Custom key -> MiniMessage tag
    // &a -> <#111111> for example
    val colorMap = mutableMapOf<String, String>()

    data class Test(var x:Int=0,var y:Int=0)

    fun init() {

        val taml = Taml(TuringLib.INSTANCE.resolvePath("CustomColors.yml"))
        val colors: List<String> = taml["colors", listOf()]
        for (str in colors) {
            val split = str.split("@")
            colorMap[split[0]] = "<${split[1]}>"
        }

//        val skin = PlayerSkin.fromUsername("Anzide")
//        taml["a"] = skin
//        taml["b"] = Test(1, 2)
//        taml["c"] = Player(1, 2)


//        taml.save()
//        println(Yaml().dump(Test(1, 2)))

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