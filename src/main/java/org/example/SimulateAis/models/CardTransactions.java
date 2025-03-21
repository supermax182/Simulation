package org.example.SimulateAis.models;

import java.util.List;

public class CardTransactions {
    private List<CardTransaction> pending;
    private List<CardTransaction> booked;
    private CardTransactionLinks _links;

    // Getters and Setters
    public List<CardTransaction> getPending() {
        return pending;
    }

    public void setPending(List<CardTransaction> pending) {
        this.pending = pending;
    }

    public List<CardTransaction> getBooked() {
        return booked;
    }

    public void setBooked(List<CardTransaction> booked) {
        this.booked = booked;
    }

    public CardTransactionLinks get_links() {
        return _links;
    }

    public void set_links(CardTransactionLinks _links) {
        this._links = _links;
    }
}

