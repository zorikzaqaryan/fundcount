package com.fundcount.task.service;

import com.fundcount.task.model.RateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Service for money calculation operations
 */
@Service
public class FixerConnectionService {
    private Logger logger = LoggerFactory.getLogger(FixerConnectionService.class);

    @Value("${fixer.api.url}")
    private String fixerUrl;

    @Value("${fixer.api.access.key}")
    private String accessKey;

    /**
     * @param date for exchange information for latest information use latest instead of date
     * @return RateDto
     */
    public RateDto getFixerDataByDateAndCurrency(String date) {
        RateDto rateDto = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            final String baseUrl = fixerUrl + date + "?access_key=" + accessKey + "&&symbols=USD,RUB&format=1";
            URI uri = new URI(baseUrl);
            rateDto = restTemplate.getForEntity(uri, RateDto.class).getBody();
        } catch (URISyntaxException e) {
            logger.error("Can't reach URL getFixerDataByDateAndCurrency ", e);
        }
        return rateDto;
    }
}
