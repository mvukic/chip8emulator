package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.interfaces.IMemory

/**
 * Created by matija on 08.06.17..
 */
class Memory : IMemory{

    private var memory: ByteArray = ByteArray(1024 * 4) // 4kb of memory
    private var rom: ByteArray = ByteArray(80) // only loaded rom data

    // Initial fonts size + size of loaded ROM
    var size = 0

    private val fontset: Array<Int> = arrayOf(
        0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
        0x20, 0x60, 0x20, 0x20, 0x70, // 1
        0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
        0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
        0x90, 0x90, 0xF0, 0x10, 0x10, // 4
        0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
        0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
        0xF0, 0x10, 0x20, 0x40, 0x40, // 7
        0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
        0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
        0xF0, 0x90, 0xF0, 0x90, 0x90, // A
        0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
        0xF0, 0x80, 0x80, 0x80, 0xF0, // C
        0xE0, 0x90, 0x90, 0x90, 0xE0, // D
        0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
        0xF0, 0x80, 0xF0, 0x80, 0x80  // F
    )

    init{
        reset()
    }

    override fun read(index: Int) = memory[index]

    override fun write(index: Int, value: Byte) = memory.set(index,value)

    fun reset(){
        size = 0
        for(i in 0 until memory.size){
            memory[i] = 0
        }
        initFonts()
    }

    private fun initFonts(){
        for(i in 0 until this.fontset.size){
            write(i, fontset[i].toByte())
        }
        this.size += this.fontset.size
        println("Memory size: $size")
    }

    fun loadROMbytes(romBytes: ByteArray){
        reset()
        rom = romBytes
        for(i in 0 until romBytes.size){
            write(i+80, romBytes[i])
        }
        this.size += romBytes.size
        println("Memory size: $size")
    }


}