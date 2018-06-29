package com.solution.alnahar.newsreaderkotlin.model

class HomeFeed( var user:User ,val  videos:List<Videos>)


class User(val id:Int,val name:String,val username:String)

class Videos(val id:Int,var name:String,val link:String,val imageUrl:String,val numberOfViews:String,val channel:Channel)

class Channel(val name: String,val profileImageUrl:String,val numberOfSubscribers:String )

class CoursesLessons( var name:String,var  duration:String, var  number:String, var imageUrl:String,var link: String)

//name": "Creating a Registration Screen",
//"duration": "21:50",
//"number": 1,
//"imageUrl": "https://letsbuildthatapp-videos.s3-us-west-2.amazonaws.com/d6938a65-b273-421b-976a-40cf923a17ba_thumbnail",
//"link": "https://