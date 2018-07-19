package com.tech.royal_vee.myapplication.model

class Studentdetails {
    var id: String? = null
    var Fullname: String? = null
    var regnum: String? = null
    var SubmittedAssignment: String? = null
    var Score: String? = null
    var Paswword: String? = null

    constructor() {}

    constructor(id: String, fullname: String, regnum: String, message: String, score: String ) {
        this.id = id
        this.Fullname = fullname
        this.regnum = regnum
        this.SubmittedAssignment = message
        this.Score = score
    }

    constructor(id: String, fullname: String) {
        this.id = id
        this.Fullname = fullname
    }

   /** fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("title", title!!)
        result.put("content", content!!)

        return result
    }*/
}