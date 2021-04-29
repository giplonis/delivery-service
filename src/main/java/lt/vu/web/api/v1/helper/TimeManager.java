package lt.vu.web.api.v1.helper;

import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@RequestScoped
public class TimeManager implements Serializable {

    Date dateNow = new Date();

    public boolean isExpired(@NotNull Date date,
                             int daysTill, int hoursTill,
                             int minutesTill, int secondsTill) {
        long tillInUnix = timeInMs(
                daysTill,
                hoursTill,
                minutesTill,
                secondsTill);
        return isExpired(date, tillInUnix);
    }

    public boolean isExpired(@NotNull Date date,
                             long unixTillExpire) {
        long givenUnix = date.getTime();
        Date expirationDate = new Date(givenUnix + unixTillExpire);
        return dateNow.after(expirationDate);
    }

    public long timeInMs(int daysTill,
                         int hoursTill,
                         int minutesTill,
                         int secondsTill) {
        long toReturn = (long)secondsTill * 1000;
        toReturn += (long)minutesTill*60*1000;
        toReturn += (long)hoursTill*60*60*1000;
        toReturn += (long)daysTill*24*60*60*1000;
        return toReturn;
    }
}
