package org.example.SimulateAis.models;

import java.util.List;

public class CardTransaction {
    private String cardTransactionId;
    private String terminalId;
    private String transactionDate;
    private String bookingDate;
    private String status;
    private BalanceAmount transactionAmount;
    private List<CurrencyExchange> currencyExchange;
    private BalanceAmount originalAmount;
    private BalanceAmount markupFee;
    private String markupFeePercentage;
    private String cardAcceptorId;
    private CardAcceptorAddress cardAcceptorAddress;
    private String merchantCategoryCode;
    private String maskedPAN;
    private String transactionDetails;
    private boolean invoiced;
    private String proprietaryBankTransactionCode;

    public String getCardTransactionId() {
        return cardTransactionId;
    }

    public void setCardTransactionId(String cardTransactionId) {
        this.cardTransactionId = cardTransactionId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BalanceAmount getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BalanceAmount transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public List<CurrencyExchange> getCurrencyExchange() {
        return currencyExchange;
    }

    public void setCurrencyExchange(List<CurrencyExchange> currencyExchange) {
        this.currencyExchange = currencyExchange;
    }

    public BalanceAmount getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BalanceAmount originalAmount) {
        this.originalAmount = originalAmount;
    }

    public BalanceAmount getMarkupFee() {
        return markupFee;
    }

    public void setMarkupFee(BalanceAmount markupFee) {
        this.markupFee = markupFee;
    }

    public String getMarkupFeePercentage() {
        return markupFeePercentage;
    }

    public void setMarkupFeePercentage(String markupFeePercentage) {
        this.markupFeePercentage = markupFeePercentage;
    }

    public String getCardAcceptorId() {
        return cardAcceptorId;
    }

    public void setCardAcceptorId(String cardAcceptorId) {
        this.cardAcceptorId = cardAcceptorId;
    }

    public CardAcceptorAddress getCardAcceptorAddress() {
        return cardAcceptorAddress;
    }

    public void setCardAcceptorAddress(CardAcceptorAddress cardAcceptorAddress) {
        this.cardAcceptorAddress = cardAcceptorAddress;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public String getMaskedPAN() {
        return maskedPAN;
    }

    public void setMaskedPAN(String maskedPAN) {
        this.maskedPAN = maskedPAN;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    public String getProprietaryBankTransactionCode() {
        return proprietaryBankTransactionCode;
    }

    public void setProprietaryBankTransactionCode(String proprietaryBankTransactionCode) {
        this.proprietaryBankTransactionCode = proprietaryBankTransactionCode;
    }
}

