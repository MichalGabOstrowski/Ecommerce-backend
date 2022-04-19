package pl.ryspie.springbootecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import pl.ryspie.springbootecommerce.entity.Product;
import pl.ryspie.springbootecommerce.entity.ProductCategory;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        disableHttpMethodsForGivenClass(config, theUnsupportedActions, Product.class);
        disableHttpMethodsForGivenClass(config, theUnsupportedActions, ProductCategory.class);

//        call an internal helper method
        exposeIds(config);


    }

    private <T> void disableHttpMethodsForGivenClass(RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions, Class<T> productClass) {
        config.getExposureConfiguration()
                .forDomainType(productClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        //expose entity ids

        //get a list of all entity clases from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //create an array of entity types
        List<Class> entityClasses = new ArrayList<>();

        //get the entityTypes for the entities
        entities.forEach(tempEntityType -> entityClasses.add(tempEntityType.getJavaType()));
        //for (EntityType tempEntityType : entities) {
        //entityClasses.add(tempEntityType.getJavaType());


        //expose the [entity ids] for the array of entity/domain types
        //set ID as a Class property
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);


    }


    //disable HtttpMethod for Product-> PUT, POST, DELETE
//        config.getExposureConfiguration()
//                .forDomainType(Product.class)
//                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
//                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
//
//        //disable HtttpMethod for ProductCategory -> PUT, POST, DELETE
//        config.getExposureConfiguration()
//                .forDomainType(ProductCategory.class)
//                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
//                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
}

