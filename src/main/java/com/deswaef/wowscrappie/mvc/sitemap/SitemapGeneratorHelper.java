package com.deswaef.wowscrappie.mvc.sitemap;

public class SitemapGeneratorHelper {
    protected static String startUrl() {
        return "<url>\n";
    }

    protected static String endUrl() {
        return "</url>\n";
    }

    protected static String startChangeFreq() {
        return "\t<changefreq>";
    }

    protected static String endChangeFreq() {
        return "</changefreq>\n";
    }

    protected static String startLoq() {
        return "\t<loc>";
    }

    protected static String endLoq() {
        return "</loc>\n";
    }
}
