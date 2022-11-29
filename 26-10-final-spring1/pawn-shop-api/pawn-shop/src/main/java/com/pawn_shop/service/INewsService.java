package com.pawn_shop.service;

import com.pawn_shop.dto.projection.INewsDto;
import com.pawn_shop.model.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface INewsService {

    Page<INewsDto> findAllNews(Pageable pageable, String searchName, String searchContent, String dateFirst, String dateLast);

    News getNewsById(Long id);

    void deleteNews(Long idDelete);

    void saveNews(News news);


}
