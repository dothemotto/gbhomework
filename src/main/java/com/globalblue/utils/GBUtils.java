package com.globalblue.utils;

import com.globalblue.commons.Constants;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.lang3.StringUtils;

public class GBUtils {

    public static Stage errorDialog(String errorText) {
        return dialog(errorText, true);
    }

    public static Stage infoDialog(String infoText) {
        return dialog(infoText, false);
    }

    public static String generateFilename(TextField shopNumber, DatePicker dateFrom, DatePicker dateTo) {
        StringBuilder sb = new StringBuilder();

        if (shopNumber == null
                || (dateFrom == null || dateFrom.getValue() == null)
                || (dateTo == null || dateTo.getValue() == null)) {
            return StringUtils.EMPTY;
        }

        sb.append(Constants.FILENAME_PREFIX)
                .append(shopNumber.getText())
                .append(Constants.UNDERSCORE)
                .append(dateFrom.getValue().toString())
                .append(Constants.UNDERSCORE)
                .append(dateTo.getValue().toString())
                .append(Constants.CSV_EXTENSION);

        return sb.toString();
    }


    private static Stage dialog(String text, boolean isError) {
        final Stage dialog = new Stage();

        VBox dialogVbox = new VBox(20);
        Text txt = new Text(text);
        txt.setFont(Font.font("Verdana", 14));
        if (isError) {
            txt.setFill(Color.RED);
        } else {
            txt.setFill(Color.GREEN);
        }
        txt.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.getChildren().add(txt);
        dialogVbox.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(dialogVbox, 350, 150);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(dialogScene);
        dialog.initStyle(StageStyle.UNDECORATED);

        dialogScene.setOnKeyReleased((event) -> {
            if (event.getCode().equals(KeyCode.ESCAPE) || event.getCode().equals(KeyCode.ENTER)) {
                dialog.close();
            }
        });

        return dialog;
    }
}
