package kma.romaniuk.shipment_service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shipment createShipment(@RequestBody CreateShipmentRequest request) {
        return shipmentService.createShipment(request.destination());
    }
}