package hr.mvukic.chip8emu.impl

import java.util.*

data class VmState(
        // Only thing required is memory reference
        var mem: Memory,
        var stack: IntArray = IntArray(16),
        var sp: Int = 0,
        var registers: ByteArray = ByteArray(16),
        var I: Int = 0,
        var delay: Int = 0,
        var sound: Int = 0,
        var keys: ByteArray = ByteArray(16),
        var pc: Int = 80
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VmState

        if (mem != other.mem) return false
        if (!Arrays.equals(stack, other.stack)) return false
        if (sp != other.sp) return false
        if (!Arrays.equals(registers, other.registers)) return false
        if (delay != other.delay) return false
        if (sound != other.sound) return false
        if (!Arrays.equals(keys, other.keys)) return false
        if (pc != other.pc) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mem.hashCode()
        result = 31 * result + Arrays.hashCode(stack)
        result = 31 * result + sp
        result = 31 * result + Arrays.hashCode(registers)
        result = 31 * result + delay
        result = 31 * result + sound
        result = 31 * result + Arrays.hashCode(keys)
        result = 31 * result + pc
        return result
    }
}