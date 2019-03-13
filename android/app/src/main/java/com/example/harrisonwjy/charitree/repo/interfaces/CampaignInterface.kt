package com.example.harrisonwjy.charitree.repo.interfaces


/**
 * An Campaign Interface for CampaignRepo
 * @author Harrison Wong
 */
interface CampaignInterface : RepositoryInterface {

    fun register(item: Any): Any
    fun verify(item: Any): Any
    fun showAll(): Any
    fun showAllByCMSession(): Any
    fun getOrgNameByUEN(item: Any): Any
    fun getItems(): Any
    fun create(item: Any): Any
    fun showDonors(item: Any): Any
    fun getDonationByDID(item: Any): Any
    fun changeStatusByDID(id: Int,item: Any): Any

}