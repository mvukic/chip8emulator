package hr.mvukic.chip8emu.controllers

import hr.mvukic.chip8emu.impl.Cpu
import hr.mvukic.chip8emu.enums.ChipStatus
import hr.mvukic.chip8emu.impl.Memory
import javafx.beans.property.SimpleStringProperty
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

/**
 * Created by matija on 09.06.17..
 */

class ChipController : Controller(){

    var memory: Memory = Memory()
    var cpu: Cpu = Cpu(memory)
    var status: ChipStatus = ChipStatus.INIT
    var statusText = SimpleStringProperty("Init")

    val keys:Map<String,String> = mapOf(
            "a" to "b",
            "b" to "c",
            "c" to "d",
            "stop" to "ctrl+h",
            "run" to "ctrl+r")

    fun loadRom(file: File){
        status = ChipStatus.WAITING
        // auto closable
        with(file){
            memory.loadROMbytes(readBytes())
        }
        statusText.set("Loaded file: "+file.name)
    }

    fun halt(){
        cpu.halt()
        status = ChipStatus.STOPPED
        statusText.set("Stopped")
    }

    fun run(){
        cpu.start()
        status = ChipStatus.RUNNING
        statusText.set("RUNNING")
    }

    fun disassemble() =  cpu.disassemble()

    fun fileFilters() = arrayOf(
            FileChooser.ExtensionFilter("*.rom","*.rom"),
            FileChooser.ExtensionFilter("*.chip","*.chip"),
            FileChooser.ExtensionFilter("*.chip8","*.chip8"),
            FileChooser.ExtensionFilter("*.*","*.*")
    )


}