package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.interfaces.DisassemblerInterface
import java.io.File

/**
 * Created by matija on 10.06.17..
 */

class Disassembler: DisassemblerInterface{

    val opcodeParser:OpcodeParser = OpcodeParser()
    var operations: MutableList<Opcode> = mutableListOf()

    override fun disassemble(memory: Memory): String {
        val sb = StringBuilder()

        for(i in 80..memory.rom.size-1 step 2){
            val op = opcodeParser.parse(i,memory.rom.get(i),memory.rom.get(i+1))
            operations.add(op)
            sb.append(op.toString())
            sb.append("\n")
        }
        return sb.toString()
    }

}