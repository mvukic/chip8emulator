package hr.mvukic.chip8emu

import hr.mvukic.chip8emu.controllers.ChipController
import hr.mvukic.chip8emu.views.ChipView
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.stage.Stage
import tornadofx.*


class Chip : App() {
    override val primaryView = ChipView::class

    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }
}