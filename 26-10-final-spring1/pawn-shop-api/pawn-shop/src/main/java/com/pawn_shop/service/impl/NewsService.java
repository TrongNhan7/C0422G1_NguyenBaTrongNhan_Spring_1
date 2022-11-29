package com.pawn_shop.service.impl;

import com.pawn_shop.dto.projection.INewsDto;
import com.pawn_shop.model.news.News;
import com.pawn_shop.repository.INewsRepository;
import com.pawn_shop.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements INewsService {
    @Autowired
    private INewsRepository newsRepository;

    @Override
    public Page<INewsDto> findAllNews(Pageable pageable, String searchName, String searchContent, String dateFirst, String dateLast) {
        return  this.newsRepository.findAllNews(pageable,"%" +searchName + "%","%" + searchContent + "%",dateFirst,dateLast);
    }


    @Override
    public News getNewsById(Long id) {
        return this.newsRepository.getNewsById(id);
    }

    @Override
    public void deleteNews(Long idDelete) {
        this.newsRepository.deleteNews(idDelete);
    }

    @Override
    public void saveNews(News news) {
        this.newsRepository.saveNews(news.getContent(),news.getImgUrl(),news.getPostingDay(),news.getTitle());
    }

}
