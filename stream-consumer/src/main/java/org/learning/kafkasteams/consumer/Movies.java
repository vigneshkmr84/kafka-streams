package org.learning.kafkasteams.consumer;

public class Movies {

    int imdbID;
    String title;
    int year;
    String runTime;
    String genre;
    String releasedYear;

    String director;
    double imdbRating;
    String awards;
    String cast;


    public Movies(int imdbID, String title, int year, String runTime, String genre, String releasedYear, String director, double imdbRating, String awards, String cast) {
        this.imdbID = imdbID;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
        this.genre = genre;
        this.releasedYear = releasedYear;
        this.director = director;
        this.imdbRating = imdbRating;
        this.awards = awards;
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "Movies Dump Data - {" +
                "imdbID=" + imdbID +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", runTime='" + runTime + '\'' +
                ", genre='" + genre + '\'' +
                ", releasedYear='" + releasedYear + '\'' +
                ", director='" + director + '\'' +
                ", imdbRating=" + imdbRating +
                ", awards='" + awards + '\'' +
                ", cast='" + cast + '\'' +
                '}';
    }

    public int getImdbID() {
        return imdbID;
    }

    public void setImdbID(int imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
