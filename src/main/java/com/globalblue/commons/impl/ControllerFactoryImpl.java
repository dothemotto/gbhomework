package com.globalblue.commons.impl;

import com.globalblue.commons.Constants;
import com.globalblue.commons.Controller;
import com.globalblue.commons.ControllerFactory;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ControllerFactoryImpl implements ControllerFactory {

    private final Injector injector;

    @Inject
    public ControllerFactoryImpl(final Injector injector) {
        this.injector = Objects.requireNonNull(injector, "Injector has not been initialized! Please check that!");
    }

    @Override
    public Controller load(String fxmlFile) throws IOException {

        Objects.requireNonNull(fxmlFile, "fxmlFile must not be null! Please check that!");

        final URL fxmlFileUrl = getClass().getResource(Constants.VIEW_FOLDER + fxmlFile);
        final FXMLLoader loader = new FXMLLoader(fxmlFileUrl);
        loader.setControllerFactory(injector::getInstance);

        final Parent view = loader.load();
        final Controller controller = loader.getController();
        controller.setView(view);

        return controller;
    }
}
