package hr.mvukic.chip8emu.views

import javafx.collections.FXCollections
import javafx.scene.layout.GridPane
import tornadofx.*

/**
 * Created by matija on 12.06.17..
 */
class KeyBindongsView : View("My View") {

    val keys: Map<String,String> by param()

    override val root = GridPane()

    init{
        with(root){
            label("Key bindings for emulator")
            tableview(FXCollections.observableArrayList<Map.Entry<String, String>>(keys.entries))
                {
                    column("Original", Map.Entry<String, String>::key)
                    column("New", Map.Entry<String, String>::value)

                    columnResizePolicy = SmartResize.POLICY
                }
        }
    }
}
