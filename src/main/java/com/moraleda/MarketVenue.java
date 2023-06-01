package com.moraleda;

import java.util.HashMap;
import java.util.Map;

public class MarketVenue {
    private String name;
    private Map<String, Double> instrumentPrices;

    public MarketVenue(String name) {
        this.name = name;
        this.instrumentPrices = new HashMap<>();
    }

    public void updateMarketDepth(String instrument, double price, int quantity) {
        instrumentPrices.put(instrument, price);
        notifyListeners(instrument, price, quantity);
    }

    private void notifyListeners(String instrument, double price, int quantity) {
        for (IListener listener : MarketDepthManager.getInstance().getListeners()) {
            listener.onMarketDepthUpdate(instrument, price, quantity);
        }
    }
    public Map<String, Double> getInstrumentPrices() {
        return instrumentPrices;
    }
    public void addEmptyInstrument(String name){
        instrumentPrices.put(name, null);
    }

}