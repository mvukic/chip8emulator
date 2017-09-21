package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.interfaces.IDecoder
import hr.mvukic.chip8emu.*

class Disassembler : IDecoder {
    val builder = StringBuilder()
    val operations: MutableList<Opcode> = mutableListOf()
    var currentOpcode = Opcode()
    var index = 0
    var command = ""

    override fun before(opCode: Int, address: Int) {
        currentOpcode.address = address.toString()
        currentOpcode.hex = opCode.toHex()
        currentOpcode.index = index
        builder.append("addr: 0x${address.toHex()}, op: 0x${opCode.toHex()}, ")
    }

    override fun after(){
        currentOpcode.command = command
        operations.add(currentOpcode)
        index++
        currentOpcode = Opcode()
    }

    override fun unknown(opCode: Int, address: Int) {
        command = "Unknown opcode addr: 0x${address.toHex()}, op: 0x${opCode.toHex()}"
        builder.append(command)
    }

    override fun clear() {
        command = "clear"
        builder.line(command)
    }

    override fun ret() {
        command = "ret"
        builder.line(command)
    }

    override fun jmp(address: Int) {
        command = "jmp 0x${address.toHex()}"
        builder.line(command)
    }

    override fun call(address: Int) {
        command = "call 0x${address.toHex()}"
        builder.line(command)
    }

    override fun jeq(reg: Int, value: Int) {
        command ="jeq v${reg.toHex()}, 0x${value.toHex()}"
        builder.line(command)
    }

    override fun jneq(reg: Int, value: Int) {
        command ="jneq v${reg.toHex()}, 0x${value.toHex()}"
        builder.line(command)
    }

    override fun jeqr(reg1: Int, reg2: Int) {
        command = "jeqr v${reg1.toHex()}, v${reg2.toHex()}"
        builder.line(command)
    }

    override fun set(reg: Int, value: Int) {
        command ="set v${reg.toHex()}, 0x${value.toHex()}"
        builder.line(command)
    }

    override fun add(reg: Int, value: Int) {
        command ="add v${reg.toHex()}, 0x${value.toHex()}"
        builder.line(command)
    }

    override fun setr(reg1: Int, reg2: Int) {
        command ="setr v${reg1.toHex()}, v${reg2.toHex()}"
        builder.line(command)
    }

    override fun or(reg1: Int, reg2: Int) {
        command ="or v${reg1.toHex()}, v${reg2.toHex()}"
        builder.line(command)
    }

    override fun and(reg1: Int, reg2: Int) {
        command ="and v${reg1.toHex()}, v${reg2.toHex()}"
        builder.line(command)
    }

    override fun xor(reg1: Int, reg2: Int) {
        command ="xor v${reg1.toHex()}, v${reg2.toHex()}"
        builder.line(command)
    }

    override fun addr(reg1: Int, reg2: Int) {
        command ="addr v${reg1.toHex()}, v${reg2.toHex()}"
        builder.line(command)
    }

    override fun sub(reg1: Int, reg2: Int) {
        command ="sub v${reg1.toHex()}, v${reg2.toHex()}"
        builder.line(command)
    }

    override fun shr(reg1: Int) {
        command ="shr v${reg1.toHex()}"
        builder.line(command)
    }

    override fun subb(reg1: Int, reg2: Int) {
        command ="subb v${reg1.toHex()}, v${reg2.toHex()}"
        builder.line(command)
    }

    override fun shl(reg1: Int) {
        command ="shl v${reg1.toHex()}"
        builder.line(command)
    }

    override fun jneqr(reg1: Int, reg2: Int) {
        command ="jneqr v${reg1.toHex()}, v${reg2.toHex()}"
        builder.line(command)
    }

    override fun seti(value: Int) {
        command ="seti 0x${value.toHex()}"
        builder.line(command)
    }

    override fun jmpv0(address: Int) {
        command ="jmpv0 0x${address.toHex()}"
        builder.line(command)
    }

    override fun rand(reg: Int, value: Int) {
        command ="rand v${reg.toHex()}, 0x${value.toHex()}"
        builder.line(command)
    }

    override fun draw(reg1: Int, reg2: Int, value: Int) {
        command ="draw v${reg1.toHex()}, v${reg2.toHex()}, 0x${value.toHex()}"
        builder.line(command)
    }

    override fun jkey(reg: Int) {
        command ="jkey v${reg.toHex()}"
        builder.line(command)
    }

    override fun jnkey(reg: Int) {
        command ="jnkey v${reg.toHex()}"
        builder.line(command)
    }

    override fun getdelay(reg: Int) {
        command ="getdelay v${reg.toHex()}"
        builder.line(command)
    }

    override fun waitkey(reg: Int) {
        command ="waitkey v${reg.toHex()}"
        builder.line(command)
    }

    override fun setdelay(reg: Int) {
        command ="setdelay v${reg.toHex()}"
        builder.line(command)
    }

    override fun setsound(reg: Int) {
        command ="setsound v${reg.toHex()}"
        builder.line(command)
    }

    override fun addi(reg: Int) {
        command ="addi v${reg.toHex()}"
        builder.line(command)
    }

    override fun spritei(reg: Int) {
        command ="spritei v${reg.toHex()}"
        builder.line(command)
    }

    override fun bcd(reg: Int) {
        command ="bcd v${reg.toHex()}"
        builder.line(command)
    }

    override fun push(reg: Int) {
        command ="push v0-v${reg.toHex()}"
        builder.line(command)
    }

    override fun pop(reg: Int) {
        command ="pop v0-v${reg.toHex()}"
        builder.line(command)
    }

    override fun toString(): String {
        return builder.toString()
    }
}