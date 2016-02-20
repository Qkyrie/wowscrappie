package com.deswaef.wowscrappie.auctionhouse.service;

import com.deswaef.wowscrappie.auctionhouse.domain.HistoricAuctionHouseSnapshot;
import com.deswaef.wowscrappie.auctionhouse.repository.HistoricAuctionHouseSnapshotRepository;
import com.deswaef.wowscrappie.infrastructure.exception.WowscrappieException;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.repository.ItemRepository;
import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.repository.RealmRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static com.deswaef.wowscrappie.util.TestHelper.assertThrows;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HistoricAuctionHouseDataServiceImplTest {


    public static final long ITEM_ID = 123L;
    public static final long REALM_ID = 321L;
    @InjectMocks
    private HistoricAuctionHouseDataServiceImpl auctionHouseSnapshotService;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private RealmRepository realmRepository;
    @Mock
    private HistoricAuctionHouseSnapshotRepository auctionHouseSnapshotRepository;
    @Mock
    private Realm realm;
    @Mock
    private Item item;
    @Mock
    private HistoricAuctionHouseSnapshot auctionHouseSnapshot;

    @Before
    public void setUp() throws Exception {
        when(item.getId())
                .thenReturn(ITEM_ID);
        when(realm.getId())
                .thenReturn(REALM_ID);
        when(auctionHouseSnapshot.getItemId())
                .thenReturn(ITEM_ID);
        when(auctionHouseSnapshot.getRealmId())
                .thenReturn(REALM_ID);
    }

    @Test
    public void itemDoesNotExistThrowsException() throws Exception {
        when(itemRepository.findOne(ITEM_ID))
                .thenReturn(Optional.empty());

        Throwable throwable = assertThrows(WowscrappieException.class,
                () -> auctionHouseSnapshotService.findByItemIdAndRealm(ITEM_ID, REALM_ID).toBlocking().single()
        );

        assertThat(throwable.getMessage()).isEqualTo("That is not yet indexed or does not exist");
    }

    @Test
    public void realmDoesNotExistThrowsException() throws Exception {
        when(itemRepository.findOne(ITEM_ID))
                .thenReturn(Optional.of(item));
        when(realmRepository.findOne(REALM_ID))
                .thenReturn(Optional.empty());

        Throwable throwable = assertThrows(WowscrappieException.class,
                () -> auctionHouseSnapshotService.findByItemIdAndRealm(ITEM_ID, REALM_ID).toBlocking().single()
        );

        assertThat(throwable.getMessage()).isEqualTo("That realm is not yet indexed or does not exist");
    }


    @Test
    public void noLastSnapshotFoundThrowsException() throws Exception {
        when(itemRepository.findOne(ITEM_ID))
                .thenReturn(Optional.of(item));
        when(realmRepository.findOne(REALM_ID))
                .thenReturn(Optional.of(realm));
        when(auctionHouseSnapshotRepository.findByItemIdAndRealmId(ITEM_ID, REALM_ID))
                .thenReturn(new ArrayList<>());
        assertThat(
                auctionHouseSnapshotService.findByItemIdAndRealm(ITEM_ID, REALM_ID).toList().toBlocking().single()
        ).isEmpty();
    }

    @Test
    public void whenFoundReturnsObservable() throws Exception {
        when(itemRepository.findOne(ITEM_ID))
                .thenReturn(Optional.of(item));
        when(realmRepository.findOne(REALM_ID))
                .thenReturn(Optional.of(realm));
        when(auctionHouseSnapshotRepository.findByItemIdAndRealmId(ITEM_ID, REALM_ID))
                .thenReturn(singletonList(auctionHouseSnapshot));

        assertThat(
                auctionHouseSnapshotService.findByItemIdAndRealm(ITEM_ID, REALM_ID)
                        .toList()
                        .toBlocking().single()
                        .stream().findFirst().get()
        ).isSameAs(auctionHouseSnapshot);
    }

}