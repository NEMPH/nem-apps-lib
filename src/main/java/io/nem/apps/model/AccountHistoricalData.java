package io.nem.apps.model;

import org.nem.core.model.Address;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.Deserializer;

/**
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public class AccountHistoricalData {

    private long height;

    private Address address;

    private Amount balance;

    private Amount vestedBalance;

    private Amount unvestedBalance;

    private double importance;

    private double pageRank;

    public AccountHistoricalData(Deserializer des) {
        height = des.readLong("height");
        address = Address.fromEncoded(des.readString("address"));
        balance = Amount.fromMicroNem(des.readLong("balance"));
        vestedBalance = Amount.fromMicroNem(des.readLong("vestedBalance"));
        unvestedBalance = Amount.fromMicroNem(des.readLong("unvestedBalance"));
        importance = des.readDouble("importance");
        pageRank = des.readDouble("pageRank");
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    public Amount getVestedBalance() {
        return vestedBalance;
    }

    public void setVestedBalance(Amount vestedBalance) {
        this.vestedBalance = vestedBalance;
    }

    public Amount getUnvestedBalance() {
        return unvestedBalance;
    }

    public void setUnvestedBalance(Amount unvestedBalance) {
        this.unvestedBalance = unvestedBalance;
    }

    public double getImportance() {
        return importance;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }

    public double getPageRank() {
        return pageRank;
    }

    public void setPageRank(double pageRank) {
        this.pageRank = pageRank;
    }
}

