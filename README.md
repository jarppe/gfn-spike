# gcc-fn-spike

Spiking Google Cloud Functions with ClojureScript.
 
## Usage

Build and deploy:

```bash
lein prod
gcloud functions deploy helloworld --region=europe-west1 --runtime nodejs8 --trigger-http --source target
```

Invoke:

```bash
http https://europe-west1-jarppe.cloudfunctions.net/helloworld name=Jarppe
```

## License

Copyright © 2018 Jarppe Länsiö

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
