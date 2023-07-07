package ar.edu.unlp.pas.product.infraestructure.adapters.config;

import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.mapper.IPublicationPersistenceMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.edu.unlp.pas.product.infraestructure.adapters.output.persistence.mapper.IProductPersistenceMapper;

@Configuration
public class BeanConfiguration {

    @Bean
    public IProductPersistenceMapper productPersistenceMapper() {
        return Mappers.getMapper(IProductPersistenceMapper.class);
    }
    @Bean
    public IPublicationPersistenceMapper publicationPersistenceMapper() {
        return Mappers.getMapper(IPublicationPersistenceMapper.class);
    }
}
