package nz.vortus.domain.valueObjects;

// Email.java
public record Email(String value) {
    public Email {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private boolean isValid(String value) {
 return true;
    }
}


