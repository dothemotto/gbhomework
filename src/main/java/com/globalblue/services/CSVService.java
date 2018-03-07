package com.globalblue.services;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface CSVService {

    void writeLine(Writer w, List<String> values) throws IOException;

    void writeLine(Writer w, List<String> values, char separators) throws IOException;

    void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException;
}
