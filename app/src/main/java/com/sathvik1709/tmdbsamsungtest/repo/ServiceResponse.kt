package com.sathvik1709.tmdbsamsungtest.repo

interface ServiceResponse<T> {
    fun onSuccess(t : T)
    fun onErrorMsg(errorMessage : String)
}