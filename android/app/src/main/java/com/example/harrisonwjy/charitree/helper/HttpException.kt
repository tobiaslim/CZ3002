package com.example.harrisonwjy.charitree.helper

interface HttpException {
    fun onHttpException(code: Int?): String{
        when (code){
            400 -> return badRequest()
            401 -> return unauthorized()
            404 -> return notFound()
            405 -> return methodNotFound()
            406 -> return notAcceptable()
            408 -> return requestTimeOut()
            422 -> return unprocessableEntity()
            429 -> return tooManyRequest()
            else -> return "HTTP Code not found"
        }
    }

    fun badRequest():String{return "badRequest"}
    fun unauthorized():String{return "unauthorized"}
    fun notFound():String{return "notFound"}
    fun methodNotFound():String{return "methodNotFound"}
    fun notAcceptable():String{return "notAcceptable"}
    fun requestTimeOut():String{return "requestTimeOut"}
    fun unprocessableEntity():String{return "unprocessableEntity"}
    fun tooManyRequest():String{return "tooManyRequest"}


}