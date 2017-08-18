package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.address
import hr.mvukic.chip8emu.array2dOfBoolean
import hr.mvukic.chip8emu.toHex
import hr.mvukic.chip8emu.toPositiveInt
import java.util.concurrent.ThreadLocalRandom
import kotlin.experimental.and

/**
 * Created by matija on 08.06.17..
 */
class Cpu {

    var pc = 80 // program counter skips memory with fonts
    lateinit var memory:Memory
    lateinit var screen: Array<BooleanArray>
    var stack = IntArray(16)
    var running = true

    fun run(){
        while(running){
            val row = ThreadLocalRandom.current().nextInt(0,32)
            val col = ThreadLocalRandom.current().nextInt(0,64)
            println("$row $col")
            screen.get(row).set(col,true)
            Thread.sleep(2000)
        }

//        while(pc < memory.size){
//            val msb = memory.rom.get(pc)
//            val lsb = memory.rom.get(pc+1)
//
//            val address = address(msb,lsb)
//
//            when(msb.toPositiveInt() and 0xf0){
//                0x00 -> when(lsb.toPositiveInt()){
//                    0xEE -> {
//                        screen.forEach {
//                            it.fill(false)
//                        }
//                    }
//                    0xE0 -> {}
//                    else -> {}
//                }
//                0x10 -> {
//                    val adr = (address and 0x0fff).toHex()
//
//                }
//                0x20 -> {
//                    val adr = (address and 0x0fff).toHex()
//
//                }
//                0x30 -> {
//                    val value = lsb.toPositiveInt().toHex()
//                    val reg = (msb and 0x0f).toHex()
//
//                }
//                0x40 -> {
//                    val value = lsb.toPositiveInt().toHex()
//                    val reg = (msb and 0x0f).toHex()
//
//                }
//                0x50 -> {
//                    val reg1 = (msb and 0x0f).toHex()
//                    val reg2 = (lsb.toPositiveInt() shr 8).toHex()
//
//                }
//                0x60 -> {
//                    val value = lsb.toPositiveInt().toHex()
//                    val reg = (msb and 0x0f).toHex()
//
//                }
//                0x70 -> {
//                    val value = lsb.toPositiveInt().toHex()
//                    val reg = (msb and 0x0f).toHex()
//
//                }
//                0x80 -> {
//                    val x = (msb.toPositiveInt() and 0x0f).toHex()
//                    val y = (lsb.toPositiveInt() and 0xf0).toHex()
//                    when(lsb.toPositiveInt() and 0x0f){
//                        0x00 ->{
//
//                        }
//                        0x01 -> {
//
//                        }
//                        0x02 -> {
//
//                        }
//                        0x03 -> {
//
//                        }
//                        0x04 -> {
//
//                        }
//                        0x05 -> {
//
//                        }
//                        0x06 -> {
//
//                        }
//                        0x07 -> {
//
//                        }
//                        0x0E -> {
//
//                        }
//                        else -> {
//
//                        }
//                    }
//                }
//                0x90 -> {
//                    val reg1 = (msb and 0x0f).toHex()
//                    val reg2 = ((lsb.toPositiveInt() shl 4) and 0x0f)
//
//                }
//                0xA0 -> {
//                    val adr = (address and 0x0fff).toHex()
//
//                }
//                0xB0 -> {
//                    val adr = (address and 0x0fff).toHex()
//
//                }
//                0xC0 -> {
//                    val reg = (msb and 0x0f).toHex()
//                    val rng = lsb.toPositiveInt().toHex()
//
//                }
//                0xD0 -> {
//                    val reg1 = (msb and 0x0f).toHex()
//                    val reg2 = ((lsb.toPositiveInt() shl 4) and 0x0f)
//                    val value = (lsb and 0x0f).toHex()
//
//                }
//                0xE0 -> {
//                    val reg = (msb and 0x0f).toHex()
//                    when(lsb.toPositiveInt() and 0xff){
//                        0x9E -> {}
//                        0xA1 -> {}
//                        else -> {
//
//                        }
//                    }
//                }
//                0xF0 -> {
//                    val x = (msb.toPositiveInt() and 0x0f).toHex()
//                    when(lsb.toPositiveInt() and 0xff){
//                        0xA1 -> {
//
//                        }
//                        0x07 -> {
//
//                        }
//                        0x0A -> {
//
//                        }
//                        0x15 -> {
//
//                        }
//                        0x18 -> {
//
//                        }
//                        0x1E -> {
//
//                        }
//                        0x29 -> {
//
//                        }
//                        0x33 -> {
//
//                        }
//                        0x55 -> {
//
//                        }
//                        0x65 -> {
//
//                        }
//                        else -> {
//
//                        }
//                    }
//                }
//                else -> {}
//            }
//
//        }
    }
}