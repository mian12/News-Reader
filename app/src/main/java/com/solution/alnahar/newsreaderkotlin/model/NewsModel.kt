package com.solution.alnahar.newsreaderkotlin.model

class NewsModel(var status: String, var totalResults: String, var articles: MutableList<Article>)

class Article(var source: Sourrce, var author: String, var title: String, var description: String, var url: String, var urlToImage: String, var publishedAt: String)


class Sourrce(var id: String, var name: String)



