package net.geekmc.turinglib.color

import net.geekmc.turing.Turing
import net.geekmc.turinglib.TuringLib
import net.geekmc.turinglib.taml.Taml
import net.geekmc.turinglib.taml.loadAsTaml
import net.kyori.adventure.text.minimessage.MiniMessage
import org.yaml.snakeyaml.Yaml
import javax.swing.text.html.HTML.Tag.I

object ColorUtil {

    val deserializer = MiniMessage.miniMessage()

    fun enable() {

        val taml = Taml(TuringLib.INSTANCE.getResource("CustomColors.yml")!!)
        val colors: List<String> = taml["colors", listOf()]



    }

}