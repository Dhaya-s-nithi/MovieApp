package com.codepark.moviedemoapp.network

import com.google.gson.annotations.SerializedName

data class MovieListResponse(@SerializedName("score" ) var score : Double? = null,
                             @SerializedName("show"  ) var show  : Show?   = null)

data class Show(
    @SerializedName("id") var id: Int?= null,
    @SerializedName("url") var url : String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type : String? = null,
    @SerializedName("language") var language: String?= null,
    @SerializedName("genres") var genres: ArrayList<String> = arrayListOf(),
    @SerializedName("status") var status : String? = null,
    @SerializedName("runtime") var runtime : Int?  = null,
    @SerializedName("averageRuntime") var averageRuntime : Int? = null,
    @SerializedName("premiered") var premiered : String? = null,
    @SerializedName("ended" ) var ended : String? = null,
    @SerializedName("officialSite") var officialSite: String? = null,
    @SerializedName("schedule") var schedule : Schedule? = Schedule(),
    @SerializedName("rating") var rating : Rating? = Rating(),
    @SerializedName("weight") var weight : Int? = null,
    @SerializedName("network") var network : Network? = Network(),
    @SerializedName("webChannel") var webChannel : Any? = null,
    @SerializedName("dvdCountry") var dvdCountry : Any?  = null,
    @SerializedName("externals") var externals : Externals? = Externals(),
    @SerializedName("image" ) var image : Image? = Image(),
    @SerializedName("summary") var summary : String? = null,
    @SerializedName("updated") var updated : Int? = null,
    @SerializedName("_links" ) var Links : Links? = Links()

)

data class Rating(@SerializedName("average") var average : Double? = null)

data class Schedule(
    @SerializedName("time") var time : String?= null,
    @SerializedName("days" ) var days : ArrayList<String> = arrayListOf())

data class Network (
    @SerializedName("id") var id : Int? = null,
    @SerializedName("name") var name : String?= null,
    @SerializedName("country" ) var country : Country? = Country()

)

data class Country (

    @SerializedName("name") var name : String? = null,
    @SerializedName("code") var code : String? = null,
    @SerializedName("timezone") var timezone : String? = null

)

data class Externals (

    @SerializedName("tvrage") var tvrage : String? = null,
    @SerializedName("thetvdb") var thetvdb : Int? = null,
    @SerializedName("imdb") var imdb : String? = null

)

data class Image (

    @SerializedName("medium") var medium : String? = null,
    @SerializedName("original") var original : String? = null

)

data class Links(

    @SerializedName("self") var self : Self? = Self(),
    @SerializedName("previousepisode") var previousepisode : Previousepisode? = Previousepisode(),
    @SerializedName("nextepisode") var nextepisode : Nextepisode? = Nextepisode()

)

data class Self(@SerializedName("href") var href : String? = null)

data class Previousepisode(@SerializedName("href") var href : String? = null)


data class Nextepisode (@SerializedName("href" ) var href : String? = null)