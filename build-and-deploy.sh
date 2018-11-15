#!/usr/bin/env bash

set -e
lein prod
gcloud functions deploy helloworld --source target/dist --region=europe-west1 --runtime nodejs8 --trigger-http
echo "\n\nSuccess:"
echo "http post https://europe-west1-jarppe.cloudfunctions.net/helloworld/ name=Jarppe"
http post https://europe-west1-jarppe.cloudfunctions.net/helloworld/ name=Jarppe
