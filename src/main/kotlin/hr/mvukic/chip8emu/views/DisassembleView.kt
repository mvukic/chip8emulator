package hr.mvukic.chip8emu.views

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hr.mvukic.chip8emu.impl.Opcode
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.scene.input.KeyCombination
import tornadofx.*
import java.io.File

/**
 * Created by matija on 10.06.17..
 */
class DisassembleView : Fragment("Disassembler view") {

    val opcodes: List<Opcode> by param()

    override val root = borderpane()

    init{
        with(root){
            setMinSize(400.0,400.0)
            top = menubar{
                menu("Save") {
                    item("json") {
                        action{
                            println("Save to json")
                        }
                    }
                    item("csv"){
                        action{
                            println("Save to csv")
                        }
                    }
                }
            }
            center = tableview(FXCollections.observableArrayList<Opcode>(opcodes))
            {
                column("#", Opcode::index)
                column("Address", Opcode::address)
                column("Hex",Opcode::hex)
                column("Command",Opcode::command)

                columnResizePolicy = SmartResize.POLICY
            }
        }
    }
}
