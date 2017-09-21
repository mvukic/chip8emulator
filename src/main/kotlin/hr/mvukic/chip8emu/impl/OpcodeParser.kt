package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.address
import hr.mvukic.chip8emu.toHex
import unsigned.toUbyte
import kotlin.experimental.and

class OpcodeParser{

//    fun parse(indexInMemory:Int, hi:Byte, lo:Byte): Opcode{
//        var higher = hi.toPositiveInt().toHex().toUpperCase()
//        var lower = lo.toPositiveInt().toHex().toUpperCase()
//
//        higher = if (higher.length == 1) "0$higher" else higher
//        lower = if (lower.length == 1) "0$lower" else lower
//
//        val address = address(hi,lo)
//        val index = indexInMemory-80
//        when(hi.toPositiveInt() and 0xf0){
//            0x00 -> when(lo.toPositiveInt()){
//                0xE0 -> return Opcode(index,"$address","$higher$lower","cls")
//                0xEE -> return Opcode(index,"$address","$higher$lower","ret")
//                else -> return Opcode(index,"$address","$higher$lower","DATA 0x$higher$lower")
//            }
//            0x10 -> {
//                val adr = (address and 0x0fff).toHex()
//                return Opcode(index,"$address","$higher$lower","jmp #$adr")
//            }
//            0x20 -> {
//                val adr = (address and 0x0fff).toHex()
//                return Opcode(index,"$address","$higher$lower","call $adr")
//            }
//            0x30 -> {
//                val value = lo.toPositiveInt().toHex()
//                val reg = (hi and 0x0f).toHex()
//                return Opcode(index,"$address","$higher$lower","seq r$reg #$value")
//            }
//            0x40 -> {
//                val value = lo.toPositiveInt().toHex()
//                val reg = (hi and 0x0f).toHex()
//                return Opcode(index,"$address","$higher$lower","sne r$reg #$value")
//            }
//            0x50 -> {
//                val reg1 = (hi and 0x0f).toHex()
//                val reg2 = (lo.toPositiveInt() shr 8).toHex()
//                return Opcode(index,"$address","$higher$lower","seq r$reg1 V$reg2")
//            }
//            0x60 -> {
//                val value = lo.toPositiveInt().toHex()
//                val reg = (hi and 0x0f).toHex()
//                return Opcode(index,"$address","$higher$lower","mov #$value, r$reg")
//            }
//            0x70 -> {
//                val value = lo.toPositiveInt().toHex()
//                val reg = (hi and 0x0f).toHex()
//                return Opcode(index,"$address","$higher$lower","add #$value, r$reg")
//            }
//            0x80 -> {
//                val x = (hi.toPositiveInt() and 0x0f).toHex()
//                val y = (lo.toPositiveInt() and 0xf0).toHex()
//                when(lo.toPositiveInt() and 0x0f){
//                    0x00 ->{
//                        return Opcode(index,"$address","$higher$lower","mov r$y, r$x")
//                    }
//                    0x01 -> {
//                        return Opcode(index,"$address","$higher$lower","or r$x, r$y")
//                    }
//                    0x02 -> {
//                        return Opcode(index,"$address","$higher$lower","and r$x, r$y")
//                    }
//                    0x03 -> {
//                        return Opcode(index,"$address","$higher$lower","xor r$x, r$y")
//                    }
//                    0x04 -> {
//                        return Opcode(index,"$address","$higher$lower","addc r$x, r$y")
//                    }
//                    0x05 -> {
//                        return Opcode(index,"$address","$higher$lower","subc r$x, r$y")
//                    }
//                    0x06 -> {
//                        return Opcode(index,"$address","$higher$lower","shr_store r$x, r$y")
//                    }
//                    0x07 -> {
//                        return Opcode(index,"$address","$higher$lower","subc_store r$x, r$y")
//                    }
//                    0x0E -> {
//                        return Opcode(index,"$address","$higher$lower","shl_store r$x, r$y")
//                    }
//                    else -> {
//                        println("0x80")
//                        return unknown()
//                    }
//                }
//            }
//            0x90 -> {
//                val reg1 = (hi and 0x0f).toHex()
//                val reg2 = ((lo.toPositiveInt() shl 4) and 0x0f)
//                return Opcode(index,"$address","$higher$lower","seq r$reg1,r$reg2")
//            }
//            0xA0 -> {
//                val adr = (address and 0x0fff).toHex()
//                return Opcode(index,"$address","$higher$lower","mov I, #$adr")
//            }
//            0xB0 -> {
//                val adr = (address and 0x0fff).toHex()
//                return Opcode(index,"$address","$higher$lower","jmp #($adr+[r0])")
//            }
//            0xC0 -> {
//                val reg = (hi and 0x0f).toHex()
//                val rng = lo.toPositiveInt().toHex()
//                return Opcode(index,"$address","$higher$lower","rnd r$reg, #$rng")
//            }
//            0xD0 -> {
//                val reg1 = (hi and 0x0f).toHex()
//                val reg2 = ((lo.toPositiveInt() shl 4) and 0x0f)
//                val value = (lo and 0x0f).toHex()
//                return Opcode(index,"$address","$higher$lower","draw r$reg1,r$reg2,#$value")
//            }
//            0xE0 -> {
//                val reg = (hi and 0x0f).toHex()
//                when(lo.toPositiveInt() and 0xff){
//                    0x9E -> return Opcode(index,"$address","$higher$lower","spre r$reg")
//                    0xA1 -> return Opcode(index,"$address","$higher$lower","snpre r$reg")
//                    else -> {
//                        println("0xE0")
//                        return unknown()
//                    }
//                }
//            }
//            0xF0 -> {
//                val x = (hi.toPositiveInt() and 0x0f).toHex()
//                when(lo.toPositiveInt() and 0xff){
//                    0xA1 -> {
//                        return Opcode(index,"$address","$higher$lower","skip_key_pressed r$x")
//                    }
//                    0x07 -> {
//                        return Opcode(index,"$address","$higher$lower","mov delay_timer, r$x")
//                    }
//                    0x0A -> {
//                        return Opcode(index,"$address","$higher$lower","wait_key_press r$x")
//                    }
//                    0x15 -> {
//                        return Opcode(index,"$address","$higher$lower","mov r$x,delay_timer")
//                    }
//                    0x18 -> {
//                        return Opcode(index,"$address","$higher$lower","mov r$x,sound_timer")
//                    }
//                    0x1E -> {
//                        return Opcode(index,"$address","$higher$lower","adds I,r$x")
//                    }
//                    0x29 -> {
//                        return Opcode(index,"$address","$higher$lower","mov I,address of sprite value in r$x")
//                    }
//                    0x33 -> {
//                        return Opcode(index,"$address","$higher$lower","mov_bdencoded I123,r$x")
//                    }
//                    0x55 -> {
//                        return Opcode(index,"$address","$higher$lower","mov I+,V+")
//                    }
//                    0x65 -> {
//                        return Opcode(index,"$address","$higher$lower","mov V+,I+")
//                    }
//                    else -> {
//                        println("0xF0")
//                        return unknown()
//                    }
//                }
//            }
//            else -> return unknown()
//        }
//    }

    fun unknown():Opcode{
        return Opcode(0,"error","error","error")
    }
}