package com.pawn_shop.repository;

import com.pawn_shop.dto.projection.INewsDto;
import com.pawn_shop.model.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface INewsRepository extends JpaRepository<News, Long> {

    @Transactional
    @Query(value = "select news.id,news.title,news.content,news.posting_day as postingDay, " +
            "news.img_url as imgUrl,news.app_user_id as appUser,news.status " +
            " from news" +
            " join app_user on app_user.id = news.app_user_id" +
            " join employee on employee.id = app_user.employee_id" +
            " where news.title like :searchName and news.content like :searchContent and (news.posting_day between :dateFirst and :dateLast) and news.`status` = 1" +
            " order by news.posting_day DESC ", nativeQuery = true,
            countQuery = "select count(*) from (select news.id,news.title,news.content,news.posting_day as postingDay, " +
                    "news.img_url as imgUrl,news.app_user_id as appUser,news.status " +
                    " from news" +
                    " join app_user on app_user.id = news.app_user_id " +
                    " join employee on employee.id = app_user.employee_id " +
                    " where news.title like :searchName and news.content like :searchContent and (news.posting_day between :dateFirst and :dateLast) and news.`status` = 1" +
                    " order by news.posting_day DESC ) temp_table ")
    Page<INewsDto> findAllNews(Pageable pageable, @Param(value = "searchName") String searchName, @Param(value = "searchContent") String contentSearch, @Param(value = "dateFirst") String dateFirst, @Param(value = "dateLast") String dateLast);

    @Transactional
    @Query(value = "select news.id,news.title,news.content,news.posting_day,news.img_url,news.app_user_id,news.status" +
            " from news" +
            " join app_user on app_user.id = news.app_user_id" +
            " join employee on employee.id = app_user.employee_id" +
            " where news.id = :id ", nativeQuery = true)
    News getNewsById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update news set `status` = 0 where id = :id", nativeQuery = true)
    void deleteNews(@Param("id") Long idDelete);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `news` (`content`, `img_url`, `posting_day`," +
            " `status`, `title`, `app_user_id`) VALUES (:content,:imgUrl, :postingDay, 1 , " +
            " :title, 1) ",nativeQuery = true)
    void saveNews(@Param("content")String content, @Param("imgUrl")String imgUrl, @Param("postingDay") LocalDate postingDay,
                  @Param("title")String title);
}
