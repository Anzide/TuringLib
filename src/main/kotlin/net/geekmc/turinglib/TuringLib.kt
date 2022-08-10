package net.geekmc.turinglib

import net.minestom.server.extensions.Extension

class TuringLib : Extension() {

    override fun initialize() {
        logger.info("TuringLib initialized.")
    }

    override fun terminate() {
        logger.info("TuringLib terminated.")
    }

}