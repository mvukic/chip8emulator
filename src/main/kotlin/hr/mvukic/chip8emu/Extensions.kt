package hr.mvukic.chip8emu

/**
 * Created by matija on 10.06.17..
 */

fun Int.toHex() = Integer.toHexString(this)

fun Byte.toHex() = Integer.toHexString(this.toInt())

fun Byte.high() = (this.toInt() and 0xf0) shr 4

fun Byte.low() = this.toInt() and 0xf

fun Byte.toPositiveInt() = toInt() and 0xFF

fun address(msb: Byte, lsb: Byte) = ((msb.toInt() and 0xf) shl 8) or (lsb.toInt() and 0xff)