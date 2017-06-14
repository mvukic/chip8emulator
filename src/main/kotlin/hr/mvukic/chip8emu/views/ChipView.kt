package hr.mvukic.chip8emu.views

import hr.mvukic.chip8emu.controllers.ChipController
import hr.mvukic.chip8emu.enums.ChipStatus
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.input.KeyCombination
import tornadofx.*
import java.io.File

/**
 * Created by matija on 10.06.17..
 */
class ChipView : View(title = "Chip 8 Emulator") {

    val chip : ChipController by inject()
    var status = SimpleStringProperty("Init")

    override val root = borderpane {
        setMinSize(800.0,600.0)
        top = menubar {
            menu("File") {
                item("Load ROM") {
                    accelerator = KeyCombination.valueOf("ctrl+l")
                    action {
                        val files: List<File> = chooseFile(title = "Load ROM file", filters = chip.fileFilters())
                        if (files.size == 1) {
                            status.set("Loaded file: "+files.get(0).name)
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
                        status.set("Running...")
                        runAsync {
                            chip.run()
                        }
                    }
                }
                item("Halt"){
                    accelerator = KeyCombination.valueOf("ctrl+h")
                    action {
                        status.set("Stopped")
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
                                println(it)
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

        center = canvas {

        }

        bottom = label{
            textProperty().bindBidirectional(status)
        }

    }
}