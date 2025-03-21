package org.example.SimulateAis.models;

public class ConsentStatus {

    public Statuses consent_status = Statuses.received;

    public enum Statuses{
        valid("valid"),
        invalid("invalid"),
        received("received"),
        revoked("revoked");

        public final String status;
        private Statuses (String status){
            this.status = status;
        }
    }

    public ConsentStatus(Statuses consent_status) {
        this.consent_status = consent_status;
    }

    public Statuses getConsent_status() {
        return consent_status;
    }

    public void setConsent_status(Statuses consent_status) {
        this.consent_status = consent_status;
    }
}
