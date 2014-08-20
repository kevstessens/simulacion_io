package model;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.List;


public class ExcelGenerator {


    public static void generateExcel(String fileName, List<Event> events) {
        try {

            File exlFile = new File("C:\\"+fileName + ".xls");
            WritableWorkbook book = Workbook.createWorkbook(exlFile);
            WritableSheet sheet = book.createSheet("Tabla", 0);

            WritableCellFormat centerAlignment = new WritableCellFormat();
            centerAlignment.setAlignment(Alignment.CENTRE);

            sheet.mergeCells(1, 0, 3, 0);
            sheet.addCell(new Label(1, 0, "ARRIBOS", centerAlignment));
            sheet.addCell(new Label(4, 0, "COLA", centerAlignment));
            sheet.mergeCells(5, 0, 8, 0);
            sheet.addCell(new Label(5, 0, "ATENCIÓN CANAL", centerAlignment));
            sheet.mergeCells(9, 0, 10, 0);
            sheet.addCell(new Label(9, 0, "SALIDA", centerAlignment));
            sheet.mergeCells(11, 0, 13, 0);
            sheet.addCell(new Label(11, 0, "INTERRUPCIÓN", centerAlignment));

            sheet.addCell(new Label(0, 1, "t", centerAlignment));
            sheet.addCell(new Label(1, 1, "Nº Cliente", centerAlignment));
            sheet.addCell(new Label(2, 1, "Intervalo", centerAlignment));
            sheet.addCell(new Label(3, 1, "Próxima", centerAlignment));
            sheet.addCell(new Label(4, 1, "Longitud", centerAlignment));
            sheet.addCell(new Label(5, 1, "Estado", centerAlignment));
            sheet.addCell(new Label(6, 1, "Nº Cliente", centerAlignment));
            sheet.addCell(new Label(7, 1, "Duración", centerAlignment));
            sheet.addCell(new Label(8, 1, "Fin", centerAlignment));
            sheet.addCell(new Label(9, 1, "Nº Cliente", centerAlignment));
            sheet.addCell(new Label(10, 1, "W", centerAlignment));
            sheet.addCell(new Label(11, 1, "Estado", centerAlignment));
            sheet.addCell(new Label(12, 1, "Duración", centerAlignment));
            sheet.addCell(new Label(13, 1, "Próxima", centerAlignment));
            sheet.addCell(new Label(14, 1, "Δt", centerAlignment));
            sheet.addCell(new Label(15, 1, "Tipo de Evento", centerAlignment));


            for (int i = 0; i < events.size(); i++) {
                Event event = events.get(i);
                String time = "" + event.getStartTime();
                String clientArrival = "" + event.getClientNumberArrival();
                if (clientArrival.equals("0")) {
                    clientArrival = "-";
                }
                String iac = "" + event.getIac();
                if (iac.equals("0.0") && !(event.getType().equals(EventType.ARRIVAL))) {
                    iac = "-";
                }
                String pa = "" + event.getNextArrival();
                if (pa.equals("0.0")) {
                    pa = "-";
                }
                String length = "" + event.getQueueLength();
                String state = "Ocupado";
                if (event.isChannelEmpty()) {
                    state = "Vacío";
                }
                String clientAttention = "" + event.getClientNumberChannel();
                if (clientAttention.equals("0")) {
                    clientAttention = "-";
                }
                String channelDuration = "" + event.getChannelDuration();
                if (channelDuration.equals("0.0") && !(event.getType().equals(EventType.DEPARTURE))) {
                    channelDuration = "-";
                }
                String end = "" + event.getEnd();
                if (end.equals("0.0")) {
                    end = "-";
                }
                String clientDeparture = "" + event.getClientNumberDeparture();
                if (clientDeparture.equals("0")) {
                    clientDeparture = "-";
                }
                String stayTime = "" + event.getTimeInSystem();
                if (stayTime.equals("0.0")) {
                    stayTime = "-";
                }
                String interruptionState = "Activo";

                if (event.isInterrupted()) {
                    interruptionState = "Interrumpido";
                }
                String interruptionDuration = event.getInterruptionDuration() + "";
                if (interruptionDuration.equals("0.0")) {
                    interruptionDuration = "-";
                }
                String nextInterruption = event.getNextInterruption() + "";
                if (nextInterruption.equals("0.0")) {
                    nextInterruption = "-";
                }

                String duration = "" + event.getDuration();
                String type = event.getType().toString();

                sheet.addCell(new Label(0, i + 2, time, centerAlignment));
                sheet.addCell(new Label(1, i + 2, clientArrival, centerAlignment));
                sheet.addCell(new Label(2, i + 2, iac, centerAlignment));
                sheet.addCell(new Label(3, i + 2, pa, centerAlignment));
                sheet.addCell(new Label(4, i + 2, length, centerAlignment));
                sheet.addCell(new Label(5, i + 2, state, centerAlignment));
                sheet.addCell(new Label(6, i + 2, clientAttention, centerAlignment));
                sheet.addCell(new Label(7, i + 2, channelDuration, centerAlignment));
                sheet.addCell(new Label(8, i + 2, end, centerAlignment));
                sheet.addCell(new Label(9, i + 2, clientDeparture, centerAlignment));
                sheet.addCell(new Label(10, i + 2, stayTime, centerAlignment));

                sheet.addCell(new Label(11, i + 2, interruptionState, centerAlignment));
                sheet.addCell(new Label(12, i + 2, interruptionDuration, centerAlignment));
                sheet.addCell(new Label(13, i + 2, nextInterruption, centerAlignment));

                sheet.addCell(new Label(14, i + 2, duration, centerAlignment));
                sheet.addCell(new Label(15, i + 2, type, centerAlignment));

              //  sheet.addCell(new Label(16, i + 2, "" + event.getTimeInQueue(), centerAlignment));

            }


            book.write();
            book.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /* public static void main(String[] args) {

       Event e = new Event(1.2, EventType.INITIATION, 2, false);
       List<Event> events = new ArrayList<Event>();
       events.add(e);
       ExcelGenerator.generateExcel("l", events);
   } */


}
