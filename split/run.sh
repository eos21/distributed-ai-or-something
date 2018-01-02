#!/bin/bash
docker build -t local/ai-split .
echo 1 | docker run -i --rm local/ai-split:latest
