package com.example.oneentry.config

import com.example.oneentry.model.OneEntryMenuPage

class OneEntryTree(private val pages: List<OneEntryMenuPage>) {

    private var data: MutableList<OneEntryMenuPage> = mutableListOf()

    fun buildTree() {

        val rootPages = pages.filter { it.parentId == null }

        rootPages.forEach { page ->
            buildSubtree(page)
        }
    }

    private fun buildSubtree(parent: OneEntryMenuPage) {

        data.add(parent)

        val childPages = pages.filter { it.parentId == parent.id }
        parent.children = childPages

        childPages.forEach { child ->
            buildSubtree(child)
        }
    }
}