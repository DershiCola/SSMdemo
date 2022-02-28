package com.dershi.service;

import com.dershi.dao.AnimeMapper;
import com.dershi.pojo.Anime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnimeServiceImpl implements AnimeService {

    private final AnimeMapper mapper;

    @Autowired
    public AnimeServiceImpl(AnimeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public PageInfo<Anime> queryAllAnimes(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Anime> animes = mapper.getAllAnimes();
        return new PageInfo<>(animes);
    }

    @Override
    public Anime queryOneAnime(int id) {
        return mapper.getAnimeById(id);
    }

    @Override
    public int addOneAnime(Map<String, Object> map) {
        return mapper.addAnime(map);
    }

    @Override
    public int deleteOneAnime(int animeId) {
        return mapper.deleteAnimeById(animeId);
    }

    @Override
    public int updateOneAnime(int animeId, Map<String, Object> map) {
        map.put("animeId", animeId);
        return mapper.updateAnimeById(map);
    }
}
