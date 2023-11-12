package com.example.sigmaindustry.domain.usecases.news

import com.example.sigmaindustry.data.local.NewsDao
import com.example.sigmaindustry.domain.model.Article
import javax.inject.Inject

class GetSavedArticle @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article?{
        return newsDao.getArticle(url = url)
    }

}