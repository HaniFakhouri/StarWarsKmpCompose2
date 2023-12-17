package model

import kotlinx.serialization.Serializable

/**
 * Created by hani.fakhouri on 2023-07-29.
 */

@Serializable
data class GetPeopleResponse(
    val results: List<Person>
)