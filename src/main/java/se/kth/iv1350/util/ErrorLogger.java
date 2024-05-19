package se.kth.iv1350.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ErrorLogger implements Logger {
    private String fileName;
    private PrintWriter logger;
    private FileWriter fileWriter;

    /**
     * Is used to Construct the ErrorLogger class
     */
    public ErrorLogger(){
        setFileName(this.fileName);
        try{
            fileWriter = new FileWriter(this.fileName);
            logger = new PrintWriter(fileWriter, true);
        }
        catch (IOException e){
            System.out.println("FAILED TO LOG.");
            e.printStackTrace();
        }
    }

    private void setFileName( String fileName ) {
        this.fileName = "ErrorLogger.log";
    }

    /**
     * Construct the string and logs it in file ErrorLogger.log
     *
     * @param level The observer to notify.
     * @param msg is the message that will be logged
     * @param e is the exception that was thrown
     */
    @Override
    public void log(WarningLevel level, String msg, Exception e){

        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append(createTime());
        errorMsgBuilder.append(" " +level + ": ");
        errorMsgBuilder.append(", ERROR OCCURED: ");
        errorMsgBuilder.append(e.getMessage());
        errorMsgBuilder.append(msg);
        logger.println(errorMsgBuilder);
        e.printStackTrace(logger);
    }
    private String createTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
    /**
     * Handles runtimeExceptions
     *
     * @param level The observer to notify.
     * @param msg is the message that will be logged
     * @param e is the exception that was thrown
     */

    public void log(WarningLevel level, String msg, RuntimeException e) {

        log(level, msg, (Exception) e);
    }



}
