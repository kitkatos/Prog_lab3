package org.example.factory;

public record FactoryReport(Factory factory, int producedToday, int profit, WorkerInformation[] workers) {
}
