package hardware.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import hardware.utils.LowerCaseConverter
import org.hibernate.annotations.Proxy
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Entity
@Proxy(lazy = false)
@Table(name = "t_computer")
@JsonIgnoreProperties(ignoreUnknown = true)
data class Computer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty
        @field:Min(value = 0L)
        @Column(name = "computer_id", nullable = false)
        var accountId: Long,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "computer_name", unique = true, nullable = false)
        @JsonProperty
        var computerName: String
        ) {

    constructor() : this(0L, "")

    override fun toString(): String = mapper.writeValueAsString(this)

    companion object {
        @JsonIgnore
        private val mapper = ObjectMapper()
    }
}