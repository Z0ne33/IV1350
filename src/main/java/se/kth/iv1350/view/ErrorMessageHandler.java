package se.kth.iv1350.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ErrorMessageHandler {

    /**
     * Displays the specified error message.
     *
     * @param msg The error message.
     */
    void showErrorMsg( String msg ) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append(", TIME: ");
        errorMsgBuilder.append(createTime());
        errorMsgBuilder.append(", ERROR: ");
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder);
    }

    private String createTime() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);

    }

}
