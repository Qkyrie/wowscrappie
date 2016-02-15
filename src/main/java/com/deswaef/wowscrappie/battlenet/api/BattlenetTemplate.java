package com.deswaef.wowscrappie.battlenet.api;

import com.deswaef.wowscrappie.battlenet.api.impl.CharacterProfileTemplate;
import com.deswaef.wowscrappie.battlenet.api.impl.ItemTemplate;
import com.deswaef.wowscrappie.battlenet.api.impl.UserTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.List;

public class BattlenetTemplate extends AbstractOAuth2ApiBinding implements Battlenet {

    private String appId;

    private UserOperations userOperations;
    private ItemOperations itemOperations;
    private CharacterProfileOperations characterProfileOperations;

    private ObjectMapper objectMapper;

    public BattlenetTemplate() {
        super();
        initialize();
    }

    public BattlenetTemplate(String accesstoken, String appId) {
        super(accesstoken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        initialize();
    }

    @Override
    public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
        // Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat reads on the response.getBody()
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(requestFactory));
    }

    @Override
    public UserOperations userOperations() {
        return userOperations;
    }

    @Override
    public CharacterProfileOperations characterProfileOperations() {
        return null;
    }

    @Override
    public ItemOperations itemOperations() {
        return itemOperations;
    }

    @Override
    public RestOperations restOperations() {
        return getRestTemplate();
    }


    private void initialize() {
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
        initSubApis();
    }

    private void initSubApis() {
        userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
        itemOperations = new ItemTemplate(getRestTemplate(), isAuthorized());
        characterProfileOperations = new CharacterProfileTemplate(getRestTemplate(), isAuthorized());
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> deserializeDataList(JsonNode jsonNode, final Class<T> elementType) {
        try {
            CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(List.class, elementType);
            return (List<T>) objectMapper.reader(listType).readValue(jsonNode.toString()); // TODO: EXTREMELY HACKY--TEMPORARY UNTIL I FIGURE OUT HOW JACKSON 2 DOES THIS
        } catch (IOException e) {
            throw new UncategorizedApiException("facebook", "Error deserializing data from Facebook: " + e.getMessage(), e);
        }
    }

    private String join(String[] strings) {
        StringBuilder builder = new StringBuilder();
        if (strings.length > 0) {
            builder.append(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                builder.append("," + strings[i]);
            }
        }
        return builder.toString();
    }


}
