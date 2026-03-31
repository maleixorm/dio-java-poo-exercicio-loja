public non-sealed class USClock extends Clock {
    private String periodIndicator;

    public String getPeriodIndicator() {
        return periodIndicator;
    }

    public void setPeriodIndicator(String periodIndicator) {
        this.periodIndicator = periodIndicator;
    }

    public void setAfterMidday() {
        this.periodIndicator = "PM";
    }

    public void setBeforeMidday() {
        this.periodIndicator = "AM";
    }

    public void setHour(int hour) {
        setBeforeMidday();
        if ((hour > 12) && (hour <=23)) {
            setAfterMidday();
            this.hour = hour > 23 ? 0 : hour - 12;
        }
        else if (hour >= 24) {
            this.hour = 0;
        } 
        else {
            this.hour = hour;
        }
    }

    @Override
    public Clock convert(Clock clock) {
        this.second = clock.getSecond();
        this.minute = clock.getMinute();
        switch (clock){
            case USClock usclock -> {
                this.hour = usclock.getHour();
                this.periodIndicator = usclock.getPeriodIndicator();
            }
            case BRLClock brlClock -> this.setHour(brlClock.getHour());
        }
        return this;
    }

    @Override 
    public String getTime() {
        return super.getTime() + " " + this.periodIndicator;
    }
}
