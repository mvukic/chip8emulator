package hr.mvukic.chip8emu.views

import tornadofx.*

/**
 * Created by matija on 10.06.17..
 */
class AboutView : Fragment("My View") {

    override val root = borderpane {
        center = label("About modal")
    }
}
