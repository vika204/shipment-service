package kma.romaniuk.shipment_service;

import java.util.UUID;

public record Shipment(
        UUID id,
        String destination,
        String status
) {}
