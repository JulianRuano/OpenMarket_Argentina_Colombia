package ar.edu.unlp.pas.delivery.infrastructure.config;

import ar.edu.unlp.pas.delivery.infrastructure.output.persistence.DeliveryPersistenceAdapter;
import ar.edu.unlp.pas.delivery.infrastructure.output.persistence.mapper.IDeliveryPersistenceMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public IDeliveryPersistenceMapper deliveryPersistenceMapper(){
        return Mappers.getMapper(IDeliveryPersistenceMapper.class);
    }


}
