package com.dershi.dao;

import com.dershi.pojo.Anime;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AnimeMapper {
    List<Anime> getAllAnimes();
    Anime getAnimeById(@Param("animeId") int id);
    int addAnime(Map<String, Object> map);
    int deleteAnimeById(@Param("animeId") int id);
    int updateAnimeById(Map<String, Object> map);
}
