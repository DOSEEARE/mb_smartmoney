package com.molbulak.smartmoney.cicerone

import com.github.terrakok.cicerone.Cicerone
import com.molbulak.smartmoney.util.enums.ContainerType
import java.util.*

object LocalCiceroneHolder {
    private val containers = HashMap<ContainerType, LocalCicerone>()

    fun getCicerone(containerType: ContainerType): LocalCicerone =
        containers.getOrPut(containerType) {
            LocalCicerone()
        }
}

class LocalCicerone {
    val cicerone = Cicerone.create()
    val router = cicerone.router
}


