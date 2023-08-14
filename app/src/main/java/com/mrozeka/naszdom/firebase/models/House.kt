package com.mrozeka.naszdom.firebase.models

import com.google.firebase.firestore.PropertyName

data class House(
    @PropertyName("Title") var title: String = "",
    @PropertyName("adress") var adress: String = "",
    @PropertyName("KW") var kw: String = "",
    @PropertyName("cost") var cost: Map<String, Map<String, String>> = emptyMap()
) {

    fun getCostList(): ArrayList<Cost> {
        val result = ArrayList<Cost>()
        for (entry in cost.entries.iterator()) {
            val projects:ArrayList<Project> = ArrayList()
            val projectsMap =  entry.value
            for ( prEntry in projectsMap.entries.iterator()) {
                projects.add(Project(prEntry.key, prEntry.value))
            }
            result.add(Cost(entry.key, projects))
        }
        return result
    }
}