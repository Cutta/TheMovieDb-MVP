package com.andcun.themoviedb_mvp.data.rest.model;

import java.util.List;

/**
 * Created by cuneytcarikci on 25/05/2017.
 */

public enum Genre {

    Action(28),
    Adventure(12),
    Animation(16),
    Comedy(35),
    Crime(80),
    Documentary(99),
    Drama(18),
    Family(10751),
    Fantasy(14),
    History(36),
    Horror(27),
    Music(10402),
    Mystery(9648),
    Romance(10749),
    ScienceFiction(878),
    TvMovie(10770),
    Thriller(53),
    War(10752),
    Western(37);

    private int id;

    Genre(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        switch (this) {
            case Action:
                return "Aksiyon";
            case Adventure:
                return "Macera";
            case Animation:
                return "Animasyon";
            case Comedy:
                return "Komedi";
            case Crime:
                return "Suç";
            case Documentary:
                return "Belgesel";
            case Drama:
                return "Dram";
            case Family:
                return "Aile";
            case Fantasy:
                return "Fantastik";
            case History:
                return "Tarih";
            case Horror:
                return "Korku";
            case Music:
                return "Müzikal";
            case Mystery:
                return "Gizem";
            case Romance:
                return "Romantik";
            case ScienceFiction:
                return "Bilim Kurgu";
            case TvMovie:
                return "TV Film";
            case Thriller:
                return "Gerilim";
            case War:
                return "Savaş";
            case Western:
                return "Vahşi Batı";
            default:
                return "";
        }
    }

    private static Genre getGenreById(int id) {
        for (Genre genre : values())
            if (genre.getId() == id)
                return genre;
        return Action;
    }

    public static String getGenresText(List<Integer> genreIds) {
        String result = "";
        for (Integer genreId : genreIds) {
            result += getGenreById(genreId).getName() + ", ";
        }
        if (result.length() >= 2)//boş geldiği oldu o yüzden bu şartı arıyoruz
            result = result.substring(0, result.length() - 2);
        return result;
    }
}
