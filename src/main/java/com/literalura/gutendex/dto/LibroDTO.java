package com.literalura.gutendex.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {

    private String title;
    private List<String> languages;
    private int download_count;

    @JsonAlias("authors")
    private List<AutorDTO> autores;

    public String getTitle() {return title;}
    public List<String> getLanguages() {return languages;}
    public int getDownload_count() {return download_count;}
    public List<AutorDTO> getAutores() {return autores;}

    public LibroDTO() {}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    public void setAutores(List<AutorDTO> autores) {
        this.autores = autores;
    }
}
