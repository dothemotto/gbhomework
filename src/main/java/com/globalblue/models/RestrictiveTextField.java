package com.globalblue.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.TimeUnit;

public class RestrictiveTextField extends TextField {

    private IntegerProperty maxLength = new SimpleIntegerProperty(this, "maxLength", -1);
    private StringProperty restrict = new SimpleStringProperty(this, "restrict");

    public RestrictiveTextField() {

        textProperty().addListener(new ChangeListener<String>() {

            private boolean ignore;

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                if (ignore || s1 == null)
                    return;
                if (maxLength.get() > -1 && s1.length() > maxLength.get()) {
                    ignore = true;
                    setText(s1.substring(0, maxLength.get()));
                    getErrorDialog().show();
                    ignore = false;
                }

                if (restrict.get() != null && !restrict.get().equals("") && !s1.matches(restrict.get() + "*")) {
                    ignore = true;
                    setText(s);
                    getErrorDialog().show();
                    ignore = false;
                }
            }
        });
    }

    /**
     * The max length property.
     *
     * @return The max length property.
     */
    public IntegerProperty maxLengthProperty() {
        return maxLength;
    }

    /**
     * Gets the max length of the text field.
     *
     * @return The max length.
     */
    public int getMaxLength() {
        return maxLength.get();
    }

    /**
     * Sets the max length of the text field.
     *
     * @param maxLength The max length.
     */
    public void setMaxLength(int maxLength) {
        this.maxLength.set(maxLength);
    }

    /**
     * The restrict property.
     *
     * @return The restrict property.
     */
    public StringProperty restrictProperty() {
        return restrict;
    }

    /**
     * Gets a regular expression character class which restricts the user input.

     *
     * @return The regular expression.
     * @see #getRestrict()
     */
    public String getRestrict() {
        return restrict.get();
    }

    /**
     * Sets a regular expression character class which restricts the user input.

     * E.g. [0-9] only allows numeric values.
     *
     * @param restrict The regular expression.
     */
    public void setRestrict(String restrict) {
        this.restrict.set(restrict);
    }

    private Stage getErrorDialog() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        Text txt = new Text("This is a Dialog");
        txt.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.getChildren().add(txt);
        Scene dialogScene = new Scene(dialogVbox, 350, 150);
        dialog.setScene(dialogScene);
        dialog.initStyle(StageStyle.UTILITY);

        return dialog;
    }
}