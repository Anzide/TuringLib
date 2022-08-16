package net.geekmc.turinglib

import net.geekmc.turinglib.color.ColorUtil
import net.geekmc.turinglib.util.saveResource
import net.minestom.server.extensions.Extension

class TuringLib : Extension() {

    companion object {
        lateinit var INSTANCE: TuringLib
            private set
    }

    override fun initialize() {
        INSTANCE = this

        saveResource("CustomColors.yml")

//        val yaml = Yaml()
//        val taml = yaml.loadAsTaml(getResource("CustomColors.yml")!!)
//        val importantStr: String = taml["2.3"]!!
//        val customColors: List<String> = taml["colors", listOf()]
//
//        println(importantStr)
//        println(customColors)

        ColorUtil.init()
        logger.info("TuringLib initialized.")
    }

    override fun terminate() {
        logger.info("TuringLib terminated.")
    }

}