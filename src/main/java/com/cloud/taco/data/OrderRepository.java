package com.cloud.taco.data;

import com.cloud.taco.TacoOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
    //Spring Data method signatures
    
    List<TacoOrder> findByDeliveryZipAndPlacedAtBetween(String deliveryZid,
                                                        Instant startDate,
                                                        Instant finalDate);
    List<TacoOrder> findByDeliveryCityOrderByDeliveryTo(String city);

    @Query("TacoOrder o where o.deliveryCity='Seattle'")
    List<TacoOrder> readOrdersDeliveredInSeattle();
}
