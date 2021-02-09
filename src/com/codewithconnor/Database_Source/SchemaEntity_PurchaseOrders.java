package com.codewithconnor.Database_Source;

public class SchemaEntity_PurchaseOrders {
    private String purchaseOrderNumber;
    private String purchaseOrderDate;
    private String supplierNumber;
    private String supplierAddress;
    private String supplierPostCode;
    private String totalAmount;

    public SchemaEntity_PurchaseOrders() {

    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(String purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierPostCode() {
        return supplierPostCode;
    }

    public void setSupplierPostCode(String supplierPostCode) {
        this.supplierPostCode = supplierPostCode;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
