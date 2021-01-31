package com.sep.koncentratorPlacanja.dto;

import com.sep.koncentratorPlacanja.model.BankaSecret;

public class BankaSecretDTO {

    private Long id;
    private String casopisUsername;
    private String clientId;
    private String clientPassword;

    public BankaSecretDTO() {
    }
    public BankaSecretDTO(BankaSecret bankaSecret){
        this.id = bankaSecret.getId();
        this.casopisUsername = bankaSecret.getCasopisUsername();
        this.clientId = bankaSecret.getClientId();
        this.clientPassword = bankaSecret.getClientPassword();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCasopisUsername() {
        return casopisUsername;
    }

    public void setCasopisUsername(String casopisUsername) {
        this.casopisUsername = casopisUsername;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
