package com.deswaef.wowscrappie.mvc.sitemap;

import com.deswaef.wowscrappie.classes.service.ClassService;
import com.deswaef.wowscrappie.raids.service.BossService;
import com.deswaef.wowscrappie.raids.service.RaidService;
import com.deswaef.wowscrappie.ui.macros.domain.Macro;
import com.deswaef.wowscrappie.ui.macros.service.MacroService;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAura;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import com.deswaef.wowscrappie.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.deswaef.wowscrappie.mvc.sitemap.SitemapGeneratorHelper.*;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@Component
public class SitemapGenerator {

    @Autowired
    private ClassService classService;
    @Autowired
    private BossService bossService;
    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private MacroService macroService;
    @Autowired
    private UserService userService;
    @Autowired
    private RaidService raidService;

    @Value("${com.deswaef.scrappie.fullbaseurl}")
    private String baseUrl;

    public String generate() {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append(header());
        xmlBuilder.append(mainsite());
        xmlBuilder.append(classAndSpecPages());
        xmlBuilder.append(bossesAndRaids());
        xmlBuilder.append(allSharedPages());
        xmlBuilder.append(userPages());
        xmlBuilder.append(tools());
        xmlBuilder.append(footer());
        return xmlBuilder.toString();
    }

    private String tools() {
        return urlToEntry("/warcraftlogs/character", ChangeFrequency.WEEKLY);
    }

    private String userPages() {
        return userService.findAll()
                .stream()
                .filter(user -> user.isAccountNonExpired() && user.isAccountNonLocked() && user.isEnabled() && user.isCredentialsNonExpired())
                .map(user -> urlToEntry(format("/users/%d", user.getId()), ChangeFrequency.WEEKLY))
                .collect(joining());
    }

    private String allSharedPages() {
        return new StringBuilder()
                .append(sharedWeakauras())
                .append(sharedMacros())
                .append(sharedTellMeWhens())
                .toString();
    }

    private String sharedWeakauras() {
        return weakAuraService
                .findAll()
                .stream()
                .filter(WeakAura::isApproved)
                .map(wa -> urlToEntry(format("/shared/wa/%d", wa.getId()), ChangeFrequency.WEEKLY))
                .collect(joining());
    }

    private String sharedMacros() {
        return macroService
                .findAll()
                .stream()
                .filter(Macro::isApproved)
                .map(macro -> urlToEntry(format("/shared/macro/%d", macro.getId()), ChangeFrequency.WEEKLY))
                .collect(joining());
    }

    private String sharedTellMeWhens() {
        return tellMeWhenService
                .findAll()
                .stream()
                .filter(TellMeWhen::isApproved)
                .map(tmw -> urlToEntry(format("/shared/tmw/%d", tmw.getId()), ChangeFrequency.WEEKLY))
                .collect(joining());
    }

    private String bossesAndRaids() {
        return
                new StringBuilder()
                        .append(urlToEntry("/raids", ChangeFrequency.WEEKLY))
                        .append(
                                bossService
                                        .findAllRaidBosses()
                                        .stream()
                                        .map(rb -> new StringBuilder()
                                                        .append(urlToEntry(format("/raids/%d?bossid=%d&amp;configtype=macro", rb.getRaid().getId(), rb.getId()), ChangeFrequency.WEEKLY))
                                                        .append(urlToEntry(format("/raids/%d?bossid=%d&amp;configtype=weakaura", rb.getRaid().getId(), rb.getId()), ChangeFrequency.WEEKLY))
                                                        .append(urlToEntry(format("/raids/%d?bossid=%d&amp;configtype=tellmewhen", rb.getRaid().getId(), rb.getId()), ChangeFrequency.WEEKLY))
                                                        .toString()
                                        )
                                        .collect(joining())
                        )
                        .append(
                                raidService
                                        .findAll()
                                        .stream()
                                        .map(raid -> urlToEntry(format("/raids/%d", raid.getId()), ChangeFrequency.WEEKLY))
                                        .collect(joining())
                        ).toString();
    }

    private String classAndSpecPages() {
        return classService.findAll()
                .stream()
                .map(c -> new StringBuilder(urlToEntry(format("/classes/%s", c.getSlug()), ChangeFrequency.DAILY))
                        .append(
                                c.getSpecs()
                                        .stream()
                                        .map(s -> new StringBuilder()
                                                        .append(urlToEntry(format("/classes/%s?wowspec=%s&amp;configtype=macro", c.getSlug(), s.getSlug()), ChangeFrequency.WEEKLY))
                                                        .append(urlToEntry(format("/classes/%s?wowspec=%s&amp;configtype=weakaura", c.getSlug(), s.getSlug()), ChangeFrequency.WEEKLY))
                                                        .append(urlToEntry(format("/classes/%s?wowspec=%s&amp;configtype=tellmewhen", c.getSlug(), s.getSlug()), ChangeFrequency.WEEKLY))
                                                        .toString()
                                        )
                                        .collect(joining())

                        ).toString())
                .collect(joining());
    }

    private String mainsite() {
        return urlToEntry("", ChangeFrequency.DAILY);
    }

    private String footer() {
        return "\n</urlset>";
    }

    private String header() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<urlset\n" +
                "      xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n" +
                "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "      xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9\n" +
                "            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">\n";
    }

    private String urlToEntry(String url, ChangeFrequency changeFrequency) {
        return new StringBuilder()
                .append(url())
                    .append(loc())
                        .append(baseUrl).append(url)
                    .append(_loc())
                    .append(changefreq())
                    .append(changeFrequency.getName())
                    .append(_changefreq())
                .append(_url())
                .toString();
    }


}
