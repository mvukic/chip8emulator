package hr.mvukic.chip8emu

import hr.mvukic.chip8emu.views.ChipView
import javafx.application.Application
import javafx.stage.Stage
import tornadofx.*


class Chip : App() {
    override val primaryView = ChipView::class

    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }

    /**
     * Below method is static method on class Chip.
     * Used as starting point for run configuration
     */
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(Chip::class.java)
        }
    }
}

