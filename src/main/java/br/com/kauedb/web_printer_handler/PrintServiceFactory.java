package br.com.kauedb.web_printer_handler;

import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kaue
 */
public class PrintServiceFactory {


    private static final int INITIAL_CAPACITY = 5;
    private Map<String, PrintService> cache = new HashMap<>(INITIAL_CAPACITY);
    private static final PrintServiceFactory INSTANCE = new PrintServiceFactory();

    private PrintServiceFactory() {
        super();
    }

    public static PrintServiceFactory getInstance() {
        return INSTANCE;
    }

    public PrintService getPrintServiceByName(String name) {

        final PrintService ps;
        try {
            ps = (PrintService) ServiceManager.lookup("javax.jnlp.PrintService");
        } catch (UnavailableServiceException e) {
            throw new PrintServiceNotFoundException(e);
        }

        if (ps == null) {

            if (cache.get(name) == null) {
                final PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
                for (PrintService printService : printServices) {
                    if (printService.getName().toLowerCase().contains(name.toLowerCase())) {
                        cache.put(name, printService);
                        return printService;
                    }
                }

                throw new PrintServiceNotFoundException();
            } else {
                return cache.get(name);
            }
        }

        return ps;


    }


    public static class PrintServiceNotFoundException extends RuntimeException {

        public PrintServiceNotFoundException() {
            super();
        }

        public PrintServiceNotFoundException(Exception e) {
            super(e);
        }
    }

}
