aws sqs  create-queue --queue-name timeline-queue --endpoint-url=http://localhost:4576

aws sqs send-message --queue-url http://localhost:4576/000000000000/timeline-queue --endpoint-url=http://localhost:4576 --message-body 123