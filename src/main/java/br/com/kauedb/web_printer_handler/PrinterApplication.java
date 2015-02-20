package br.com.kauedb.web_printer_handler;

import java.applet.Applet;

/**
 * Created by Kaue
 */
public class PrinterApplication extends Applet implements Application {

    @Override
    public void start() {
        sendPrintToArgox();
    }

    public void sendPrintToArgox() {

        final String personName = getParameter("personName");
        final String company = getParameter("company");
        final String position = getParameter("position");
        final String supplier = getParameter("supplier");

        sendPrintToArgox(personName, company, position, supplier);
    }

    public String getTest() {
        return "success";
    }

    public boolean sendPrintToArgox(final String personName, final String company, final String position, final String supplier) {

        try {
            final SimplePrintTemplate simplePrintTemplate = new SimplePrintTemplate(personName, company, position, supplier);

            final ArgoxPrinterHandler argoxPrinterHandler = new ArgoxPrinterHandler(simplePrintTemplate);

            argoxPrinterHandler.print();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
