package com.moraleda;

public class PriceChecker {
    public static void main(String[] args) {
        MarketVenue venue1 = new MarketVenue("Venue 1"), venue2 = new MarketVenue("Venue 2");

        // Create instruments and subscribe to market venues
        String instrument1 = "Instrument 1", instrument2 = "Instrument 2";

        venue1.addEmptyInstrument(instrument1);
        venue1.addEmptyInstrument(instrument2);

        venue2.addEmptyInstrument(instrument1);
        venue2.addEmptyInstrument(instrument2);

        MarketDepthManager.getInstance().subscribeMarketVenue("Venue 1", venue1);
        MarketDepthManager.getInstance().subscribeMarketVenue("Venue 2", venue2);

        // Create VWAP listener for instruments
        VWAPListener vwapListener1 = new VWAPListener(instrument1);
        VWAPListener vwapListener2 = new VWAPListener(instrument2);

        MarketDepthManager.getInstance().addMarketDepthListener(vwapListener1);
        MarketDepthManager.getInstance().addMarketDepthListener(vwapListener2);

        // Start simulating market data updates
        MarketDepthManager.getInstance().startMarketDataSimulation(10);
    }
}
