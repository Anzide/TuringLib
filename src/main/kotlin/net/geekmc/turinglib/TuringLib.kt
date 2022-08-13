package net.geekmc.turinglib

import net.geekmc.turinglib.color.ColorUtil
import net.geekmc.turinglib.taml.Taml
import net.geekmc.turinglib.taml.loadAsTaml
import net.geekmc.turinglib.util.saveResource
import net.minestom.server.extensions.Extension
import org.yaml.snakeyaml.Yaml

object TuringLib : Extension() {

    override fun initialize() {

        saveResource("CustomColors.yml")
        val yaml = Yaml()

        var taml = yaml.loadAsTaml(getResource("CustomColors.yml")!!)
        val x: Int? = taml["1"]
        println(x)
        println(taml["2.3"])
        println(taml["test"])
        println(taml["colors"])


        ColorUtil.enable()
        logger.info("TuringLib initialized.")
    }

    override fun terminate() {
        logger.info("TuringLib terminated.")
    }

}