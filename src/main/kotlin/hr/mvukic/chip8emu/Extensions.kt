package hr.mvukic.chip8emu


/*

 */
fun Int.toHex() = Integer.toHexString(this)!!

/*
    Returns hex representation of byte value eg.
    4 -> 4
    10 -> A
    15 -> F
 */
fun Byte.toHex() = Integer.toHexString(this.toInt())!!

//fun Byte.high() = (this.toInt() and 0xf0) shr 4
//
//fun Byte.low() = this.toInt() and 0xf
//

/*
    Get unsigned integer value of byte.
 */
fun Byte.toPositiveInt() = toInt() and 0xFF

fun address(msb: Byte, lsb: Byte) = ((msb.toInt() and 0xf) shl 8) or (lsb.toInt() and 0xff)

fun array2dOfBoolean(sizeOuter: Int, sizeInner: Int): Array<BooleanArray>
        = Array(sizeOuter) { BooleanArray(sizeInner) }