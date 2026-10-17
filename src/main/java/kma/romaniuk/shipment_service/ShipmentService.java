package kma.romaniuk.shipment_service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ShipmentService {

    private final Map<UUID, Shipment> shipments = new ConcurrentHashMap<>();

    private final Counter createdShipmentsCounter;

    public ShipmentService(MeterRegistry registry) {
        this.createdShipmentsCounter =
                Counter.builder("shipments.created")
                .description("Total successfully created shipments")
                .register(registry);
    }

    public Shipment createShipment(String destination) {

        if (destination == null || destination.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Destination is required"
            );
        }

        Shipment shipment = new Shipment(
                UUID.randomUUID(),
                destination,
                "CREATED"
        );

        shipments.put(shipment.id(), shipment);

        createdShipmentsCounter.increment();

        return shipment;
    }
}