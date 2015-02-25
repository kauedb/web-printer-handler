package br.com.kauedb.web_printer_handler;

import javax.swing.*;

/**
 * Created by Kaue
 */
public class PrinterApplication extends JApplet implements Application {

    public String getTest() {
        return "success";
    }

    public boolean sendPrintToArgox(final String personName, final String company, final String position, final String supplier, final String barcode) {

        if (StringHelper.isNotBlank(personName)
                && StringHelper.isNotBlank(company)
                && StringHelper.isNotBlank(position)
                && StringHelper.isNotBlank(supplier)) {


            try {
                final SimplePrintTemplate simplePrintTemplate = new SimplePrintTemplate(personName, company, position, supplier, barcode);

                final ArgoxPrinterHandler argoxPrinterHandler = new ArgoxPrinterHandler(simplePrintTemplate);

                argoxPrinterHandler.print();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        return false;
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
