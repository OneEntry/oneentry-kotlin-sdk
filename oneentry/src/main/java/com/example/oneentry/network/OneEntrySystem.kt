package com.example.oneentry.network

import com.example.oneentry.model.OneEntryException
import com.example.oneentry.network.core.OneEntryCore

class OneEntrySystem private constructor() {

    private val core = OneEntryCore.instance

    companion object {

        val instance: OneEntrySystem = OneEntrySystem()
    }

    /**
     * Emulate 404 error
     *
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun test404() {

        core.requestData("/system/test404")
    }

    /**
     * Emulate 500 error
     *
     * @throws RuntimeException if OneEntry application has not been initialized
     * @throws IllegalArgumentException if the decoded input is not a valid instance of T or serializer error
     * @throws OneEntryException in case of OneEntry errors
     */
    suspend fun test500() {

        core.requestData("/system/test500")
    }
}