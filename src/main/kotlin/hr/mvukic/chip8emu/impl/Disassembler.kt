package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.interfaces.DisassemblerInterface
import java.io.File

/**
 * Created by matija on 10.06.17..
 */

class Disassembler: DisassemblerInterface{

    val opcodeParser:OpcodeParser = OpcodeParser()

    override fun disassemble(memory: Memory): String {
        val sb = StringBuilder()

        for(i in 80..memory.rom.size-1 step 2){
            sb.append(opcodeParser.parse(memory.rom.get(i),memory.rom.get(i+1)))
            sb.append("\n")
        }
        return sb.toString()
    }

}