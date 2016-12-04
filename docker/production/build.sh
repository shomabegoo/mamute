#!/bin/bash
pushd ../../
./scripts/mvn-package.sh
cp target/mamute-1.4.0.war docker/production/Shomabegoo/ 
cp target/mamute-1.4.0.war docker/production/Tabaye/
popd
