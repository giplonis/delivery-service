package lt.vu.infrastructure.logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
@Default
public class ConsoleLogger implements Logger {

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void info(Exception e) {
        System.out.println(this.getPrefix(LogLevel.INFO) + e.toString());
    }

    @Override
    public void warning(Exception e) {
        System.out.println(this.getPrefix(LogLevel.WARNING) + e.toString());
    }

    @Override
    public void error(Exception e) {
        System.out.println(this.getPrefix(LogLevel.ERROR) + e.toString());
    }

    private String getPrefix(LogLevel logLevel) {
        return this.format.format(new Date()) + " " + "[" + logLevel.name() + "] ";
    }
}
