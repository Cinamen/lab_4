import org.example.AlbumBuilder;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;

public class AlbumTest {

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*Title can consist only of letters.*")
    public void testInvalidTitle() {
        new AlbumBuilder().title("123").releaseDate(LocalDate.parse("2022-01-11"))
                .totalLength(Duration.ofMinutes(22)).totalSoldUnits(5).build();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*Release Date should be in the past.*")
    public void testFutureReleaseDate() {
        new AlbumBuilder().title("Valid Title").releaseDate(LocalDate.parse("2030-01-11"))
                .totalLength(Duration.ofMinutes(22)).totalSoldUnits(5).build();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*Invalid time format or out of range.*")
    public void testInvalidTotalLength() {
        new AlbumBuilder().title("Valid Title").releaseDate(LocalDate.parse("2022-01-11"))
                .totalLength(Duration.ofMinutes(5)).totalSoldUnits(5).build();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*Total sold units should not be less than 0.*")
    public void testNegativeTotalSoldUnits() {
        new AlbumBuilder().title("Valid Title").releaseDate(LocalDate.parse("2022-01-11"))
                .totalLength(Duration.ofMinutes(22)).totalSoldUnits(-2).build();
    }

}