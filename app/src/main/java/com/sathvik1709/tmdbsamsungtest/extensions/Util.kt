package com.sathvik1709.tmdbsamsungtest.extensions

class Util {

    fun getPrettyDisplayList(list : List<String>) : String{
        var res : StringBuilder = StringBuilder()
        for (i in list.indices) {
            if(i != list.size - 1){
                res.append(list[i]).append(", ")
            }else{
                res.append(list[i])
            }
        }
        return res.toString()
    }

}
