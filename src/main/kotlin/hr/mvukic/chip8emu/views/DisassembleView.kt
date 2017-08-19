package hr.mvukic.chip8emu.views

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hr.mvukic.chip8emu.impl.Opcode
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.scene.input.KeyCombination
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File
import javax.json.JsonObject

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
                            val json = opcodes.map { it.toJSON() }.toString()
                            val files = chooseFile(
                                    title = "Save disassembly",
                                    mode = FileChooserMode.Save,
                                    filters = arrayOf(FileChooser.ExtensionFilter("*.json","*.json"))
                            )
                            if(files.size == 1){
                                files.get(0).writeText(json)
                            }

                        }
                    }
                    item("csv"){
                        action{
                            // implement saving to csv
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
