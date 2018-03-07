package com.globalblue.services.impl;

import com.globalblue.commons.Constants;
import com.globalblue.services.CSVService;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVServiceImpl implements CSVService {

    @Override
    public void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, Constants.DEFAULT_SEPARATOR, ' ');
    }

    @Override
    public void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    @Override
    public void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        if (separators == ' ') {
            separators = Constants.DEFAULT_SEPARATOR;
        }

        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }

        sb.append("\n");
        w.append(sb.toString());
    }

    //https://tools.ietf.org/html/rfc4180
    private String followCVSformat(String value) {

        String result = value;

        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }

        return result;
    }
}
