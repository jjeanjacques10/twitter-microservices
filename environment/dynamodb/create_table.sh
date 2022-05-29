#!/usr/bin/env bash

set -euo pipefail

# enable debug
# set -x

echo "configure aws"
echo "==================="

aws configure set aws_access_key_id admin
aws configure set aws_secret_access_key admin
aws configure set region us-west-1

echo "create table"
echo "==================="

aws dynamodb create-table --cli-input-json file://tables.json --endpoint-url=http://localhost:4566

# or
# awslocal dynamodb create-table --cli-input-json file://tables.json
