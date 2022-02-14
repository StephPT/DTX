package com.steph.dtx.database.entity.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankHolidays implements Serializable {

    @JsonProperty("england-and-wales")
    private BankHolidayDAO englandAndWales;

    private BankHolidayDAO scotland;

    @JsonProperty("northern-ireland")
    private BankHolidayDAO northernIreland;

    public void setEnglandAndWales(BankHolidayDAO englandAndWales) {
        this.englandAndWales = englandAndWales;
    }

    public BankHolidayDAO getEnglandAndWales() {
        return englandAndWales;
    }

    public void setScotland(BankHolidayDAO scotland) {
        this.scotland = scotland;
    }

    public BankHolidayDAO getScotland() {
        return scotland;
    }

    public void setNorthernIreland(BankHolidayDAO northernIreland) {
        this.northernIreland = northernIreland;
    }

    public BankHolidayDAO getNorthernIreland() {
        return northernIreland;
    }
}
