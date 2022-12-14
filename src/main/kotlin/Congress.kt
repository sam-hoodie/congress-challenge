import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException

data class Person(
    val name: Name,
    val bio: Bio,
    val terms: List<Term>,
)

data class Name(
    val first: String,
    val last: String,
)

data class Bio(
    val birthday: String?,
    val gender: String,
)

data class Term(
    val type: String,
    val start: String,
    val end: String,
    val state: String,
    val party: String?,
    val district: String?,
)

fun parseCongressFile(current: Boolean): List<Person> {
    val input = if (current) {
        Person::class.java.getResourceAsStream("/legislators-current.yaml")
    } else {
        Person::class.java.getResourceAsStream("/legislators-historical.yaml")
    }
    val mapper = ObjectMapper(YAMLFactory())
    mapper.registerModule(KotlinModule())
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    val typeFactory: TypeFactory = mapper.typeFactory
    return try {
        mapper.readValue(
            input, typeFactory.constructCollectionType(
                MutableList::class.java,
                Person::class.java
            )
        )
    } catch (exception: MissingKotlinParameterException) {
        println("Could not read YAML file!")
        println(exception.message)
        throw exception
    }
}

fun main() {
    val result = parseCongressFile(false)

    println(result.size)

}