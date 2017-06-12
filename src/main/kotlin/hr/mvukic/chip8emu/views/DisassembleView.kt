package hr.mvukic.chip8emu.views

import tornadofx.*

/**
 * Created by matija on 10.06.17..
 */
class DisassembleView : Fragment("Disassemble") {

    val assembly: String by param()

    override val root = borderpane {
        setMinSize(400.0,400.0)
        center = textarea(assembly) {

        }
    }
}
