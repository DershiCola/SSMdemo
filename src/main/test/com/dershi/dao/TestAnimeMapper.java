package com.dershi.dao;

import com.dershi.pojo.Anime;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAnimeMapper {
    @Test
    public void testAddAnime() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnimeMapper mapper = context.getBean(AnimeMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("animeName", "Fate/Zero");
        mapper.addAnime(map);
    }

    @Test
    public void testGetAllAnimes() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnimeMapper mapper = context.getBean(AnimeMapper.class);
        List<Anime> allAnimes = mapper.getAllAnimes();
        for (Anime anime : allAnimes) {
            System.out.println(anime);
        }
    }
}
