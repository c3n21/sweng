#!/usr/bin/env bash

curl https://raw.githubusercontent.com/matteocontrini/comuni-json/master/comuni.json | jq -j '.[] | .nome,":",.codiceCatastale,"\n"' > comuni.txt

curl https://raw.githubusercontent.com/arseniosiani/cfjs/master/stati_esteri.json | jq -j '.[] | .nome,":",.codiceCatastale,"\n"' > stati_esteri.txt
