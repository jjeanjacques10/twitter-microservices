package com.twitter.exception

import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime


@Data
@Builder
@NoArgsConstructor
open class ExceptionDetails(
    var title: String? = null,
    var status: Int = 0,
    var details: String? = null,
    var timestamp: LocalDateTime? = null,
    var developerMethod: String? = null
)

