package net.geekmc.turinglib.taml

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Path
import kotlin.io.path.absolutePathString

/**
 * Construct a Taml object from the yml file found with the path parameter.Use the Yaml parameter to load it.
 */
class Taml(val path: Path, val yaml: Yaml = defaultYaml) {

    companion object {

        private val defaultYaml: Yaml

        init {
            // 默认的保存风格
            val options = DumperOptions()
            options.defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
            defaultYaml = Yaml()
        }
    }

    private val rootMap: MutableMap<Any?, Any?> = yaml.load(FileReader(path.absolutePathString())) ?: LinkedHashMap()

    /**
     * Get a value from taml.
     * Example:if the yml file is like:
     * a:
     *   b: 1
     *   c: 2
     * Then Taml#get("a.b") or taml["a.b"] will return 1.
     */
    operator fun <T> get(keyString: String): T? {
        val keys = keyString.split(".")

        var obj: Map<Any?, Any?> = rootMap
        val iter = keys.iterator()
        while (iter.hasNext()) {

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

    /**
     * Get a value from taml.Return the default value instead of null when value not found or can't convert the value to generic type T.
     */
    operator fun <T> get(keyString: String, default: T): T {
        return get(keyString) ?: default
    }

    operator fun <T> set(keyString: String, value: T): Boolean {
        val keys = keyString.split(".")

        var obj: MutableMap<Any?, Any?> = rootMap
        val iter = keys.iterator()

        while (iter.hasNext()) {
            val key = iter.next()
            if (iter.hasNext()) {
                @Suppress("UNCHECKED_CAST")
                obj = obj[key] as? MutableMap<Any?, Any?> ?: let {
                    return false
                }
            } else {
                obj[key] = value

                return true
            }
        }
        return false
    }

    /**
     * Save the taml to the yml file.
     */
    fun save() {
        yaml.dump(rootMap, FileWriter(path.absolutePathString()))
    }

}