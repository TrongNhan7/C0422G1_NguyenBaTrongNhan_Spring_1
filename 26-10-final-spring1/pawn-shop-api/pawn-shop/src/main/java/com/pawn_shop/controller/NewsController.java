package com.pawn_shop.controller;

import com.pawn_shop.dto.NewsDto;
import com.pawn_shop.dto.projection.INewsDto;
import com.pawn_shop.model.news.News;
import com.pawn_shop.service.INewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/employee/news")
public class NewsController {
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

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createNews(@Valid @RequestBody NewsDto newsDto, BindingResult bindingResult) {
        new NewsDto().validate(newsDto, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
        }
        News news = new News();
        BeanUtils.copyProperties(newsDto, news);
        news.setPostingDay(newsDto.getPostingDay());
        this.newsService.saveNews(news);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<News> findNewsById(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Optional<News> newsOptional = Optional.ofNullable(this.newsService.getNewsById(id));
            return new ResponseEntity<>(newsOptional.get(), HttpStatus.OK);
        }
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable("id") Long idDelete) {
        newsService.deleteNews(idDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
