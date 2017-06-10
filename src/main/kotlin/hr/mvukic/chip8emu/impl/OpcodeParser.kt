package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.address
import hr.mvukic.chip8emu.toHex
import hr.mvukic.chip8emu.toPositiveInt
import kotlin.experimental.*

/**
 * Created by matija on 10.06.17..
 */

class OpcodeParser{

    fun parse(hi:Byte,lo:Byte): String{
        var higher = hi.toPositiveInt().toHex().toUpperCase()
        var lower = lo.toPositiveInt().toHex().toUpperCase()
        if (higher.length == 1) higher = "0"+higher
        if (lower.length == 1) lower = "0"+lower

        val address = address(hi,lo)
        when(hi.toPositiveInt() and 0xf){
            0x00 -> when(lo.toPositiveInt()){
                0xe0 -> return "$higher$lower ${hi.toPositiveInt()} ${lo.toPositiveInt()} CLS"
                0xee -> return "$higher$lower ${hi.toPositiveInt()} ${lo.toPositiveInt()} RET"
                0x00 -> return "$higher$lower ${hi.toPositiveInt()} ${lo.toPositiveInt()}"
                else -> return "$higher$lower ${hi.toPositiveInt()} ${lo.toPositiveInt()}"
            }
            0x01 -> {
                val target = address and 0x0fff
                return "$higher$lower ${hi.toPositiveInt()} ${lo.toPositiveInt()} JMP ${target.toHex()}"
            }
            else -> return "$higher$lower ${hi.toPositiveInt()} ${lo.toPositiveInt()}"
        }
    }
}