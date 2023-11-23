package org.example;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

public class AlbumBuilder implements BuilderI<Album> {
    private String title;
    private LocalDate releaseDate;
    private Duration totalLength;
    private int totalSoldUnits;

    public AlbumBuilder title(String title) {
        this.title = title;
        return this;
    }

    public AlbumBuilder releaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public AlbumBuilder totalLength(Duration totalLength) {
        this.totalLength = totalLength;
        return this;
    }

    public AlbumBuilder totalSoldUnits(int totalSoldUnits) {
        this.totalSoldUnits = totalSoldUnits;
        return this;
    }

    @Override
    public Album build() {
        Album album = new Album(title, releaseDate, totalLength, totalSoldUnits);

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Album>> errors = validator.validate(album);

            if (!errors.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (ConstraintViolation<Album> val : errors) {
                    sb.append("InvalidValue: ").append(val.getInvalidValue()).append("; ")
                            .append(val.getMessage()).append("\n");
                }
                throw new IllegalArgumentException(sb.toString());
            }

        }

        return album;
    }
}
