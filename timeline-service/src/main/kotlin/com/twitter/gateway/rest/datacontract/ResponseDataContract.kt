package com.twitter.gateway.rest.datacontract

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
data class ResponseDataContract<T>(
    var data: T? = null
)