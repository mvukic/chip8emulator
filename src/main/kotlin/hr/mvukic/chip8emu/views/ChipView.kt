package hr.mvukic.chip8emu.views

import hr.mvukic.chip8emu.controllers.ChipController
import hr.mvukic.chip8emu.enums.ChipStatus
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Border
import javafx.scene.layout.GridPane
import tornadofx.*
import java.awt.Color
import java.io.File
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.concurrent.schedule

/**
 * Created by matija on 10.06.17..
 */
class ChipView : View(title = "Chip 8 Emulator") {

    val chip : ChipController by inject()

    override val root = borderpane {
        setMinSize(800.0,600.0)
        top = menubar {
            menu("File") {
                item("Load ROM") {
                    accelerator = KeyCombination.valueOf("ctrl+l")
                    action {
                        val files: List<File> = chooseFile(title = "Load ROM file", filters = chip.fileFilters())
                        if (files.size == 1) {
                            chip.loadRom(files.get(0))
                        }
                    }
                }
                separator()
                item("Quit"){
                    accelerator = KeyCombination.valueOf("ctrl+q")
                    action {
                        println("Quitting.")
                    }
                }
            }
            menu("Action"){
                item("Start"){
                    accelerator = KeyCombination.valueOf("ctrl+r")
                    action {
                        runAsync {
                            chip.run()
                        }
                    }
                }
                item("Halt"){
                    accelerator = KeyCombination.valueOf("ctrl+h")
                    action {
                        chip.halt()
                    }
                }
            }
            menu("Disassemble") {
                item("To window"){
                    action{
                        if(chip.status != ChipStatus.INIT){
                            runAsync {
                                chip.disassemble()
                            } ui{
                                find<DisassembleView>(mapOf("opcodes" to it)).openModal()
                            }
                        }else{
                            alert(Alert.AlertType.WARNING,"No ROM-s loaded","Please load a ROM file first!", ButtonType.OK)
                        }
                    }
                }

            }
            menu("Help"){
                item("Keys"){
                    action{
                        find<KeyBindingsView>(mapOf("keys" to chip.keys)).openModal()
                    }
                }
                item("About"){
                    action{
                        find(AboutView::class).openModal()
                    }
                }
            }
        }

        bottom = label{
            textProperty().bindBidirectional(chip.statusText)
        }

    }
}