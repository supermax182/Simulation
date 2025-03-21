package org.example.SimulateAis.models;

import java.time.LocalDate;

public class Consent {
    private String consentId;
    private boolean recurringIndicator;
    private ConsentStatus consentStatus;
    private LocalDate validUntil;
    private int frequencyPerDay;
    private boolean combinedServiceIndicator;
    private Access access;

    public Consent(String consentId) {
        this.consentId = consentId;
    }

    public Consent() {
    }

    // Getters and Setters
    public String getConsentId() {
        return consentId;
    }

    public void setConsentId(String consentId) {
        this.consentId = consentId;
    }

    public boolean isRecurringIndicator() {
        return recurringIndicator;
    }

    public void setRecurringIndicator(boolean recurringIndicator) {
        this.recurringIndicator = recurringIndicator;
    }

    public ConsentStatus getConsentStatus() {
        return consentStatus;
    }

    public void setConsentStatus(ConsentStatus consentStatus) {
        this.consentStatus = consentStatus;
    }

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

    public boolean isCombinedServiceIndicator() {
        return combinedServiceIndicator;
    }

    public void setCombinedServiceIndicator(boolean combinedServiceIndicator) {
        this.combinedServiceIndicator = combinedServiceIndicator;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public static Consent createCustomConsent(String consentId, boolean recurringIndicator, LocalDate validUntil, int frequencyPerDay, String allPsd2){
        if (consentId == null || validUntil == null || allPsd2 == null) {
            throw new IllegalArgumentException("Consent fields cannot be null!");
        }
        Consent consent = new Consent();
        consent.setConsentId(consentId);
        consent.setRecurringIndicator(recurringIndicator);
        consent.setValidUntil(validUntil);
        consent.setFrequencyPerDay(frequencyPerDay);
        Access access = new Access();
        access.setAllPsd2(allPsd2);
        consent.setAccess(access);

        return consent;
    }


}

