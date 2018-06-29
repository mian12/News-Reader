package com.solution.alnahar.newsreaderkotlin.common

import java.text.SimpleDateFormat
import java.util.*

object ISO86601Parser {

    fun parser(params: String): Date {

        var input = params
        val df =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

        if (input.endsWith("Z")) {
            input = input.substring(0, input.length - 1) + "GMT-00:00"
        } else {
            var inset = 6
            val startText = input.subSequence(0, input.length - inset)
            val endText = input.subSequence(input.length - inset, input.length)
            input = StringBuilder(startText).append("GMT").append(endText).toString()


        }
        return df.parse(input)


    }

    fun toString(date:Date):String{


        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        val tz=TimeZone.getTimeZone("UTC")
        df.timeZone=tz
        val output=df.format(date)

        val inset0=9
        val inset1=6

        val s0 = output.subSequence(0, output.length - inset0)
        val s1 = output.subSequence(output.length - inset1, output.length)

       var result=StringBuilder(s0).append(s1).toString()

        result= result.replace("UTC".toRegex(),"+00:00")
        return  result

    }
}