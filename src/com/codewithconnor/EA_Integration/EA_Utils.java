package com.codewithconnor.EA_Integration;

import de.easy.ebis.client.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class EA_Utils {
    public static final String EA_USERNAME ="superadmin";
    public static final String EA_PASSWORD = "super";
    public static final String EBIS_URL = "http://192.168.1.223:9090/";
    public static final String EBIS_INSTANCE = "Default";
    public static final String EA_POOL_NAME = "JAVA_View_Purchase_Orders";
    public static final String EA_SCHEMA_NAME = "JAVA_Purchase_Orders";
    public static EA_SchemaMap mapschema = new EA_SchemaMap();

    public static ISession session = generateSession();
    public static IRepository repo = initRepository(EA_POOL_NAME, session);
    public static IDocumentSchema schema = initSchema(EA_SCHEMA_NAME,session);


    public void syncDocuments() throws IOException {
        IDocument doc;
        String docid;
        File file = new File("statement.pdf");

        doc = session.createDocument(repo.getReference(), schema.getReference());
        try {
            mapschema.updateDoc(doc, file);
            doc.save(true, true, false);
            docid = doc.getID();
            System.out.println(docid);
        } catch (IOException | ClientException e) {
            session.logout();
            e.printStackTrace();
        }
    }

    public static ISession generateSession(){
        ISession session;
        IClientService service;
        IInstance instance;
        HashMap<String, String> connect = new HashMap<>();
        connect.put("UUID", UUID.randomUUID().toString());

        try {
            service = new DefaultClientService(EBIS_URL);
            instance = service.getInstance(EBIS_INSTANCE);
            session = instance.login(EA_USERNAME, EA_PASSWORD, connect);
            return session;
        } catch (ClientException e){
            e.getMessage();
            return null;
        }
    }

    public static IDocumentSchema initSchema (String schemaName, ISession session) {
        IDocumentSchema schema = null;
        try {
            schema = session.getDocumentSchema(schemaName);
        } catch (Exception e) {
            throw new ClientException("Unable to find EASY Schema: " + schemaName);
        } finally {
            session.logout();
        }

        if (schema == null){
            session.logout();
            throw new ClientException("EBIS connector could not find schema: " + schemaName);
        }
        return schema;
    }

    public static IRepository initRepository(String viewName, ISession session){
        IRepository view = null;
        try {
            view = session.getRepository(viewName);
        } catch (Exception e){
            session.logout();
            throw new ClientException("Unable to find EASY View: " +viewName);
        } finally {
            session.logout();
        }

        if (view == null){
            session.logout();
            throw new ClientException("EBIS connector could not connect with repository: " + viewName);
        }

        return view;
    }


}
