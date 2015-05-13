package com.deswaef.weakauras.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Quinten
 * Date: 12-5-2015
 * Time: 23:34
 *
 * @author Quinten De Swaef
 */
@Controller
@RequestMapping("/connect")
@Profile({"development", "production", "default"})
public class CustomConnectController extends ConnectController {
    /**
     * Constructs a ConnectController.
     *
     * @param connectionFactoryLocator the locator for {@link ConnectionFactory} instances needed to establish connections
     * @param connectionRepository     the current user's {@link ConnectionRepository} needed to persist connections; must be a proxy to a request-scoped bean
     */
    @Autowired
    public CustomConnectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    @Override
    protected String connectedView(String providerId) {
        return "redirect:/profiles/edit";
    }

    @Override
    protected String connectView(String providerId) {
        return "redirect:/profiles/edit";
    }
}
