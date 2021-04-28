package lt.vu.infrastructure.logger;

public interface Logger {

    void info(Exception e);

    void warning(Exception e);

    void error(Exception e);
}
