# Dataflow ErrorCount: Streaming processing with Real-time data
Build a streaming  datapipeline which demonstrate how to set up a processing pipeline that can read server log,  perform a frequency error count on each log file and write the result data to GCS.

![assignment-02-problem-statement (2)](https://user-images.githubusercontent.com/42492058/59561142-1406be80-903a-11e9-8992-2e4a7205d5f5.png)

#### Design

Build a pipeline to output a error count on each log file for every minute.By default all data belons to single Global Window.The singal global window is unbounded but we can set trigger to determine when to emit the result.
1. Read the unbounded log data data from a gCloud PUB/SUB Topic.
2. Process the log data and extract the error.
3. Use Processing time trigger to emit the results. 
4. Calculate the error count for each file.
5. Write the result data to GCS.
6. Run the pipeline both locally and using  cloud Dataflow service.


#### Environment Setup
1. Create a account on Google Cloud Platform(GCP).
2. Install the Cloud Tools for Eclipse plugin and provide authentication credentials to your application code by setting the environment variable GOOGLE_APPLICATION_CREDENTIALS.
3. Create a Cloud Dataflow project in Eclipse [Cloud Dataflow project and run an example pipeline from within Eclipse](https://cloud.google.com/dataflow/docs/quickstarts/quickstart-java-eclipse)
4. Installl Cloud Storage Client Libraries(Add dependancy to pom.xml file)	
 If you are using Maven, add the following to your pom.xml file:
```
<dependency>
  <groupId>com.google.cloud</groupId>
  <artifactId>google-cloud-storage</artifactId>
  <version>1.77.0</version>
</dependency>
```

#### Implementation

![Blank Diagram (1)](https://user-images.githubusercontent.com/42492058/59561237-7ad8a780-903b-11e9-9a44-98d7c0fc6bb9.png)

1. Create the Pipeline
2. Apply transforms to read data into the Pipeline
3. Apply  ParDo transforms that invokes a DoFn on each element that tokenizes the  
   PubSubMessage payload into String data.
4. Apply ExtractError transform to find error in the log file.
5. Sets the processing time trigger to output the data for errorcount.
4. Apply SDK-provided transforms (Count.globally) which calculate the number of elements of PCollection.
5. Write output to GCS.
6. Run the Pipeline.


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
 
