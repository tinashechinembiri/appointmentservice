package com.example.Websitepractice.appointment.pogo;

public class MovieDetails {
    private String movieid;
    private String moviename;
    private String movieyear;
    private Actors stars;
    private Writers writers;
    private Directors directors;


    public String getMovieid() {
        return this.movieid;
    }

    public void setMovieid(final String movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return this.moviename;
    }

    public void setMoviename(final String moviename) {
        this.moviename = moviename;
    }

    public String getMovieyear() {
        return this.movieyear;
    }

    public void setMovieyear(final String movieyear) {
        this.movieyear = movieyear;
    }

    public Actors getStars() {
        return this.stars;
    }

    public void setStars(final Actors stars) {
        this.stars = stars;
    }

    public Writers getWriters() {
        return this.writers;
    }

    public void setWriters(final Writers writers) {
        this.writers = writers;
    }

    public Directors getDirectors() {
        return this.directors;
    }

    public void setDirectors(final Directors directors) {
        this.directors = directors;
    }
}
