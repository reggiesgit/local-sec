package com.inter.challenge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 14/07/2021
 */

@Entity
@Table(name = "verifier")
public class VerifierDigit {

    @Id
    private String param;
    private int digit;

    public VerifierDigit(int digit, String param) {
        this.digit = digit;
        this.param = param;
    }

    public VerifierDigit() {
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerifierDigit that = (VerifierDigit) o;
        return getDigit() == that.getDigit() && getParam().equals(that.getParam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParam(), getDigit());
    }
}
