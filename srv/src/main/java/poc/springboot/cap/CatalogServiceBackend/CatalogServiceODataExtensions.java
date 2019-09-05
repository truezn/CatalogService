package poc.springboot.cap.CatalogServiceBackend;


import com.sap.cloud.sdk.service.prov.api.ExtensionHelper;
import com.sap.cloud.sdk.service.prov.api.annotations.AfterQuery;
import com.sap.cloud.sdk.service.prov.api.annotations.BeforeQuery;
import com.sap.cloud.sdk.service.prov.api.exits.BeforeQueryResponse;
import com.sap.cloud.sdk.service.prov.api.request.QueryRequest;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponse;
import com.sap.cloud.sdk.service.prov.api.response.QueryResponseAccessor;
import com.sap.cloud.servicesdk.spring.ServiceProcessingExtensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ServiceProcessingExtensions
public class CatalogServiceODataExtensions {

    private static final Logger logger = LoggerFactory.getLogger(BusinessConfigurationServiceODataExtensions.class);
    private static final String SERVICE_NAME = "CatalogService";
    private static final String ENTITY_NAME = "Books";

    @BeforeQuery(entity = ENTITY_NAME, serviceName = SERVICE_NAME)
    public BeforeQueryResponse beforeRead(QueryRequest readRequest, ExtensionHelper extensionHelper) {
        logger.info("Executing OData Processor: BEFORE READ");
        return BeforeQueryResponse.setSuccess().response();
    }

    @AfterQuery(entity = ENTITY_NAME,serviceName = SERVICE_NAME)
    public QueryResponse afterRead(QueryRequest readRequest, QueryResponseAccessor readResponseAccessor, ExtensionHelper extensionHelper){
        logger.info("Executing OData Processor: AFTER READ");
        return QueryResponse.setSuccess().response();
    }

    private static class BusinessConfigurationServiceODataExtensions {
    }
}
