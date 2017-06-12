package hr.mvukic.chip8emu

import hr.mvukic.chip8emu.views.ChipView
import javafx.stage.Stage
import tornadofx.*


class Chip : App() {
    override val primaryView = ChipView::class

    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }
}