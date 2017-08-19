package hr.mvukic.chip8emu.impl

import tornadofx.JsonBuilder
import tornadofx.JsonModel
import tornadofx.getProperty
import tornadofx.property

/**
 * Created by matija on 10.06.17..
 */
class Opcode(var index:Int=0,var address:String="",var hex:String="", var command:String=""): JsonModel{



    fun addressProperty() = getProperty(Opcode::address)

    fun hexProperty() = getProperty(Opcode::hex)

    fun commandProperty() = getProperty(Opcode::command)

    override fun toJSON(json: JsonBuilder) {
        with(json){
            add("index",index)
            add("address",address)
            add("hex",hex)
            add("command",command)
        }
    }
}