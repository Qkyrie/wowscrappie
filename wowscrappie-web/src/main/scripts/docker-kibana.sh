#!/usr/bin/env bash
eval $(docker-machine env dev)
docker run -d -p 5601:5601 --name scrappie-kibana --link scrappie-elasticsearch:scrappie-elasticsearch -e KIBANA_ES_URL=http://scrappie-elasticsearch:9200 bobrik/kibana