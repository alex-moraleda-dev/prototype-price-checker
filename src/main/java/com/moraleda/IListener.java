package com.moraleda;

interface IListener {
    void onMarketDepthUpdate(String instrument, double price, int quantity);
}
