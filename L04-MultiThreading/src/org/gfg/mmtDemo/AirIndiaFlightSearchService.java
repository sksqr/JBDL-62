package org.gfg.mmtDemo;

import java.util.ArrayList;
import java.util.List;

public class AirIndiaFlightSearchService implements FlightSearchInterface {
    @Override
    public List<FlightData> getFlightsBySrcDes(String src, String des) {
        // API call to indigo Server
        //
        List<FlightData> data = new ArrayList<>();
        data.add(new FlightData("DLI", "BLR", 25000.00));
        data.add(new FlightData("DLI", "BLR", 24000.00));
        data.add(new FlightData("DLI", "BLR", 29000.00));
        return data;
    }
}
