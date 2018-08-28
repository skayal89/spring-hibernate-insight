package com.test.customelockdemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shipmentAttribute")
@Data
public class ShipmentAttribute {
    @Id
    @GeneratedValue
    @Column(name ="shipmentAttributeId")
    private Long id;
    private String shipmentId;
    private String name;
    private String value;


    public enum attributeKeys {
        dangerous, priority_value, seller_return, booking_reference_id, service_instruction, print_customer_form,
        customer_promise_date, deliver_after_capability,deliver_after_date,mp_shipment_returned_to_ekl, pickup_center_location_id,pickup_promise_window_end_date,pickup_promise_window_start_date,try_and_buy,
        pickup_initiated_type, pickup_center_option, shipment_print_count, tier_type, movement_type, source_type, flyer_id, flash_shipment, packaging_material, estimated_rate, flash_dimension ,timeEstimate, lockerService,
        hyper_local, fragile, service, weight_slab,logistics_promise_date,four_hour_delivery,hawkshaw_shipment, contraband, self_serve_starting_promise_date, smart_pickup_v3, valid_for_digipay, digipay_transaction_id,
        dispatch_service_tier, dimension_updated_by, furniture_shipment, furniture_seller_pickup, furniture_units,
        return_service_type,is_d_plus_i, mps_package_reference_id, obd_type , shipment_notional_value, shipment_notional_value_qualifier,
        mh_destination_offload_id, hyper_local_detag_date
    }

    public enum flashAttributeKeys {
        length, breadth, height, weight, service_type, external_insurance, declared_value, courier_type, estimated_rate,lbh,
        booked_service_type, booked_courier_type, sla, sla_cutoff, timeEstimate, booked_package_type, booked_dimensions, booked_weight, package_type
    }
}
