package org.gfg.mmtDemo;

import java.util.ArrayList;
import java.util.List;

public class MmtDemo {

    public static void main(String[] args) {
        List<FlightSearchInterface> flightSearchImps = new ArrayList<>();
        flightSearchImps.add(new GoFlightSearchService());
        flightSearchImps.add(new IndigoFlightSearchService());
        flightSearchImps.add(new AirIndiaFlightSearchService());
        MmtSearchService mmtSearchService = new MmtSearchService(flightSearchImps);
        System.out.println(mmtSearchService.getAllFlightsBySrcAndDes("DLI","BLR"));
    }
}
