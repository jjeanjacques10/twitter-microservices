#!/usr/bin/env bash

# enable debug
# set -x

echo "configure aws"
echo "==================="

aws configure set aws_access_key_id admin
aws configure set aws_secret_access_key admin
aws configure set region us-west-1

echo "create sqs queue"
echo "==================="

# https://docs.aws.amazon.com/cli/latest/reference/sqs/create-queue.html
create_queue() {
	local QUEUE_NAME_TO_CREATE=$1
	awslocal --endpoint-url=http://localhost:4576 sqs create-queue --queue-name ${QUEUE_NAME_TO_CREATE} --region us-west-1 --attributes VisibilityTimeout=30
}

create_queue "timeline-queue"

#aws sqs create-queue --queue-name timeline-queue --endpoint-url=http://localhost:4576
#aws sqs send-message --queue-url http://localhost:4576/000000000000/timeline-queue --endpoint-url=http://localhost:4576 --message-body 123
#aws sqs receive-message --queue-url http://localhost:4576/000000000000/timeline-queue --endpoint-url=http://localhost:4576