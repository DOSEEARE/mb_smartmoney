package com.molbulak.smartmoney.service.network


data class Resource<out T>(val status: Status, val  data: T?, val msg: String?, val code: Int?) {
    companion object {
        fun <T> success(data: T?, msg: String = "", code: Int?): Resource<T> {
            return Resource(Status.SUCCESS, data, msg, code)
        }

        fun <T> error(data: T?, msg: String = "", code: Int?): Resource<T> {
            return Resource(Status.ERROR, data, msg, code)
        }

        fun <T> network(data: T?, msg: String = "", code: Int?): Resource<T> {
            return Resource(Status.NETWORK, data, msg, code)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    NETWORK,
}
