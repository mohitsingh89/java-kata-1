package org.echocat.kata.java.part1.datasource;

public class DataProviderCsvImpl implements DataProvider {

    @Override
    public Data get() {
        return CsvDataLoader.loadData();
    }
}
