package hr.mvukic.chip8emu.interfaces

/**
 * Created by matija on 08.06.17..
 */
interface CpuInterface{

    fun start()
    fun halt()

    fun fetch()
    fun decode()
    fun execute()

    fun notifyListeners()

    fun cycle(){

        fetch()
        decode()
        execute()

        notifyListeners()
    }
}