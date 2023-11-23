package org.example;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.*;

public class Album{
    @Pattern(regexp = "[\\pL{U}][\\pL{l}]{0,24}( [\\pL{U}][\\pL{l}]{0,24})?", message = "Title can consist only of letters.")
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past(message = "Release Date should be in the past")
    @NotNull(message = "It should exist")
    private LocalDate releaseDate;

    @DurationFormat(message = "Invalid time format or out of range (10-200 minutes)")
    private Duration totalLength;

    @PositiveOrZero(message = "Total sold units should not be less than 0")
    private int totalSoldUnits;

    public Album(String title, LocalDate releaseDate, Duration totalLength, int totalSoldUnits) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.totalLength = totalLength;
        this.totalSoldUnits = totalSoldUnits;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateOfRelease() {
        return releaseDate;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.releaseDate = dateOfRelease;
    }

    public Duration getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Duration totalLength) {
        this.totalLength = totalLength;
    }

    public int getTotalSoldUnits() {
        return totalSoldUnits;
    }

    public void setTotalSoldUnits(int totalSoldUnits) {
        this.totalSoldUnits = totalSoldUnits;
    }

    @Override
    public String toString() {
        long minutes = totalLength.toMinutes();
        return "Title of album - " + title +
                "\n Date of release - " + releaseDate +
                "\n Album length - " + minutes + " minutes" +
                "\n Total sold units - " + totalSoldUnits;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Album album = (Album) object;
        return Objects.equals(title, album.title) &&
                Objects.equals(releaseDate, album.releaseDate) &&
                Objects.equals(totalLength, album.totalLength) &&
                totalSoldUnits == album.totalSoldUnits;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, totalLength, totalSoldUnits);
    }
}