#!/usr/bin/env bash

docker run -d -p 127.0.0.1:9200:9200 -p 127.0.0.1:9300:9300 --name scrappie-elasticsearch -v /opt/wowscrappie/development/elasticsearch-data:/usr/share/elasticsearch/data -v /opt/wowscrappie/development/elasticsearch-config:/usr/share/elasticsearch/config elasticsearch:1.7