package com.rajesh.recyclerview.view.ui

class BaseUI {

    var uiStatus: UiStatus? = null
    var message: String = ""

    enum class UiStatus {
        SUCCESS,
        ERROR,
        LOADING,
        NO_INTERNET
    }

    companion object {
        fun loading(): BaseUI {
            return BaseUI(UiStatus.LOADING)
        }

        fun noInternet(): BaseUI {
            return BaseUI(UiStatus.NO_INTERNET)
        }

        fun error(message: String): BaseUI {
            return BaseUI(UiStatus.ERROR, message)
        }

        fun success(message: String): BaseUI {
            return BaseUI(UiStatus.SUCCESS, message)
        }

        fun success(): BaseUI {
            return BaseUI(UiStatus.SUCCESS)
        }
    }

    constructor(uiStatus: UiStatus) {
        this.uiStatus = uiStatus
    }

    constructor(uiStatus: UiStatus, message: String) {
        this.uiStatus = uiStatus
        this.message = message
    }

    constructor(){

    }
}