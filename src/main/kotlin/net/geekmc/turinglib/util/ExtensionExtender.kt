package net.geekmc.turinglib.util

import net.minestom.server.extensions.Extension
import kotlin.io.path.exists

/**
 * Save the resource to the given path.Will NOT replace the existing file.
 */
fun Extension.saveResource(resource: String) {
    val targetPath = dataDirectory.resolve(resource)
    if (targetPath.toFile().exists()) {
        return
    }
    savePackagedResource(resource)
}