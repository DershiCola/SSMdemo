package com.dershi.pojo;

public class Anime {
    private Integer animeId;
    private String animeName;
    private String imgPath;
    private String animeImpression;

    public Anime() {
    }

    public Anime(String animeName, String imgPath, String animeImpression) {
        this.animeName = animeName;
        this.imgPath = imgPath;
        this.animeImpression = animeImpression;
    }

    public Integer getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Integer animeId) {
        this.animeId = animeId;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getAnimeImpression() {
        return animeImpression;
    }

    public void setAnimeImpression(String animeImpression) {
        this.animeImpression = animeImpression;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "animeId=" + animeId +
                ", animeName='" + animeName + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", animeImpressison='" + animeImpression + '\'' +
                '}';
    }
}
