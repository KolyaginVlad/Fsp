package ru.cpt.fsp.ui.auth.analytics

import ru.cpt.fsp.utils.analytics.AnalyticsEvent

data class AuthAnalyticsEvent(
    val isSuccessful: Boolean,
) : AnalyticsEvent {
    override val name: String = "authorization"
    override val arguments: Map<String, Any?> = mapOf(
        "isSuccessful" to isSuccessful,
    )
}