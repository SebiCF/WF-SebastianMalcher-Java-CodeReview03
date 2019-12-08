package citybike;

import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

public class Rent {
    private int bikeID;
    private GregorianCalendar rentStart;
    private GregorianCalendar rentEnd;

    Rent(int bikeID) {
        this.bikeID = bikeID;
        this.rentStart = new GregorianCalendar();
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public GregorianCalendar getRentStart() {
        return rentStart;
    }

    public void setRentStart(GregorianCalendar rentStart) {
        this.rentStart = rentStart;
    }

    public GregorianCalendar getRentEnd() {
        return rentEnd;
    }

    public void setRentEnd(GregorianCalendar rentEnd) {
        this.rentEnd = rentEnd;
    }

    public String rentDurationToString() {
        return "\nRent Start: " + this.getRentStart().toZonedDateTime()
                .format(DateTimeFormatter.ofPattern("d MMM uuuu kk:mm:ss")) +
                "\nRent End: " + this.getRentEnd().toZonedDateTime()
                .format(DateTimeFormatter.ofPattern("d MMM uuuu kk:mm:ss"));
    }

    @Override
    public String toString() {
        return "Rent{" +
                "bikeID=" + bikeID +
                ", rentStart=" + rentStart +
                ", rentEnd=" + rentEnd +
                '}';
    }
}
