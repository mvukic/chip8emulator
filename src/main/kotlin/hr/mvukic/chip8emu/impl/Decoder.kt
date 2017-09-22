package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.interfaces.IDecoder
import hr.mvukic.chip8emu.toPositiveInt

class Decoder(private val state: VmState) : IDecoder {


    override fun before(opCode: Int, address: Int) { }

    override fun after() { }

    override fun unknown(opCode: Int, address: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("clear screen") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ret() {
        TODO("return from call") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jmp(address: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun call(address: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jeq(reg: Int, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jneq(reg: Int, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jeqr(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 6XNN
     *
     * Store number NN ([value]) in register VX (V[reg])
     */
    override fun set(reg: Int, value: Int) {
        state.registers[reg] = value.toByte()
    }

    /**
     * 7XNN
     *
     * Add the NN ([value])  to register VX (V[reg])
     */
    override fun add(reg: Int, value: Int) {
        state.registers[reg] = (state.registers[reg].toPositiveInt() + value).toByte()
    }

    override fun setr(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun or(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun and(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun xor(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addr(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sub(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shr(reg1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subb(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shl(reg1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jneqr(reg1: Int, reg2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * ANNN
     *
     * Store memory address NNN in register I
     */
    override fun seti(value: Int) {
        state.I = value
    }

    /**
     * BNNN
     *
     * Jump to address NNN + V0
     */
    override fun jmpv0(address: Int) {
        state.pc = address
    }

    override fun rand(reg: Int, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun draw(reg1: Int, reg2: Int, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jkey(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun jnkey(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * FX07
     *
     * Store the current value of the delay timer in register V[reg]
     */
    override fun getdelay(reg: Int) {
        state.registers[reg] = state.delay.toByte()
    }

    override fun waitkey(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * FX15
     *
     * Sets delay register to value at register r[reg]
     */
    override fun setdelay(reg: Int) {
        state.delay = state.registers[reg].toPositiveInt()
    }

    /**
     * FX18
     *
     * Sets sound register to value at register r[reg].
     */
    override fun setsound(reg: Int) {
        state.sound = state.registers[reg].toPositiveInt()
    }

    override fun addi(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun spritei(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bcd(reg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * FX55
     *
     * Store the values of registers V[0] to V[reg] inclusive in memory starting at address I.
     * I is set to I + X + 1 after operation.
     */
    override fun push(reg: Int) {
        // Iterate over registers
        for(i in 0..reg ){
            // Save register value to memory
            state.mem.write(state.I,state.registers[i])
            state.I++
        }

    }

    /**
     * FX65
     *
     * Fill registers V[0] to V[reg] inclusive with the values stored in memory starting at address I.
     * I is set to I + X + 1 after operation
     */
    override fun pop(reg: Int) {
        for(i in 0..reg){
            // Save values from memory to registers
            state.registers[i] = state.mem.read(state.I)
            state.I++
        }
    }
}