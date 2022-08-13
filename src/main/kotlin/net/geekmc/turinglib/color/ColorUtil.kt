package net.geekmc.turinglib.color

import net.geekmc.turing.Turing
import net.geekmc.turinglib.TuringLib
import org.yaml.snakeyaml.Yaml

object ColorUtil {

    fun enable() {
        var yaml=Yaml()
        val colorConfig:Map<String?,Any?> = yaml.load(TuringLib.getResource("color.yml"))
        var mp= mapOf<Int,Int>()
        var c=mp[1]

    }

}