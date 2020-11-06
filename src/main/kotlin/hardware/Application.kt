package hardware

import io.micronaut.runtime.Micronaut

open class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Micronaut.run(Application::class.java)
        }
    }
}
