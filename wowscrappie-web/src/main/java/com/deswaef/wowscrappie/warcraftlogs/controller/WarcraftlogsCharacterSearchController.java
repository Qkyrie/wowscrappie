package com.deswaef.wowscrappie.warcraftlogs.controller;

import com.deswaef.wowscrappie.classes.domain.Spec;
import com.deswaef.wowscrappie.classes.domain.WowClass;
import com.deswaef.wowscrappie.classes.service.SpecService;
import com.deswaef.wowscrappie.raids.domain.Boss;
import com.deswaef.wowscrappie.raids.service.BossService;
import com.deswaef.wowscrappie.realm.service.RealmService;
import com.deswaef.wowscrappie.importingservice.warcraftlogs.WarcraftlogsAPIException;
import com.deswaef.wowscrappie.warcraftlogs.controller.dto.CharacterRankingResultDto;
import com.deswaef.wowscrappie.importingservice.warcraftlogs.domain.DifficultyEnum;
import com.deswaef.wowscrappie.importingservice.warcraftlogs.domain.rankings.CharacterRanking;
import com.deswaef.wowscrappie.importingservice.warcraftlogs.service.RankingsFetcher;
import com.deswaef.wowscrappie.importingservice.warcraftlogs.service.searchcriteria.SearchCharacterRankingDto;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/warcraftlogs/character")
public class WarcraftlogsCharacterSearchController {

    public static final PrettyTime PRETTY_TIME = new PrettyTime(Locale.ENGLISH);
    public static final DecimalFormat decimalformat = new DecimalFormat("#.##");
    @Autowired
    private RankingsFetcher rankingsFetcher;
    @Autowired
    private RealmService realmService;
    @Autowired
    private BossService bossService;
    @Autowired
    private SpecService specService;

    @RequestMapping(method = GET)
    public String index(ModelMap modelMap) {
        return "warcraftlogs/character/charactersearch";
    }

    @RequestMapping("/{charname}/{realmId}")
    public
    @ResponseBody
    List<CharacterRankingResultDto> findPersonalRankings(@PathVariable("charname") String charname, @PathVariable("realmId") Long realmId) throws WarcraftlogsAPIException {
        return rankingsFetcher.findRankings(
                new SearchCharacterRankingDto()
                        .setCharacterName(charname)
                        .setRealm(realmService.findOne(realmId).orElseThrow(() -> new IllegalArgumentException()))
        )
                .stream()
                .map(getCharacterRankingCharacterRankingFunction())
                .collect(Collectors.toList());
    }

    private Function<CharacterRanking, CharacterRankingResultDto> getCharacterRankingCharacterRankingFunction() {
        return x -> {
            Spec spec = specService.findByWarcraftlogsIdAndWowClassWarcraftlogsid(x.getSpec(), x.getClassId()).orElse(
                    new Spec()
                            .setName("unknown")
                            .setWowClass(
                                    new WowClass()
                                            .setName("unknown")
                            )
            );
            String encounterName = "unknown";
            org.joda.time.Period period = org.joda.time.Period.millis(Integer.valueOf("" + x.getDuration())).toPeriod();
                Optional<Boss> byWarcraftlogsEncounterId = bossService.findByWarcraftlogsEncounterId(x.getEncounter());
            if (byWarcraftlogsEncounterId.isPresent()) {
                encounterName = byWarcraftlogsEncounterId.get().getName();
            }
            return new CharacterRankingResultDto()
                    .setDifficulty(x.getDifficulty())
                    .setDuration(x.getDuration())
                    .setGuild(x.getGuild())
                    .setSize(x.getSize())
                    .setReportID(x.getReportID())
                    .setItemLevel(x.getItemLevel())
                    .setTotal(x.getTotal())
                    .setRank(x.getRank())
                    .setOutOf(x.getOutOf())
                    .setRankPercentage(decimalformat.format((1 - (float)x.getRank() / x.getOutOf())*100) + "%")
                    .setFightID(x.getFightID())
                    .setClassAndSpec(String.format("%s %s", spec.getName(), spec.getWowClass().getName()))
                    .setStartTimePretty(PRETTY_TIME.format(new Date(x.getStartTime())))
                    .setStartTime(x.getStartTime())
                    .setDurationPretty(period.normalizedStandard().getMinutes() + " minutes " + period.normalizedStandard().getSeconds() + " seconds")
                    .setEncounterName(encounterName + " (" + DifficultyEnum.fromId(x.getDifficulty()).getName() + ")");
        };
    }
}

