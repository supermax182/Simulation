package org.example.SimulateAis.controllers;

import org.example.SimulateAis.models.CardAccount;
import org.example.SimulateAis.models.CardAccountResponse;
import org.example.SimulateAis.models.Consent;
import org.example.SimulateAis.models.ConsentStatus;
import org.example.SimulateAis.utils.ConsentStatusChanger;
import org.example.SimulateAis.utils.MockDataGenerator;
import org.example.SimulateAis.utils.SimpleInMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RestApiController {

    @Autowired
    private SimpleInMemoryStorage simpleInMemoryStorage;

    @Autowired
    private MockDataGenerator mockDataGenerator;

    @Autowired
    private ConsentStatusChanger consentStatusChanger;

    @PostMapping("/api/insertTestData")
    public void insertSomeAccountData() {
        List<CardAccountResponse> mockCardAccounts = mockDataGenerator.createMockCardAccounts(6);
        for (int i = 0; i < mockCardAccounts.size(); i++) {
            String consentId = "000000" + String.valueOf(i);
            CardAccountResponse cardAccountResponse = mockCardAccounts.get(i).setCardAccountId("ACC" + consentId);
            simpleInMemoryStorage.saveAccountResponse(consentId, cardAccountResponse);
            System.out.println("Inserting data for consent: " + consentId);
        }
    }

    @GetMapping("/api/v1/consents/{consent_id}/status")
    public ResponseEntity<ConsentStatus> getConsentStatus(@PathVariable String consent_id) {
        Consent consent = simpleInMemoryStorage.getConsent(consent_id);
        if (consent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(consent.getConsentStatus());
    }

    @GetMapping("/api/v1/consents/{consent_id}")
    public ResponseEntity<Consent> getConsent(@PathVariable String consent_id) {
        Consent consent = simpleInMemoryStorage.getConsent(consent_id);
        if (consent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(consent);
    }

    @PostMapping("/api/v1/consents/{consent_id}")
    public ResponseEntity<Consent> createConsent(@PathVariable String consent_id, @RequestBody Consent newConsent) {
        if (newConsent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        newConsent.setConsentStatus(new ConsentStatus(ConsentStatus.Statuses.received));
        newConsent.setConsentId(consent_id);
        simpleInMemoryStorage.saveConsent(consent_id, newConsent);
        consentStatusChanger.changeStatus(consent_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(newConsent);
    }

    @DeleteMapping("/api/v1/consents/{consentId}")
    public ResponseEntity<Void> deleteConsent(@PathVariable String consentId) {
        Consent consent = simpleInMemoryStorage.getConsent(consentId);
        if (consent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        simpleInMemoryStorage.removeConsent(consentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/v1/card-accounts/transactions")
    public ResponseEntity<CardAccountResponse> getAccountsAndTransactions(@RequestHeader(value = "Consent-Id") String consentId) {
        if (consentStatusIsValid(consentId)){
            CardAccountResponse cardAccountResponse = simpleInMemoryStorage.getAccountResponse(consentId);
            if(cardAccountResponse == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                return ResponseEntity.ok(cardAccountResponse);
            }
        } else {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/api/v1/card-accounts")
    public ResponseEntity<CardAccount> getAccountList(@RequestHeader(value = "Consent-Id") String consentId) {
        if (consentStatusIsValid(consentId)){
            CardAccount cardAccount = simpleInMemoryStorage.getAccountResponse(consentId).getCardAccount();
            if(cardAccount == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                return ResponseEntity.ok(cardAccount);
            }
        } else {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/debug/consent/{consentId}/{newStatus}")
    public void changeConsentStatus(@PathVariable String consentId, @PathVariable String newStatus){
        Consent consent = simpleInMemoryStorage.getConsent(consentId);
        consent.setConsentStatus(new ConsentStatus(ConsentStatus.Statuses.valueOf(newStatus)));
        simpleInMemoryStorage.saveConsent(consentId, consent);
    }

    // Global Exception Handling
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
    }

    //Validate Consent
    private boolean consentStatusIsValid(String consentId) {
        Consent consent = simpleInMemoryStorage.getConsent(consentId);
        if (consent == null) {
            return false;
        } else {
            return consent.getConsentStatus().getConsent_status().equals(ConsentStatus.Statuses.valid);
        }
    }
}
