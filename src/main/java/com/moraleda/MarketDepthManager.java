package com.moraleda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MarketDepthManager {
    private static MarketDepthManager instance;
    private Map<String, MarketVenue> marketVenues;
    private List<IListener> listeners;

    private MarketDepthManager() {
        this.marketVenues = new HashMap<>();
        this.listeners = new ArrayList<>();
    }

    public static MarketDepthManager getInstance() {
        if (instance == null) {
            synchronized (MarketDepthManager.class) {
                if (instance == null) {
                    instance = new MarketDepthManager();
                }
            }
        }
        return instance;
    }

    public void subscribeMarketVenue(String venueName, MarketVenue marketVenue) {
        marketVenues.put(venueName, marketVenue);
    }

    public void addMarketDepthListener(IListener listener) {
        listeners.add(listener);
    }
    public void addMarketDepthListeners(List<IListener> listeners) {
        listeners.addAll(listeners);
    }
    public void removeMarketDepthListener(String listenerName) {
        listeners.remove(listenerName);
    }

    public List<IListener> getListeners() {
        return listeners;
    }

    public void startMarketDataSimulation(int numSimulations) {
        // In this method we are simulating a stream of data
        for (int i = 0; i < numSimulations; i++) {
            for (String venueName : marketVenues.keySet()) {
                MarketVenue marketVenue = marketVenues.get(venueName);
                for (String instrument : marketVenue.getInstrumentPrices().keySet()) {
                    double price = (Math.random() * 100000);
                    int quantity = (int) (Math.random() * 100);
                    marketVenue.updateMarketDepth(instrument, price, quantity);
                }
            }
            sleep((int)(Math.random() * 5 )+5);
        }
    }
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
