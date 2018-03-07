package com.globalblue.commons;

import java.io.IOException;

public interface ControllerFactory {

    Controller load(String fxmlFile) throws IOException;
}
