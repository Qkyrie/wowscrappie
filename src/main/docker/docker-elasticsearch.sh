#!/usr/bin/env bash

#local
docker run -d -p 9200:9200 -p 9300:9300 --name scrappie-elasticsearch elasticsearch:1.7

#development
docker run -d -p 127.0.0.1:9200:9200 -p 127.0.0.1:9300:9300 --name scrappie-elasticsearch -v /opt/wowscrappie/development/elasticsearch-data:/usr/share/elasticsearch/data -v /opt/wowscrappie/development/elasticsearch-config:/usr/share/elasticsearch/config elasticsearch:1.7