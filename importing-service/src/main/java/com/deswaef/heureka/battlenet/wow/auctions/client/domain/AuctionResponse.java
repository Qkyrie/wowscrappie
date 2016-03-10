package com.deswaef.heureka.battlenet.wow.auctions.client.domain;

import com.deswaef.heureka.battlenet.wow.client.BattlenetConverter;
import com.deswaef.heureka.battlenet.wow.domain.AbstractBattlenetDto;

import java.util.List;

public class AuctionResponse extends AbstractBattlenetDto {

    private List<AuctionResponseFile> files;

    public List<AuctionResponseFile> getFiles() {
        return files;
    }

    public void setFiles(List<AuctionResponseFile> files) {
        this.files = files;
    }

    public static class AuctionResponseFile {
        private String url;
        private Long lastModified;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Long getLastModified() {
            return lastModified;
        }

        public void setLastModified(Long lastModified) {
            this.lastModified = lastModified;
        }
    }

    public static AuctionResponse fromJson(String json) {
        if (isErrorMessage(json)) {
            throwException(json);
            return null;
        } else {
            return BattlenetConverter.convert(json, AuctionResponse.class);
        }
    }
}