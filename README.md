GATLING - PERFORMANCE TESTING
==================================

## Table of Contents

- [Overview](#Overview)
- [How to run](#How to run)


## Overview
What is Performance Testing:
Performance testing is a systematic testing approach to validate the performance of an application under load.
Performance testing is a software testing process used for testing the speed, response time, stability, reliability, scalability and resource usage of a software application under a particular load.
The performance would be measured in terms of,
Throughput (PPS)
Response times (Seconds/API)
Error rate, Server health in terms of CPU, Memory, GC behaviour and 

Why Performance Testing:
Software is likely to suffer from issues such as running slow while several users use it simultaneously.
Inconsistencies across different Operating systems.
Performance testing is done to provide stakeholders with information about their application regarding speed, stability and scalability.
Speed – Determines whether the application responds quickly
Scalability – Determines maximum user load the software application can handle.
Stability– Determines if the application is stable under varying loads.
It uncovers what needs to be improved before the product goes to market.

## How to run
1. To run particular files, Goto terminal and run the below comments,
   examples,
      a) ./gradlew gatlingRun-demo.BasicSimulation   
      b) ./gradlew gatlingRun-checks.ChecksDemo
      c) ./gradlew gatlingRun-assertions.AssertionsDemo

2. To run all 
   ./gradlew gatlingRun

