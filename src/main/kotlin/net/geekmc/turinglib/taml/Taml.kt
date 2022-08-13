package net.geekmc.turinglib.taml

import org.yaml.snakeyaml.Yaml
import java.io.InputStream

fun Yaml.loadAsTaml(yaml: InputStream): Taml {
    return Taml(yaml)
}

// 包装类，里面包了一个Map，
// 可以使用["a.b.c"]提取值
class Taml {

    companion object {

        private val yaml: Yaml

        init {
//            val options = DumperOptions()
//            options.defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
            yaml = Yaml()
        }
    }

    private val rootMap: Map<Any?, Any?>

    constructor(io: InputStream) {
        rootMap = yaml.load(io)
    }

    constructor(str: String) {
        rootMap = yaml.load(str)
    }

    operator fun <T> get(keyString: String): T? {
        val keys = keyString.split(".")

        var obj: Map<Any?, Any?> = rootMap
        val iter = keys.iterator()
        while (true) {
            if (!iter.hasNext()) break
            var key: Any? = iter.next()

            if (obj[key] == null) {
                // 转换失败，返回null
                try {
                    key = key.toString().toInt()
                } catch (e: java.lang.NumberFormatException) {
                    return null
                }
                // String转成Integer作为key还是找不到，返回null
                if (obj[key] == null) {
                    return null
                }
            }
            if (!iter.hasNext()) {
                return obj[key] as? T ?: let { return null }
            } else {
                obj = obj[key] as? Map<Any?, Any?> ?: let { return null }
            }

        }
        return null
    }

}