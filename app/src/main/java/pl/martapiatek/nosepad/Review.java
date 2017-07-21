package pl.martapiatek.nosepad;

/**
 * Created by Marta on 21.07.2017.
 */

public class Review {

    private int id;
    private String brand;
    private String fragrance;
    private String[] notes;
    private String reviewDescription;
    private int rating;

    public Review(int id, String brand, String fragrance, String[] notes, String reviewDescription, int rating) {
        this.id = id;
        this.brand = brand;
        this.fragrance = fragrance;
        this.notes = notes;
        this.reviewDescription = reviewDescription;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getFragrance() {
        return fragrance;
    }

    public String[] getNotes() {
        return notes;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public int getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setFragrance(String fragrance) {
        this.fragrance = fragrance;
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
