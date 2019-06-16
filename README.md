##### Run ErrorCount locally
```
1. mvn clean
2. mvn install
3. mvn compile exec:java -Dexec.mainClass=com.example.StarterPipeline -Dexec.args="--topicName=projects/lucky-lead-240003/topics/my-pubsub"

```

##### Run ErrorCount on the Cloud Dataflow service
```
1.mvn clean
2.mvn install
3.mvn -Pdataflow-runner compile exec:java -Dexec.mainClass=com.example.StarterPipeline -Dexec.args=" --runner=DataflowRunner --topicName=projects/lucky-lead-240003/topics/my-pubsub"

```
##### Run ErrorCount on the Cloud Dataflow service using Cloud Dataflow template
```
mvn compile exec:java -Dexec.mainClass=com.example.StarterPipeline  -Dexec.args="--runner=DataflowRunner  --project=lucky-lead-240003 --stagingLocation=gs://lucky-lead-240003/staging  --templateLocation=gs://lucky-lead-240003/templates/stream-errorcount-template"


```

##### This Maven command creates and stages a template at the Cloud Storage location specified with --templateLocation.

- ADD PROJECT ID with your project ID.

- Replace BUCKET NAME (stagingLoation) with the name of your Cloud Storage bucket.

- Replace TEMPLATE NAME with the name of your template.

- Replace com.example.myclass with your Java class.

After you create and stage your template, After you create and stage your Cloud Dataflow template, execute the template with the Google Cloud Platform Console, REST API, or gcloud command-line tool.
<br>[Execute the template](https://cloud.google.com/dataflow/docs/guides/templates/executing-templates)

Template needs parameters(topicName), click on Add item in the Additional Parameters section. Enter in the Name and Value of the parameter. Repeat this step for each needed parameter.
 
