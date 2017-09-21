package hr.mvukic.chip8emu.interfaces

/**
 * Created by matija on 08.06.17..
 */
interface IMemory {

    fun read(index: Int) : Byte

    fun write(index: Int, value: Byte)

}