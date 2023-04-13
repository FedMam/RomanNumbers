import org.romannumbers.RomanNumbers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RomanNumbersTest {
    @DataProvider(name = "valid-numbers-values")
    public Iterator<Object[]> validNumbersValues() throws IOException {
        List<Object[]> validNumbers;
        File validsFile = new File("src/test/java/validNumbers.txt");
        if (!validsFile.exists())
            throw new FileNotFoundException("File not found: src/test/java/validNumbers.txt");

        validNumbers = Files.readAllLines(Path.of(validsFile.getAbsolutePath()))
                .stream()
                .map(str -> {
                    String[] args = str.split(" ");
                    if (args.length != 2)
                        throw new InputMismatchException("Incorrect file format: src/test/java/validNumbers.txt");
                    return new Object[] { args[0], Integer.parseInt(args[1]) };
                }).toList();
        return validNumbers.listIterator();
    }

    @DataProvider(name = "valid-numbers")
    public Iterator<Object[]> validNumbers() throws IOException {
        List<Object[]> validNumbers = new ArrayList<>();
        Iterator<Object[]> numberValuePairs = validNumbersValues();
        while (numberValuePairs.hasNext()) {
            Object[] pair = numberValuePairs.next();
            validNumbers.add(new Object[]{pair[0]});
        }
        return validNumbers.listIterator();
    }

    @DataProvider(name = "invalid-numbers")
    public Iterator<Object[]> invalidNumbers() throws IOException {
        List<Object[]> invalidNumbers;
        File invalidsFile = new File("src/test/java/invalidNumbers.txt");
        if (!invalidsFile.exists())
            throw new FileNotFoundException("File not found: src/test/java/invalidNumbers.txt");

        invalidNumbers = Files.readAllLines(Path.of(invalidsFile.getAbsolutePath()))
                .stream()
                .map(str -> new Object[] { str })
                .toList();
        return invalidNumbers.listIterator();
    }

    @DataProvider(name = "throws-numbers")
    public Object[][] throwsNumbers() {
        return new Object[][] {
                new Object[] { "" },
                new Object[] { "MMMMMMMMDCCCLXXXVIII" },
                new Object[] { "QWERTYUIOPLKJHGFDSAZXCVBNM" },
                new Object[] { null }
        };
    }

    @DataProvider(name = "invalid-numbers-truncated")
    public Iterator<Object[]> invalidNumbersTruncated() throws IOException {
        List<Object[]> invalidNumbersTruncated = new ArrayList<>();
        Iterator<Object[]> invalidNumbersIterator = invalidNumbers();
        int truncatedSize = 10;

        for (int i = 0; i < truncatedSize && invalidNumbersIterator.hasNext(); i++)
            invalidNumbersTruncated.add(invalidNumbersIterator.next());

        return invalidNumbersTruncated.listIterator();
    }

    @Test(dataProvider = "valid-numbers")
    public void isValidTest(String number) {
        Assert.assertTrue(RomanNumbers.isValid(number));
    }

    @Test(dataProvider = "invalid-numbers")
    public void isInvalidTest(String number) {
        Assert.assertFalse(RomanNumbers.isValid(number));
    }

    @Test(dataProvider = "valid-numbers-values")
    public void convertTest(String number, int value) {
        Assert.assertEquals(RomanNumbers.convert(number), value);
    }

    @Test(dataProvider = "throws-numbers")
    public void throwsTest(String number) {
        Assert.assertThrows(() -> RomanNumbers.isValid(number));
    }

    @Test(dataProvider = "invalid-numbers-truncated")
    public void convertThrowsTest(String number) {
        Assert.assertThrows(() -> RomanNumbers.convert(number));
    }
}
