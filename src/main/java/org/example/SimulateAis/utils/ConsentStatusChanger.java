package org.example.SimulateAis.utils;

import org.example.SimulateAis.models.Consent;
import org.example.SimulateAis.models.ConsentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ConsentStatusChanger {

    @Autowired
    private SimpleInMemoryStorage simpleInMemoryStorage;

    private Random randomizer = new Random();

    public void changeStatus(String consentId){
        Consent consent = simpleInMemoryStorage.getConsent(consentId);
        int numberOfAccounts = simpleInMemoryStorage.getNumberOfAccounts();
        int numberOfConsents = simpleInMemoryStorage.getNumberOfConsents();
        if(simpleInMemoryStorage.getAccountResponse(consentId) == null){
            consent.setConsentStatus(new ConsentStatus(ConsentStatus.Statuses.invalid));
        }  else {
            if(numberOfAccounts == 1){
                consent.setConsentStatus(new ConsentStatus(ConsentStatus.Statuses.valid));
            } else {
                if (numberOfConsents == 0 ){
                    consent.setConsentStatus(new ConsentStatus(ConsentStatus.Statuses.valid));
                } else {
                    int variant = randomizer.nextInt(1,3);
                    switch (variant){
                        case 1:
                            consent.setConsentStatus(new ConsentStatus(ConsentStatus.Statuses.valid));
                            break;
                        case 2:
                            consent.setConsentStatus(new ConsentStatus(ConsentStatus.Statuses.invalid));
                            break;
                        case 3:
                            consent.setConsentStatus(new ConsentStatus(ConsentStatus.Statuses.revoked));
                            break;
                        default:
                            break;
                    }
                }
            }
        }

    }
}
