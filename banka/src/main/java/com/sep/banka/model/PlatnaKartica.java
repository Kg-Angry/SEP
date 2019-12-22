package com.sep.banka.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class PlatnaKartica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlatneKaritice;

    private String pan;

    private String securityCode;

    private String cardHolderName;

    private String datumVazenja;

    private String merchantUsername;

    private BigDecimal amount;

    public PlatnaKartica() {
    }

    public Long getIdPlatneKaritice() {
        return idPlatneKaritice;
    }

    public void setIdPlatneKaritice(Long idPlatneKaritice) {
        this.idPlatneKaritice = idPlatneKaritice;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getDatumVazenja() {
        return datumVazenja;
    }

    public void setDatumVazenja(String datumVazenja) {
        this.datumVazenja = datumVazenja;
    }

    public String getMerchantUsername() {
        return merchantUsername;
    }

    public void setMerchantUsername(String merchantUsername) {
        this.merchantUsername = merchantUsername;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
