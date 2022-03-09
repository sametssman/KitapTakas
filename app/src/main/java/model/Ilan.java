package model;


public class Ilan {
    public String kitapIsmi;

    public String yazarIsmi;

    public String optional;

    public String image;

    public Ilan(String kitapIsmi, String yazarIsmi, String optional, String image) {
        this.kitapIsmi = kitapIsmi;
        this.yazarIsmi = yazarIsmi;
        this.optional = optional;
        this.image = image;
    }
}
