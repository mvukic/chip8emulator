package hr.mvukic.chip8emu.impl

import hr.mvukic.chip8emu.*
import hr.mvukic.chip8emu.interfaces.ICPU
import hr.mvukic.chip8emu.interfaces.IDecoder

/**
 * Created by matija on 08.06.17..
 */
class Cpu(private var memory: Memory) : ICPU {

    // flags, registers and counters
    private var state: VmState = VmState(
            mem = this.memory,
            stack = IntArray(16),
            sp = 0,
            registers = ByteArray(16),
            I = 0,
            delay = 0,
            sound = 0,
            keys = ByteArray(16),
            pc = 80
    )

    // indicates if CPU should run
    var running = true

    override fun start() {
        val decoder = Decoder(state)
        println("Running")

        // ASYNC!!
        while(running){
            decode(decoder,state.pc+80,memory.read(state.pc),memory.read(state.pc + 1))
        }

        println("Stopped")
    }

    override fun halt() {
        running = false
    }

    override fun disassemble(): List<Opcode> {
        val decoder = Disassembler()
        for(i in 80 until memory.size step 2) {
            decode(decoder,i,memory.read(i),memory.read(i + 1))
            decoder.after()
        }
        return decoder.operations
    }

    fun decode(decoder: IDecoder, address: Int, msb:Byte, lsb: Byte){
        val opCode = (msb.toInt() shl 8 or lsb.toPositiveInt()).and(0xffff)
        decoder.before(opCode, address)
        when (msb.high()) {
            0x0 -> {
                when (msb.toPositiveInt() shl 8 or lsb.toPositiveInt()) {
                    0x00e0 -> decoder.clear()
                    0x00ee -> decoder.ret()
                    else -> decoder.unknown(opCode, address)
                }
            }
            0x1 -> decoder.jmp(address(msb, lsb))
            0x2 -> decoder.call(address(msb, lsb))
            0x3 -> decoder.jeq(msb.low(), lsb.toPositiveInt())
            0x4 -> decoder.jneq(msb.low(), lsb.toPositiveInt())
            0x5 -> decoder.jeqr(msb.low(), lsb.high())
            0x6 -> decoder.set(msb.low(), lsb.toPositiveInt())
            0x7 -> decoder.add(msb.low(), lsb.toPositiveInt())
            0x8 -> {
                val reg1 = msb.low()
                val reg2 = lsb.high()
                when(lsb.low()) {
                    0x0 -> decoder.setr(reg1, reg2)
                    0x1 -> decoder.or(reg1, reg2)
                    0x2 -> decoder.and(reg1, reg2)
                    0x3 -> decoder.xor(reg1, reg2)
                    0x4 -> decoder.addr(reg1, reg2)
                    0x5 -> decoder.sub(reg1, reg2)
                    0x6 -> decoder.shr(reg1)
                    0x7 -> decoder.subb(reg1, reg2)
                    0xe -> decoder.shl(reg1)
                    else -> decoder.unknown(opCode, address)
                }
            }
            0x9 -> {
                val reg1 = msb.low()
                val reg2 = lsb.high()
                decoder.jneqr(reg1, reg2)
            }
            0xa -> decoder.seti(address(msb, lsb))
            0xb -> decoder.jmpv0(address(msb, lsb))
            0xc -> decoder.rand(msb.low(), lsb.toPositiveInt())
            0xd -> decoder.draw(msb.low(), lsb.high(), lsb.low())
            0xe -> {
                when(lsb.toPositiveInt()) {
                    0x9e -> decoder.jkey(msb.low())
                    0xa1 -> decoder.jnkey(msb.low())
                    else -> decoder.unknown(opCode, address)
                }
            }
            0xf -> {
                val reg = msb.low()
                when(lsb.toInt()) {
                    0x07 -> decoder.getdelay(reg)
                    0x0a -> decoder.waitkey(reg)
                    0x15 -> decoder.setdelay(reg)
                    0x18 -> decoder.setsound(reg)
                    0x1e -> decoder.addi(reg)
                    0x29 -> decoder.spritei(reg)
                    0x33 -> decoder.bcd(reg)
                    0x55 -> decoder.push(reg)
                    0x65 -> decoder.pop(reg)
                    else -> decoder.unknown(opCode, address)
                }
            }
            else -> decoder.unknown(opCode, address)
        }
    }

    override fun cycle(){

    }
}