package com.dershi.controller;

import com.dershi.pojo.Anime;
import com.dershi.service.AnimeServiceImpl;
import com.dershi.utils.DefalutNum;
import com.dershi.utils.IdUtils;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/anime")
public class AnimeController {
    private final AnimeServiceImpl animeService;

    @Autowired
    public AnimeController(AnimeServiceImpl animeService) {
        this.animeService = animeService;
    }

    @RequestMapping("/page/{pageNo}")
    protected ModelAndView animeList(@PathVariable int pageNo, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String loginInfo = (String) session.getAttribute("loginInfo");
        if (loginInfo != null) {
            PageInfo<Anime> pageInfo = animeService.queryAllAnimes(pageNo, DefalutNum.DEFALUT_SIZE.getNum());
            if (pageNo > pageInfo.getPages()) {
                mv.setViewName("forward:/page/" + pageInfo.getPages());
                return mv;
            }
            mv.addObject("pageInfo", pageInfo);
            List<Anime> animes = pageInfo.getList();
            mv.addObject("animes", new Gson().toJson(animes));
            mv.setViewName("anime/show");
        } else {
            mv.setViewName("user/login");
        }
        return mv;
    }

    @RequestMapping("/edit")
    protected ModelAndView animeEdit(String pageNo) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageNo", pageNo);
        mv.setViewName("anime/edit");
        return mv;
    }

    @RequestMapping("/edit/{id}")
    protected ModelAndView animeEdit(@PathVariable int id, String pageNo) {
        ModelAndView mv = new ModelAndView();
        if (id > 0) {
            Anime anime = animeService.queryOneAnime(id);
            if (anime != null) {
                mv.addObject("animeId", anime.getAnimeId());
                mv.addObject("pageNo", pageNo);
                mv.addObject("animeName", anime.getAnimeName());
                mv.addObject("imgPath", anime.getImgPath());
                mv.addObject("animeImpression", anime.getAnimeImpression());
            }
        }
        mv.setViewName("anime/edit");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    protected String addAnime(String pageNo, String animeName, MultipartFile animeImgFile, String animeImpression, HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
        int add = 0;
        if (animeName != null) {
            if (animeImgFile != null)
                if (getImgPath(animeImgFile, session) != null) {
                    map = getImgPath(animeImgFile, session);
                    map.put("imgPath", map.get("filePath"));
                }
            map.put("animeName", animeName);
            map.put("animeImpression", animeImpression);
            add = animeService.addOneAnime(map);
        }
        if (add > 0) {
            if (map.get("realPath") != null)
                animeImgFile.transferTo(new File((String) map.get("realPath")));
            return "redirect:/anime/page/" + pageNo;
        } else {
            return "forward:/anime/edit";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    protected String deleteAnime(@PathVariable int id, int pageNo, HttpSession session) {
        int delete = animeService.deleteOneAnime(id);
        session.setAttribute("deleteMsg", delete);
        return "redirect:/anime/page/" + pageNo;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    protected String updateAnime(@PathVariable int id, String animeName, MultipartFile animeImgFile, String animeImpression, String pageNo, HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
        int update = 0;
        if (animeName != null) {
            if (animeImgFile != null)
                if (getImgPath(animeImgFile, session) != null) {
                    map = getImgPath(animeImgFile, session);
                    map.put("imgPath", map.get("filePath"));
                }
            map.put("animeName", animeName);
            map.put("animeImpression", animeImpression);
            update = animeService.updateOneAnime(id, map);
        }
        if (update > 0) {
            if (map.get("realPath") != null)
                animeImgFile.transferTo(new File((String) map.get("realPath")));
            return "redirect:/anime/page/" + pageNo;
        } else {
            return "forward:/anime/edit";
        }
    }

    protected Map<String, Object> getImgPath(MultipartFile bookImgFile, HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String oldName = bookImgFile.getOriginalFilename();
        String suffixName;
        if (!"".equals(oldName) && oldName != null)
            suffixName = oldName.substring(oldName.lastIndexOf("."));
        else
            return null;
        String fileName = IdUtils.generateId() + suffixName;
        String path = session.getServletContext().getRealPath("static/img/anime");
        File anime = new File(path);
        if (!anime.exists())
            anime.mkdir();
        map.put("realPath", path + File.separator + fileName);
        map.put("filePath", "/static/img/anime/" + fileName);
        return map;
    }
}
