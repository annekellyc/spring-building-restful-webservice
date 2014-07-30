package com.hello;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Greeting {
    Long id;
    String content;

    @Wither
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Builder {

        Long id;
        String content;

        public Builder() {
            this(null, null);
        }

        public Greeting build() {
            return new Greeting(id, content);
        }
    }
}
