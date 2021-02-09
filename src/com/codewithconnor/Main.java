package com.codewithconnor;

import com.codewithconnor.EA_Integration.EA_Utils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        EA_Utils EA = new EA_Utils();

        EA.syncDocuments();

    }
}
