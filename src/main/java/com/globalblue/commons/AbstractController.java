package com.globalblue.commons;

import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.Objects;

public abstract class AbstractController implements Controller {
    private Parent view;

    @Override
    public Parent getView() throws IllegalStateException {

        if (this.view == null) {
            throw new IllegalStateException("View has not been loaded! Please check that!");
        }

        return view;
    }

    @Override
    public void setView(Parent view) throws IllegalStateException {

        if (this.view != null) {
            throw new IllegalStateException("View has been already loaded! Please check that!");
        }

        this.view = Objects.requireNonNull(view, "View has not been instantiated! PLease check that!");
    }

    protected Stage getStage() {
        return (Stage) view.getScene().getWindow();
    }
}
