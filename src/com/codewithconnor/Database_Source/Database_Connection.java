package com.codewithconnor.Database_Source;

import java.sql.*;
import java.util.ArrayList;

public class Database_Connection {
    /*
    Create DB
    Create Table for Purchase Orders
    Create Table for DOC IDs to be returned
    Create Search for Purchase Order lookups
    Return SchemaEntity Object back to caller

    TO DO
    Insert DocID/SupplierNo into Doc_ID table
     */

    public static final String DB_NAME = "Purchase_Orders.db";
    public static final String DB_PATH = "jdbc:sqlite:E:\\EASY\\SCRIPTING\\GitHub\\EA_LATE_ARCHIVING\\src\\db\\" + DB_NAME;
    public static final String TABLE_PURCHASE_ORDERS = "purchase_orders";
    public static final String TABLE_DOC_ID = "doc_id";
    public static final String COLUMN_PURCHASE_ORDERS_PURCHASE_ORDER_NUMBER = "PurchaseOrder";
    public static final String COLUMN_PURCHASE_ORDERS_PURCHASE_ORDER_DATE = "PurchaseOrderDate";
    public static final String COLUMN_PURCHASE_ORDERS_SUPPLIER_NUMBER = "SupplierNumber";
    public static final String COLUMN_PURCHASE_ORDERS_SUPPLIER_ADDRESS = "SupplierAddress";
    public static final String COLUMN_PURCHASE_ORDERS_SUPPLIER_POSTCODE = "SupplierPostCode";
    public static final String COLUMN_PURCHASE_ORDERS_TOTAL_AMOUNT = "TotalAmount";

    public static final String COLUMN_DOC_ID = "DocID";
    public static final String COLUMN_SupplierNo = "SupplierNumber";

    public static final String CREATE_TABLE_PURCHASE_ORDERS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PURCHASE_ORDERS +
                    "(" + COLUMN_PURCHASE_ORDERS_PURCHASE_ORDER_NUMBER + " text, "
                    + COLUMN_PURCHASE_ORDERS_PURCHASE_ORDER_DATE + " text, "
                    + COLUMN_PURCHASE_ORDERS_SUPPLIER_NUMBER + " text, "
                    + COLUMN_PURCHASE_ORDERS_SUPPLIER_ADDRESS + " text, "
                    + COLUMN_PURCHASE_ORDERS_SUPPLIER_POSTCODE + " text, "
                    + COLUMN_PURCHASE_ORDERS_TOTAL_AMOUNT + " text)";

    public static final String CREATE_TABLE_DOC_ID =
            "CREATE TABLE IF NOT EXISTS " + TABLE_DOC_ID +
                    "(" + COLUMN_DOC_ID + " text, "
                    + COLUMN_SupplierNo + " text)";

    public static final String LOOKUP_PURCHASE_ORDERS = "SELECT " + COLUMN_PURCHASE_ORDERS_PURCHASE_ORDER_NUMBER
            + ", " + COLUMN_PURCHASE_ORDERS_PURCHASE_ORDER_DATE
            + ", " + COLUMN_PURCHASE_ORDERS_SUPPLIER_NUMBER
            + ", " + COLUMN_PURCHASE_ORDERS_SUPPLIER_ADDRESS
            + ", " + COLUMN_PURCHASE_ORDERS_SUPPLIER_POSTCODE
            + ", " + COLUMN_PURCHASE_ORDERS_TOTAL_AMOUNT
            + " FROM " + TABLE_PURCHASE_ORDERS
            + " WHERE " + COLUMN_PURCHASE_ORDERS_PURCHASE_ORDER_NUMBER + " = ?";

    public static final String INSERT_DOC_ID = "";

    PreparedStatement createPurchaseOrder_Table;
    PreparedStatement createDocID_Table;
    PreparedStatement LookupIntoPurchase_Orders;

    private Connection conn;

    public void openConnection (){
        try {
            conn = DriverManager.getConnection(DB_PATH);
            createPurchaseOrder_Table = conn.prepareStatement(CREATE_TABLE_PURCHASE_ORDERS);
            createDocID_Table = conn.prepareStatement(CREATE_TABLE_DOC_ID);
            createPurchaseOrder_Table.execute();
            createDocID_Table.execute();

            LookupIntoPurchase_Orders = conn.prepareStatement(LOOKUP_PURCHASE_ORDERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SchemaEntity_PurchaseOrders returnPurchaseOrderDetails(String purchaseOrderNumber){
        SchemaEntity_PurchaseOrders poDetails = new SchemaEntity_PurchaseOrders();
        poDetails.setPurchaseOrderNumber("Order Number");
        poDetails.setPurchaseOrderDate("Order Date");
        poDetails.setSupplierNumber("Supplier Number");
        poDetails.setSupplierAddress("EASY Software");
        poDetails.setSupplierPostCode("IP333TA");
        poDetails.setTotalAmount("5000");
        return poDetails;
    }

    public SchemaEntity_PurchaseOrders findPurchaseOrderDetails(String purchaseOrderNumber){
        try {
            openConnection();
            LookupIntoPurchase_Orders.setString(1, purchaseOrderNumber);
            System.out.println(LookupIntoPurchase_Orders);
            System.out.println(purchaseOrderNumber);
            ResultSet results = LookupIntoPurchase_Orders.executeQuery();
            SchemaEntity_PurchaseOrders poDetails = new SchemaEntity_PurchaseOrders();
            if (results.next()){
                poDetails.setPurchaseOrderNumber(results.getString(1));
                poDetails.setPurchaseOrderDate(results.getString(2));
                poDetails.setSupplierNumber(results.getString(3));
                poDetails.setSupplierAddress(results.getString(4));
                poDetails.setSupplierPostCode(results.getString(5));
                poDetails.setTotalAmount(results.getString(6));
            } else {
                System.out.println("Error: no results found");
                return null;
            }

            return poDetails;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void uploadDocID(String docID, String SupplierNo){

    }
}
