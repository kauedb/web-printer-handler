package br.com.kauedb.web_printer_handler;

/**
 * Created by Kaue
 */
class SimplePrintTemplate implements PrintTemplate {

    private final String personName;
    private final String company;
    private final String position;
    private final String supplier;
    private final String barcode;

    public SimplePrintTemplate(
            String personName,
            String company,
            String position,
            String supplier,
            String barcode) {
        this.personName = personName;
        this.company = company;
        this.position = position;
        this.supplier = supplier;
        this.barcode = barcode;
    }

    @Override
    public String build() {
        final StringBuilder builder = new StringBuilder("\u0002O0220\n")
                .append("\u0002M3000\n")
                .append("\u0002c0000\n")
                .append("\u0002f320\n")
                .append("\u0002e\n")
                .append("\u0002L\n")
                .append("A2\n")
                .append("C0000\n")
                .append("D11\n")
                .append("H13\n")
                .append("SC\n")
                .append("PC\n")
                .append("R0000\n")
                .append("z\n")
                .append("^01\n")
                .append("191200501300014" + personName + "\n")
                .append("191200501300015" + personName + "\n")
                .append("191200400950015" + company + "\n")
                .append("141100000700014" + position + "\n")
                .append("141100000100014\n")
                .append("1e4202000260240B8837738\n")
                .append("1200000001002408837738" + supplier + "\n")
                .append("Q0001\n")
                .append("E\n");

        return builder.toString();
    }
}
