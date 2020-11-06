package hardware.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import hardware.utils.LowerCaseConverter
import io.micronaut.validation.Validated
import org.hibernate.annotations.Proxy
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Entity
@Proxy(lazy = false)
@Table(name = "t_computer")
@JsonIgnoreProperties(ignoreUnknown = true)
//@Validated
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
        var computerName: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "processor", unique = true, nullable = false)
        @JsonProperty
        var processor: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "processor_codename", unique = true, nullable = false)
        @JsonProperty
        var processorCodename: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "motherboard", unique = true, nullable = false)
        @JsonProperty
        var motherboard: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "motherboard_chipset", unique = true, nullable = false)
        @JsonProperty
        var motherboardChipset: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "primary_operating_system", unique = true, nullable = false)
        @JsonProperty
        var primaryOperatingSystem: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "secondary_operating_system", unique = true, nullable = false)
        @JsonProperty
        var secondaryOperatingSystem: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "location", unique = true, nullable = false)
        @JsonProperty
        var location: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "video_card", unique = true, nullable = false)
        @JsonProperty
        var videoCard: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "video_card_codename", unique = true, nullable = false)
        @JsonProperty
        var videoCardCodename: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "form_factor", unique = true, nullable = false)
        @JsonProperty
        var formFactor: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "optical_drive", unique = true, nullable = false)
        @JsonProperty
        var opticalDrive: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "solid_state_drive", unique = true, nullable = false)
        @JsonProperty
        var solidStateDrive: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "spinning_drive", unique = true, nullable = false)
        @JsonProperty
        var spinningDrive: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "memory_size", unique = true, nullable = false)
        @JsonProperty
        var memorySize: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "power_supply_unit", unique = true, nullable = false)
        @JsonProperty
        var powerSupplyUnit: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "hdmi_port", unique = true, nullable = false)
        @JsonProperty
        var hdmiPort: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "network_port", unique = true, nullable = false)
        @JsonProperty
        var networkPort: String,

        @field:Size(min = 1, max = 50)
        @field:Convert(converter = LowerCaseConverter::class)
        @Column(name = "notes", unique = true, nullable = false)
        @JsonProperty
        var notes: String

        ) {

    constructor() : this(0L, "","","","","","",
            "","", "","","","","","",
            "","","", "", "")

    override fun toString(): String = mapper.writeValueAsString(this)

    companion object {
        @JsonIgnore
        private val mapper = ObjectMapper()
    }
}
