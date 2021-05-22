package lt.vu.infrastructure.logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestScoped
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
        System.out.print(e.getMessage());
        e.printStackTrace();
    }

    private String getPrefix(LogLevel logLevel) {
        return this.format.format(new Date()) + " " + "[" + logLevel.name() + "] ";
    }
}
