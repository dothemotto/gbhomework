package com.globalblue;

import com.globalblue.commons.Constants;
import com.globalblue.commons.Controller;
import com.globalblue.commons.ControllerFactory;
import com.globalblue.models.Invoice;
import com.globalblue.modules.AppBundleModule;
import com.globalblue.services.DataGenerationService;
import com.globalblue.services.impl.DataGenerationServiceImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashMap;


public class Application extends javafx.application.Application {

    private final ControllerFactory controllerFactory;

    public Application() {
        final Injector injector = Guice.createInjector(new AppBundleModule());
        controllerFactory = injector.getInstance(ControllerFactory.class);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        final Controller controller = controllerFactory.load(Constants.ROOT_VIEW);

        primaryStage.setTitle(Constants.APP_TITLE);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(Constants.IMAGES_FOLDER + Constants.ICON)));
        primaryStage.setScene(new Scene(controller.getView()));

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
