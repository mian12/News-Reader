package com.solution.alnahar.newsreaderkotlin.model



class Website(val  sources:List<Source>){}



class Source( var id:String,var  name:String,var description:String,var url:String,var category:String,var language:String,var country:String ) {
}

//id": "abc-news",
//"name": "ABC News",
//"description": "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
//"url": "http://abcnews.go.com",
//"category": "general",
//"language": "en",
//"country": "us"