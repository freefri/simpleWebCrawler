package es.freefri.crawler.io.reader;

import java.io.IOException;

public class InvalidFileFormat extends IOException {

    public InvalidFileFormat(String message) {
        super(message);
    }

    public InvalidFileFormat(Throwable cause) {
        super(cause);
    }
}
