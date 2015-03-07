package br.com.kauedb.web_printer_handler;

import javax.swing.*;

/**
 * Created by Kaue
 */
public class PrinterApplication extends JApplet implements Application {

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE - 1; i++) {
            System.out.println(i);
        }
    }

    public String getTest() {
        return "success";
    }

    public String sendPrintToArgox(final String personName
            , final String company
            , final String position
            , final String supplier
            , final String barcode) {

        if (StringHelper.isNotBlank(personName)
                && StringHelper.isNotBlank(company)
                && StringHelper.isNotBlank(position)
                && StringHelper.isNotBlank(supplier)
                && StringHelper.isNotBlank(barcode)
                ) {

            try {
                final SimplePrintTemplate simplePrintTemplate = new SimplePrintTemplate(personName, company, position, supplier, barcode);

                final ArgoxPrinterHandler argoxPrinterHandler = new ArgoxPrinterHandler(simplePrintTemplate);

                argoxPrinterHandler.print();
            } catch (Throwable e) {
                return e.getLocalizedMessage();
            }
            return "success";
        }

        return "fail";
    }

    public static class StringHelper {

        public static boolean isBlank(final String str) {
            return str == null || "".equals(str.trim());
        }

        public static boolean isNotBlank(final String str) {
            return !isBlank(str);
        }
    }

}
