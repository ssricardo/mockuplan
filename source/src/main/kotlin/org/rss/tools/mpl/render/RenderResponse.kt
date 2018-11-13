package org.rss.tools.mpl.render

class RenderResponse {

    var type: ResponseType? = null

    var data: Any? = null

    constructor(type: ResponseType, data: Any?) {
        this.type = type
        this.data = data
    }

}
