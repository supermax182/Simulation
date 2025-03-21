package org.example.SimulateAis.models;
import java.util.List;

public class CardAccountResponse {
    private List<Balance> balances;
    private CardTransactions cardTransactions;
    private CardAccount cardAccount;

    // Getters and Setters
    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public CardTransactions getCardTransactions() {
        return cardTransactions;
    }

    public void setCardTransactions(CardTransactions cardTransactions) {
        this.cardTransactions = cardTransactions;
    }

    public CardAccount getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(CardAccount cardAccount) {
        this.cardAccount = cardAccount;
    }

    public CardAccountResponse setCardAccountId(String cardAccountId){

        this.cardAccount.setAccountId(cardAccountId);
        return this;
    }
}
