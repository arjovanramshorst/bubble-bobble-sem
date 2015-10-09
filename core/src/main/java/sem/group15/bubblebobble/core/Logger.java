package sem.group15.bubblebobble.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * class that used for logging events
 * Created by arjo on 18-9-15.
 */
public final class Logger {

    private static HashMap<String, Logger> loggers = new HashMap<String, Logger>();
    private BufferedWriter writer;
    private String clazz;

    private Logger(String clazz) {
        this.clazz = clazz;
        try {
            File file = new File("output.log");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            writer = new BufferedWriter(fw);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Returns the logger instance.
     * @return the logger instance.
     */
    public static Logger getLogger(final String clazz) {
        if (loggers.get(clazz) == null) {
            loggers.put(clazz, new Logger(clazz));
        }
        return loggers.get(clazz);
    }

    /**
     * Logs the message to the output file, and also to the System.out.println stream.
     * @param message The message to be logged.
     */
    public void log(final String message) {
        String logLine = clazz + " - " + (new Date()).toString();
        logLine += " - " + message;
        try {
            writer.write(logLine);
            writer.newLine();
            writer.flush();
            System.out.println(logLine);
        } catch (IOException ioe) {
            System.out.println("Error trying to write to file.");
            ioe.printStackTrace();
        }
    }
}
