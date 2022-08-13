package net.geekmc.turinglib.taml

import org.yaml.snakeyaml.Yaml
import java.io.InputStream


fun Yaml.loadAsTaml(io: InputStream): Taml {
    return Taml(io, this)
}

/**
 * 两种方式创建Taml对象：
 * Yaml.loadAsTaml，会使用提供的Yaml的格式来创建
 * Taml()，会使用默认的格式来创建
 */
class Taml {

    companion object {

        private val defaultYaml: Yaml

        init {
//            val options = DumperOptions()
//            options.defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
            defaultYaml = Yaml()
        }
    }

    private val rootMap: Map<Any?, Any?>

    constructor(io: InputStream, yaml: Yaml = defaultYaml) {
        rootMap = yaml.load(io)
    }

    constructor(str: String, yaml: Yaml = defaultYaml) {
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
                @Suppress("UNCHECKED_CAST")
                return obj[key] as? T ?: let { return null }
            } else {
                @Suppress("UNCHECKED_CAST")
                obj = obj[key] as? Map<Any?, Any?> ?: let { return null }
            }

        }
        return null
    }

    operator fun <T> get(keyString: String, default: T): T {
        return get(keyString) ?: default
    }

}