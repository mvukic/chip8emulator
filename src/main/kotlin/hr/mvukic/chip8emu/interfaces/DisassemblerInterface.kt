package hr.mvukic.chip8emu.interfaces

import hr.mvukic.chip8emu.impl.Memory
import hr.mvukic.chip8emu.impl.Opcode
import java.io.File

/**
 * Created by matija on 10.06.17..
 */
interface DisassemblerInterface {

    fun disassemble(memory: Memory): List<Opcode>
}