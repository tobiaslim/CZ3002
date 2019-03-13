package com.example.harrisonwjy.charitree.repo.interfaces


/**
 * An DonationInterface Interface for DonationRepo
 * @author Harrison Wong
 */
interface DonationInterface : RepositoryInterface{

    fun getAll(): Any
    fun create(id: Int, item: Any): Any
    fun getCount(item: Any): Any
}