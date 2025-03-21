package org.example.SimulateAis.utils;

import org.example.SimulateAis.models.CardAccountResponse;
import org.example.SimulateAis.models.Consent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SimpleInMemoryStorage {

    private Map<String, Consent> consentStorage = new HashMap<>();
    private Map<String, CardAccountResponse> responseMap = new HashMap();

    public void saveConsent(String consent_id, Consent consent){
        consentStorage.put(consent_id, consent);
    }

    public Consent getConsent(String consentId){
        return consentStorage.get(consentId);
    }

    public void removeConsent(String consentId){
        consentStorage.remove(consentId);
    }

    public int getNumberOfConsents (){ return consentStorage.size(); }

    public void saveAccountResponse(String consent_id, CardAccountResponse account){
        responseMap.put(consent_id, account);
    }

    public CardAccountResponse getAccountResponse(String consentId){
        return responseMap.get(consentId);
    }

    public void removeAccountResponse(String consentId){
        responseMap.remove(consentId);
    }

    public int getNumberOfAccounts(){
        return responseMap.size();
    }
}
