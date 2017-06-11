package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.address
import hr.mvukic.chip8emu.toHex
import hr.mvukic.chip8emu.toPositiveInt
import kotlin.experimental.and

/**
 * Created by matija on 10.06.17..
 */

class OpcodeParser{

    fun parse(index:Int,hi:Byte,lo:Byte): Opcode{
        var higher = hi.toPositiveInt().toHex().toUpperCase()
        var lower = lo.toPositiveInt().toHex().toUpperCase()
        if (higher.length == 1) higher = "0"+higher
        if (lower.length == 1) lower = "0"+lower
        val hexCode = "$higher$lower"
        val i = index-80
        val address = address(hi,lo)

        when(hi.toPositiveInt() and 0xf0){
            0x00 -> when(lo.toPositiveInt()){
                0xEE -> return Opcode(0,"$address","$higher$lower","RET")
                0xE0 -> return Opcode(0,"$address","$higher$lower","CLS")
                else -> return Opcode(0,"$address","$higher$lower","EXEC ${address.toHex()} OR DATA")
            }
            0x10 -> {
                val adr = (address and 0x0fff).toHex()
                return Opcode(0,"$address","$higher$lower","JMP $adr")
            }
            0x20 -> {
                val adr = (address and 0x0fff).toHex()
                return Opcode(0,"$address","$higher$lower","CALL $adr")
            }
            0x30 -> {
                val value = lo.toPositiveInt().toHex()
                val reg = (hi and 0x0f).toHex()
                return Opcode(0,"$address","$higher$lower","seq V$reg #$value")
            }
            0x40 -> {
                val value = lo.toPositiveInt().toHex()
                val reg = (hi and 0x0f).toHex()
                return Opcode(0,"$address","$higher$lower","sne V$reg #$value")
            }
            0x50 -> {
                val reg1 = (hi and 0x0f).toHex()
                val reg2 = (lo.toPositiveInt() shr 8).toHex()
                return Opcode(0,"$address","$higher$lower","seq V$reg1 V$reg2")
            }
            0x60 -> return Opcode(0,"$address","$higher$lower","with 6")
            0x70 -> return Opcode(0,"$address","$higher$lower","with 7")
            0x80 -> return Opcode(0,"$address","$higher$lower","with 8")
            0x90 -> return Opcode(0,"$address","$higher$lower","with 9")
            0xA0 -> return Opcode(0,"$address","$higher$lower","with A")
            0xB0 -> return Opcode(0,"$address","$higher$lower","with B")
            0xC0 -> return Opcode(0,"$address","$higher$lower","with C")
            0xD0 -> return Opcode(0,"$address","$higher$lower","with D")
            0xE0 -> return Opcode(0,"$address","$higher$lower","with E")
            0xF0 -> return Opcode(0,"$address","$higher$lower","with F")
            else -> return unknown()
        }
    }

    fun unknown():Opcode{
        return Opcode(0,"error","error","error")
    }
}