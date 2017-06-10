package hr.mvukic.chip8emu.controllers

import hr.mvukic.chip8emu.cpu.Cpu
import hr.mvukic.chip8emu.memory.Memory
import javafx.stage.FileChooser
import tornadofx.*

/**
 * Created by matija on 09.06.17..
 */

class ChipController (
        var memory:Memory = Memory(),
        var cpu: Cpu = Cpu()

): Controller(){

    fun loadRom(){

    }

    fun halt(){

    }

    fun run(){

    }

    fun disassemble(): String{
        return "Ovo je test."
    }

    fun fileFilters() = arrayOf(
            FileChooser.ExtensionFilter("*.rom","*.rom"),
            FileChooser.ExtensionFilter("*.chip","*.chip"),
            FileChooser.ExtensionFilter("*.chip8","*.chip8"),
            FileChooser.ExtensionFilter("*.*","*.*")
    )


}