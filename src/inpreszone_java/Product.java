/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Product.java
 *
 * Created on 10 oct. 2011, 15:53:11
 */

package inpreszone_java;

import java.util.List;
import org.ektorp.support.CouchDbDocument;

/**
 *
 * @author Sh1fT
 */

public class Product extends CouchDbDocument {
    protected String _id;
    // common
    protected String title;
    protected String category;
    protected String category_amazon;
    protected String asin;
    protected String endpoint;
    protected Double price;
    protected Double rank;
    protected Double review;
    protected Double weight;
    protected Double dim_width;
    protected Double dim_height;
    protected Double dim_depth;
    // book
    protected List<String> authors;
    protected String publisher;
    protected String format;
    protected String language;
    protected Integer pages;
    protected String date;
    protected String edition;
    // movie
    protected List<String> actors;
    protected List<String> directors;
    protected String studio;
    protected List<String> formats;
    protected Integer discs;
    protected String rating;
    protected List<String> languages;
    protected List<String> subtitles;
    protected String runtime;
    protected String release_date;
    protected Integer region;
    // cd
    protected List<String> artist;
    protected String label;
    //private int discs;
    //private String release_date;
    // ?
    protected String fournisseur;

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    // common

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_amazon() {
        return this.category_amazon;
    }

    public void setCategory_amazon(String category_amazon) {
        this.category_amazon = category_amazon;
    }

    public String getAsin() {
        return this.asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRank() {
        return this.rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Double getReview() {
        return this.review;
    }

    public void setReview(Double review) {
        this.review = review;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

     public Double getDim_width() {
        return this.dim_width;
    }

    public void setDim_width(Double dim_width) {
        this.dim_width = dim_width;
    }

    public Double getDim_height() {
        return this.dim_height;
    }

    public void setDim_height(Double dim_height) {
        this.dim_height = dim_height;
    }

    public Double getDim_depth() {
        return this.dim_depth;
    }

    public void setDim_depth(Double dim_depth) {
        this.dim_depth = dim_depth;
    }

    // book

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPages() {
        return this.pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEdition() {
        return this.edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    // movie

    public List<String> getActors() {
        return this.actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getDirectors() {
        return this.directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public String getStudio() {
        return this.studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public List<String> getFormats() {
        return this.formats;
    }

    public void setFormats(List<String> formats) {
        this.formats = formats;
    }

    public Integer getDiscs() {
        return this.discs;
    }

    public void setDiscs(Integer discs) {
        this.discs = discs;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<String> getLanguages() {
        return this.languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSubtitles() {
        return this.subtitles;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }

    public String getRuntime() {
        return this.runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRelease_date() {
        return this.release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Integer getRegion() {
        return this.region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    // cd

    public List<String> getArtist() {
        return this.artist;
    }

    public void setArtist(List<String> artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    // ?

    public String getFournisseur() {
        return this.fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this._id != null ? this._id.hashCode() : 0);
        hash = 31 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 31 * hash + (this.category != null ? this.category.hashCode() : 0);
        hash = 31 * hash + (this.category_amazon != null ? this.category_amazon.hashCode() : 0);
        hash = 31 * hash + (this.asin != null ? this.asin.hashCode() : 0);
        hash = 31 * hash + (this.endpoint != null ? this.endpoint.hashCode() : 0);
        hash = 31 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 31 * hash + (this.rank != null ? this.rank.hashCode() : 0);
        hash = 31 * hash + (this.review != null ? this.review.hashCode() : 0);
        hash = 31 * hash + (this.weight != null ? this.weight.hashCode() : 0);
        hash = 31 * hash + (this.dim_width != null ? this.dim_width.hashCode() : 0);
        hash = 31 * hash + (this.dim_height != null ? this.dim_height.hashCode() : 0);
        hash = 31 * hash + (this.dim_depth != null ? this.dim_depth.hashCode() : 0);
        hash = 31 * hash + (this.authors != null ? this.authors.hashCode() : 0);
        hash = 31 * hash + (this.publisher != null ? this.publisher.hashCode() : 0);
        hash = 31 * hash + (this.format != null ? this.format.hashCode() : 0);
        hash = 31 * hash + (this.language != null ? this.language.hashCode() : 0);
        hash = 31 * hash + (this.pages != null ? this.pages.hashCode() : 0);
        hash = 31 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 31 * hash + (this.edition != null ? this.edition.hashCode() : 0);
        hash = 31 * hash + (this.actors != null ? this.actors.hashCode() : 0);
        hash = 31 * hash + (this.directors != null ? this.directors.hashCode() : 0);
        hash = 31 * hash + (this.studio != null ? this.studio.hashCode() : 0);
        hash = 31 * hash + (this.formats != null ? this.formats.hashCode() : 0);
        hash = 31 * hash + (this.discs != null ? this.discs.hashCode() : 0);
        hash = 31 * hash + (this.rating != null ? this.rating.hashCode() : 0);
        hash = 31 * hash + (this.languages != null ? this.languages.hashCode() : 0);
        hash = 31 * hash + (this.subtitles != null ? this.subtitles.hashCode() : 0);
        hash = 31 * hash + (this.runtime != null ? this.runtime.hashCode() : 0);
        hash = 31 * hash + (this.release_date != null ? this.release_date.hashCode() : 0);
        hash = 31 * hash + (this.region != null ? this.region.hashCode() : 0);
        hash = 31 * hash + (this.artist != null ? this.artist.hashCode() : 0);
        hash = 31 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 31 * hash + (this.fournisseur != null ? this.fournisseur.hashCode() : 0);
        return hash;
    }

    @Override public boolean equals(Object other) {
        if (!(other instanceof Product))
            return false;
        Product castOther = (Product) other;
        if (this.getAsin().compareTo(castOther.getAsin()) == 0)
            return true;
        else
            return false;
    }
}