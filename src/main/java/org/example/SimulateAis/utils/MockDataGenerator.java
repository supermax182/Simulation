package org.example.SimulateAis.utils;

import org.example.SimulateAis.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockDataGenerator {

    public List<CardAccountResponse> createMockCardAccounts(int count) {
        List<CardAccountResponse> mockAccounts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            CardAccountResponse accountResponse = new CardAccountResponse();

            // Create balances
            List<Balance> balances = new ArrayList<>();
            Balance balance = new Balance();
            BalanceAmount balanceAmount = new BalanceAmount();
            balanceAmount.setCurrency("EUR");
            balanceAmount.setAmount(String.valueOf(100 + i * 50)); // Varying amounts
            balance.setBalanceAmount(balanceAmount);
            balance.setBalanceType("closingBooked");
            balances.add(balance);
            accountResponse.setBalances(balances);

            // Create transactions
            CardTransactions transactions = new CardTransactions();

            List<CardTransaction> pendingTransactions = new ArrayList<>();
            CardTransaction pendingTransaction = new CardTransaction();
            pendingTransaction.setCardTransactionId("P" + (1000 + i));
            pendingTransaction.setTransactionDate("2025-02-18");
            pendingTransaction.setTransactionAmount(balanceAmount);
            pendingTransaction.setMaskedPAN("123456XXXXXX" + (1000 + i));
            pendingTransactions.add(pendingTransaction);
            transactions.setPending(pendingTransactions);

            List<CardTransaction> bookedTransactions = new ArrayList<>();
            CardTransaction bookedTransaction = new CardTransaction();
            bookedTransaction.setCardTransactionId("B" + (2000 + i));
            bookedTransaction.setTransactionDate("2025-02-15");
            bookedTransaction.setBookingDate("2025-02-16");
            bookedTransaction.setTransactionAmount(balanceAmount);
            bookedTransaction.setMaskedPAN("654321XXXXXX" + (2000 + i));
            bookedTransactions.add(bookedTransaction);
            transactions.setBooked(bookedTransactions);

            accountResponse.setCardTransactions(transactions);

            // Set card account details
            CardAccount cardAccount = new CardAccount();
            cardAccount.setMaskedPan("************" + (3240 + i));
            accountResponse.setCardAccount(cardAccount);

            mockAccounts.add(accountResponse);
        }

        return mockAccounts;
    }
}
