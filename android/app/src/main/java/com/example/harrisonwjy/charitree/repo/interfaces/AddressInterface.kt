package com.example.harrisonwjy.charitree.repo.interfaces

/**
 * An Address Interface for AddressRepo
 * @author Harrison Wong
 */
interface AddressInterface : RepositoryInterface {

    /**
     * A get method
     * @return Any
     */
    fun get(): Any

    /**
     * A create method
     * @return Any
     */
    fun create(item: Any): Any
}