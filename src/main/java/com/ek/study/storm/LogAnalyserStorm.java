package com.ek.study.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

//import storm.configuration.packages

//Create main class LogAnalyserStorm submit topology.
public class LogAnalyserStorm {
    public static void main(String[] args) throws Exception {
        //Create Config instance for cluster configuration
        Config config = new Config();
        config.setDebug(true);

        //
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("call-log-reader-spout", new FakeCallLogReaderSpout());

        builder.setBolt("call-log-creator-bolt", new CallLogCreatorBolt())
                .shuffleGrouping("call-log-reader-spout");

        builder.setBolt("call-log-counter-bolt", new CallLogCounterBolt())
                .fieldsGrouping("call-log-creator-bolt", new Fields("call"));

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("LogAnalyserStorm", config, builder.createTopology());
        Thread.sleep(10000);

        //Stop the topology

        cluster.shutdown();
    }
}
