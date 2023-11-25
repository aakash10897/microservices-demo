#!/usr/bin/env bash

RELATIVE_DIR="$(dirname "${BASH_SOURCE[0]}")"

set -x

docker-compose -f ${RELATIVE_DIR}/compose/docker-compose.yml up
