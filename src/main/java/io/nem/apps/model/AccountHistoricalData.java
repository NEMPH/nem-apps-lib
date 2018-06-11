package io.nem.apps.model;

import org.nem.core.model.Address;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.Deserializer;


/**
 * The Class AccountHistoricalData.
 *
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public class AccountHistoricalData {

    /** The height. */
    private long height;

    /** The address. */
    private Address address;

    /** The balance. */
    private Amount balance;

    /** The vested balance. */
    private Amount vestedBalance;

    /** The unvested balance. */
    private Amount unvestedBalance;

    /** The importance. */
    private double importance;

    /** The page rank. */
    private double pageRank;

    /**
     * Instantiates a new account historical data.
     *
     * @param des the des
     */
    public AccountHistoricalData(Deserializer des) {
        height = des.readLong("height");
        address = Address.fromEncoded(des.readString("address"));
        balance = Amount.fromMicroNem(des.readLong("balance"));
        vestedBalance = Amount.fromMicroNem(des.readLong("vestedBalance"));
        unvestedBalance = Amount.fromMicroNem(des.readLong("unvestedBalance"));
        importance = des.readDouble("importance");
        pageRank = des.readDouble("pageRank");
    }

    /**
     * Gets the height.
     *
     * @return the height
     */
    public long getHeight() {
        return height;
    }

    /**
     * Sets the height.
     *
     * @param height the new height
     */
    public void setHeight(long height) {
        this.height = height;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the new address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the balance.
     *
     * @return the balance
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Sets the balance.
     *
     * @param balance the new balance
     */
    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    /**
     * Gets the vested balance.
     *
     * @return the vested balance
     */
    public Amount getVestedBalance() {
        return vestedBalance;
    }

    /**
     * Sets the vested balance.
     *
     * @param vestedBalance the new vested balance
     */
    public void setVestedBalance(Amount vestedBalance) {
        this.vestedBalance = vestedBalance;
    }

    /**
     * Gets the unvested balance.
     *
     * @return the unvested balance
     */
    public Amount getUnvestedBalance() {
        return unvestedBalance;
    }

    /**
     * Sets the unvested balance.
     *
     * @param unvestedBalance the new unvested balance
     */
    public void setUnvestedBalance(Amount unvestedBalance) {
        this.unvestedBalance = unvestedBalance;
    }

    /**
     * Gets the importance.
     *
     * @return the importance
     */
    public double getImportance() {
        return importance;
    }

    /**
     * Sets the importance.
     *
     * @param importance the new importance
     */
    public void setImportance(double importance) {
        this.importance = importance;
    }

    /**
     * Gets the page rank.
     *
     * @return the page rank
     */
    public double getPageRank() {
        return pageRank;
    }

    /**
     * Sets the page rank.
     *
     * @param pageRank the new page rank
     */
    public void setPageRank(double pageRank) {
        this.pageRank = pageRank;
    }
}

