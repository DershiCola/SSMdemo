package com.dershi.service;

import com.dershi.pojo.Anime;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface AnimeService {
    // 查询所有动漫
    PageInfo<Anime> queryAllAnimes(int pageNo, int pageSize);
    Anime queryOneAnime(int id);

    // 添加一部动漫的记录
    int addOneAnime(Map<String, Object> map);

    // 删除一部动漫的记录
    int deleteOneAnime(int animeId);

    // 更新一部动漫的内容,Map里需要设置要更新的内容
    int updateOneAnime(int animeId, Map<String, Object> map);
}
