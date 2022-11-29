package com.pawn_shop.controller;

import com.pawn_shop.dto.projection.INewsDto;
import com.pawn_shop.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/public/news")
public class NewsRestController {
    @Autowired
    private INewsService newsService;

    @GetMapping("/list-news")
    public ResponseEntity<Page<INewsDto>> getAllNews(@PageableDefault(size = 5) Pageable pageable,
                                                     @RequestParam("title") Optional<String> titleSearch,
                                                     @RequestParam("content") Optional<String> contentSearch,
                                                     @RequestParam Optional<String> firstDate,
                                                     @RequestParam Optional<String> lastDate) {
        String searchName = titleSearch.orElse("");
        String searchContent = contentSearch.orElse("");
        String dateFirst = firstDate.orElse("0001-01-01");
        String dateLast = lastDate.orElse("9000-01-01");
        if (searchName.equals("")) {
            searchName = "";
        }
        if (dateFirst.equals("null")) {
            dateFirst = "0001-01-01";
        }
        if (dateLast.equals("null")) {
            dateLast = "9000-01-01";
        }
        Page<INewsDto> newsDtos = this.newsService.findAllNews(pageable, searchName, searchContent, dateFirst, dateLast);
        if (newsDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(newsDtos, HttpStatus.OK);
        }
    }
}
