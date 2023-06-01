package com.moraleda;

public  class VWAPListener implements IListener {
    private String instrument;

    public VWAPListener(String instrument) {
        this.instrument = instrument;
    }

    /**
     * In this method we calculate a value for vwap and printing it
     * @param instrument instrument being updated
     * @param price
     * @param quantity
     */
    @Override
    public void onMarketDepthUpdate(String instrument, double price, int quantity) {
        if (this.instrument.equals(instrument)) {
            double vwap = calculateVWAP(price, quantity);
            System.out.println("VWAP for " + instrument + ": " + vwap);
        }
    }

    private double calculateVWAP(double price, int quantity) {
        // Assuming simple VWAP calculation using price and quantity
        return price * quantity;
    }
}
