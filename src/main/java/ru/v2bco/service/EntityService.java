package ru.v2bco.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.v2bco.entity.EntityExample;
import ru.v2bco.publishers.GraphApiPublisher;


@Service
public class EntityService {

    private static final Logger logger = LoggerFactory.getLogger(EntityService.class);

    private String accessTokken;
    private String groupId = "320025818782337";

    private GraphApiPublisher graphApiPublisher;

    public String publishEntity(String entity) {
        logger.info("publishing EntityExample entity");
        return getGraphApiPublisher().publishPhoto();
    }

    public void deleteEntity(Long id) {
        logger.info("deleting OperationStage entity");

    }

    private GraphApiPublisher getGraphApiPublisher() {
        return graphApiPublisher == null ? new GraphApiPublisher(getAccessTokken(), groupId) : graphApiPublisher;
    }

    public String getAccessTokken () {
        if (accessTokken != null) {
            return accessTokken;
        } else {
            accessTokken = "EAAHOMW1UjmUBADFJibvHlQLi2AD0LJM2KjoZCTxzvnYfHYvyhlpwaxjXnYCdvRMfZB0AAo59XgL2lloG9NSP76JskP9BZBu2qp4xYiqltMiBHYHhahtrWgZBdwoBP4XGmBE9AwYuNVBHP54riZBsosGS1zXfAx0r19TWNH5Toj2GbXTpnfECdCKA9BPqal4CF6Wuj6yUGxgZDZD";
            return accessTokken;
        }
    }
}
