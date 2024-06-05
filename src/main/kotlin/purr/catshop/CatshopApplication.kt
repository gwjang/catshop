package purr.catshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatshopApplication

fun main(args: Array<String>) {
    runApplication<CatshopApplication>(*args)
}
