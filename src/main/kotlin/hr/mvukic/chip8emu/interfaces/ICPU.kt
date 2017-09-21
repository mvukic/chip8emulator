package hr.mvukic.chip8emu.interfaces

import hr.mvukic.chip8emu.impl.Opcode

/**
 * Created by matija on 08.06.17..
 */
interface ICPU {

    fun start()
    fun halt()

    fun cycle()

    fun disassemble(): List<Opcode>
}