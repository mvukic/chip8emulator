package hr.mvukic.chip8emu.controllers

import hr.mvukic.chip8emu.impl.Cpu
import hr.mvukic.chip8emu.enums.ChipStatus
import hr.mvukic.chip8emu.impl.Disassembler
import hr.mvukic.chip8emu.impl.Memory
import hr.mvukic.chip8emu.impl.Opcode
import javafx.scene.input.KeyEvent
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

/**
 * Created by matija on 09.06.17..
 */

class ChipController : Controller(){

    var memory: Memory = Memory()
    var cpu: Cpu = Cpu()
    var disassembler :Disassembler = Disassembler()
    var status: ChipStatus = ChipStatus.INIT
    val keys:Map<String,String> = mapOf(
            "a" to "b",
            "b" to "c",
            "c" to "d",
            "stop" to "ctrl+h",
            "run" to "ctrl+r")

    fun loadRom(file: File){
        status = ChipStatus.WAITING
        memory.loadROMbytes(file.readBytes())
        cpu.memory = memory
    }

    fun halt(){
        status = ChipStatus.STOPPED
    }

    fun run(){
        status = ChipStatus.RUNNING
    }

    fun disassemble(): List<Opcode>{
        return disassembler.disassemble(memory)
    }

    fun fileFilters() = arrayOf(
            FileChooser.ExtensionFilter("*.rom","*.rom"),
            FileChooser.ExtensionFilter("*.chip","*.chip"),
            FileChooser.ExtensionFilter("*.chip8","*.chip8"),
            FileChooser.ExtensionFilter("*.*","*.*")
    )


}