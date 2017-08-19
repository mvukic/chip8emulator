package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.interfaces.DisassemblerInterface
import java.io.File

/**
 * Created by matija on 10.06.17..
 */

class Disassembler: DisassemblerInterface{

    private val opcodeParser:OpcodeParser = OpcodeParser()
    private var operations: MutableList<Opcode> = mutableListOf()

    override fun disassemble(memory: Memory): List<Opcode> {
        operations.clear()

        (80 until memory.rom.size step 2)
                .map { opcodeParser.parse(it,memory.rom.get(it),memory.rom.get(it + 1)) }
                .forEach { operations.add(it) }

        return operations
    }

}