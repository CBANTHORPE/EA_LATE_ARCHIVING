package com.codewithconnor.EA_Integration;

import com.codewithconnor.Database_Source.Database_Connection;
import com.codewithconnor.Database_Source.SchemaEntity_PurchaseOrders;
import de.easy.ebis.client.IDocument;

import java.io.*;

public class EA_SchemaMap {

    public static Database_Connection databaseConnection = new Database_Connection();

    public IDocument updateDoc (IDocument doc, File file) throws IOException {
        String fileName = file.getName();
        String[] fileNameSplit = fileName.split("\\.");

        String lookupValue;
        lookupValue = fileNameSplit[0];

        SchemaEntity_PurchaseOrders po = databaseConnection.findPurchaseOrderDetails(lookupValue);
//        SchemaEntity_PurchaseOrders po = databaseConnection.returnPurchaseOrderDetails(lookupValue);
//        if (po == null){
//            System.out.println("Error: No result found for PO Number: " + lookupValue);
//        } else {
            IDocument newDoc = doc;
            newDoc.addBlob("blobname", fileName, "application/pdf", fileToInputStream(file));
            System.out.println("Blob Added");
            newDoc.addItemValue("PurchaseOrderNumber", po.getPurchaseOrderNumber());
            System.out.println(" PO Added");
            newDoc.addItemValue("PurchaseOrderDate", po.getPurchaseOrderNumber());
            newDoc.addItemValue("SupplierAddress", po.getSupplierAddress());
            newDoc.addItemValue("SupplierNumber", po.getSupplierNumber());
            newDoc.addItemValue("SupplierPostCode", po.getSupplierPostCode());
            newDoc.addItemValue("TotalAmount", po.getTotalAmount());
            return newDoc;
//        }
//        return null;
    }

    public InputStream fileToInputStream(File file) throws IOException {
        try {
        FileInputStream fis = new FileInputStream(file);
        return fis;
        } catch (Exception e){
            return null;
        }
    }
}
