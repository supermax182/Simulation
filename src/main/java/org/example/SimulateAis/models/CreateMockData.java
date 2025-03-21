package org.example.SimulateAis.models;

import java.time.LocalDate;

public class CreateMockData {

    private boolean recurringIndicator;
    private int frequencyPerDay;
    private LocalDate validUntil;
    private Access access;

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public int getFrequencyPerDay() {
        return frequencyPerDay;
    }

    public void setFrequencyPerDay(int frequencyPerDay) {
        this.frequencyPerDay = frequencyPerDay;
    }

    public boolean isRecurringIndicator() {
        return recurringIndicator;
    }

    public void setRecurringIndicator(boolean recurringIndicator) {
        this.recurringIndicator = recurringIndicator;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public static CreateMockData createCustomMockData(boolean recurringIndicator, int frequencyPerDay, LocalDate validUntil, String allPsd2){
        CreateMockData createMockData = new CreateMockData();
        createMockData.setRecurringIndicator(recurringIndicator);
        createMockData.setFrequencyPerDay(frequencyPerDay);
        createMockData.setValidUntil(validUntil);
        Access access = new Access();
        access.setAllPsd2(allPsd2);
        createMockData.setAccess(access);
        return createMockData;
    }

}
