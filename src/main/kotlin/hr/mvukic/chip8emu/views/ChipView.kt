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
    var status = SimpleStringProperty("Init")
    var screen: GridPane = GridPane()
    lateinit var timer : TimerTask

    init {
        // Initialize screen segments
        (0 until 64).forEach { i ->
            (0 until 32).forEach{ j ->
                val node = Label("1")
                node.style { backgroundColor += c("white") }
                screen.add(node,i,j)
            }
        }

    }

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

//                        runAsync {
//                            // keyboard input thread
//                        }
                        runAsync {
                            // Thread updates screen
                            timer = Timer().schedule(0,1000){
                                (0 until 64).forEach { i ->
                                    (0 until 32).forEach{ j ->
                                        val node = Label("1")
                                        if(chip.screen.get(j).get(i)){
                                            node.style { backgroundColor += c("black") }
                                        }else{
                                            node.style { backgroundColor += c("white") }
                                        }
                                        Platform.runLater { screen.add(node,i,j) }
                                    }
                                }
                            }
                        }
                        runAsync {
                            // Thread fetches, decodes and executes instructions
                            chip.run()
                        } ui {
                            timer.cancel() // stop updating screen
                            status.set("Ended")
                            alert(
                                    type = Alert.AlertType.INFORMATION,
                                    header = "Message",
                                    content = "Program ended."
                            )
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

        center = screen

        bottom = label{
            textProperty().bindBidirectional(status)
        }

    }
}