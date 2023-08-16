package com.mrozeka.naszdom.firebase.models

import com.google.firebase.firestore.PropertyName

data class House(
    @PropertyName("Title") var title: String = "",
    @PropertyName("adress") var adress: String = "",
    @PropertyName("KW") var kw: String = "",
    @PropertyName("cost") var cost: Map<String, Map<String, Map<String, Any>>> = emptyMap()
) {

    fun getCostList(): ArrayList<Cost> {
        val result = ArrayList<Cost>()
        for (entry in cost.entries.iterator()) {
            val projects: ArrayList<Project> = ArrayList()
            val projectsMap = entry.value
            for (prEntry in projectsMap.entries.iterator()) {
                val detailsMap = prEntry.value
                val project = Project(prEntry.key)
                for (detailsEntry in detailsMap.entries.iterator()) {
                    when (detailsEntry.key) {
                        "url" -> project.url = detailsEntry.value.toString()
                        "price" -> project.price = detailsEntry.value.toString()
                        "foundation" -> project.foundation = detailsEntry.value as Boolean
                        "imgId" -> project.imgId = detailsEntry.value.toString()
                    }
                }
                projects.add(project)
            }
            result.add(Cost(entry.key, projects))
        }
        return result
    }
}