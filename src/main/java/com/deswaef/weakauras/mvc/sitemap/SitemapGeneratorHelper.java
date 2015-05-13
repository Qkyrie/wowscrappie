package com.deswaef.weakauras.mvc.sitemap;

public class SitemapGeneratorHelper {
    protected static String url() {
        return "<url>\n";
    }

    protected static String _url() {
        return "</url>\n";
    }

    protected static String changefreq() {
        return "\t<changefreq>";
    }

    protected static String _changefreq() {
        return "</changefreq>\n";
    }

    protected static String loc() {
        return "\t<loc>";
    }

    protected static String _loc() {
        return "</loc>\n";
    }
}
