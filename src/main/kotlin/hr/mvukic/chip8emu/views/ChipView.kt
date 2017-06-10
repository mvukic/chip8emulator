package hr.mvukic.chip8emu.views

import hr.mvukic.chip8emu.controllers.ChipController
import javafx.stage.FileChooser
import tornadofx.*
import tornadofx.FX.Companion.find
import tornadofx.find

/**
 * Created by matija on 10.06.17..
 */
class ChipView : View(title = "Chip 8 Emulator") {

    val chip : ChipController by inject()

    override val root = borderpane {
        setMinSize(800.0,600.0)
        top = menubar {
            menu("File") {
                item("Load ROM"){
                    shortcut("Ctrl+l"){
                        chip.loadRom()
                    }
                    action {
                        chooseFile(title="Load ROM file",filters = arrayOf()){
                            println("chosen")
                        }
                    }
                }
                separator()
                item("Quit"){
                    action {
                        println("Quitting.")
                    }
                }
            }
            menu("Action"){
                item("Start"){
                    action {
                        runAsync {
                            chip.run()
                        }
                    }
                }
                item("Halt"){
                    action {
                        chip.halt()
                    }
                }
            }
            menu("Disassemble") {
                item("To file...")
                item("To window"){
                    action{
                        runAsync {

                            chip.disassemble()
                        } ui{
                            find<DisassembleView>(mapOf("assembly" to it)).openModal()
                        }
                    }
                }
            }
        }

        center = canvas {

        }

        bottom = label("Status") {
            useMaxWidth = true

        }

    }
}