package se.kth.iv1350.util;

/**
 * An interface that will be inherited by the ErrorLogger class
 */
public interface Logger {
    void log(WarningLevel level, String msg, Exception e);




}
