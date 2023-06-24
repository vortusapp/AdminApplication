package nz.vortus.domain.valueObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record PhoneNumber(String value) {
    private static String phoneNumber;

    private static final String ISO_FORMAT = "+64";
    private static final Pattern NZ_PHONE_PATTERN = Pattern.compile("^(?:\\+64|0)?([1-9]\\d{6,9})$");

    public  PhoneNumber {
        // Remove spaces, hyphens, and parentheses
        String cleanedValue = value.replaceAll("[\\s\\-()]", "");

        Matcher matcher = NZ_PHONE_PATTERN.matcher(cleanedValue);
        if (matcher.matches()) {
            phoneNumber = ISO_FORMAT + matcher.group(1);
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }
}
