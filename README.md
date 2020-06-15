# FirearmsTrackingSimul_UsingBlockchain_HyperledgerFabric
A webapp for a firearms tracking simulation using blockchain tech, a research work that uses Hyperledger Fabric

Blockchain technology can be utilized to improvise gun control without changing existing laws. Firearm related mortality is at epidemic levels in the US and not only has a signiﬁcant impact upon public health,it also creates a large ﬁnancial burden. Gun ownership is a major factor affecting the number of suicides. Through better gun tracking and improved screening of high risk individuals, this technological advance in distributed ledger technology will advance background checks on individuals and fasten tracing of guns when crimes occur. This paper proposes a Hyperledger Fabric based solution to this problem and explains how this blockchain-based approach will help address the current issues. For this purpose, a prototype web application has been implemented with several key features.

-> This code source has the following resources - The Fabric Network, The webapp source code. The chaincode directory in both the webapp and network directory (with the main firearms tracking Go code in fabcar directory) gives the smart code functionality.
This code is an extension of the first network, with changes made to accomodate this usecase.

This is a simple simulation webapp, that starts the network, and the webapp (Java Sdk) uses the underlying fabric network to access the blockchain and add/retrieve, gun related info among peers respectively)

To get to know/learn about Hyperledger Fabric: https://hyperledger-fabric.readthedocs.io/en/release-1.4/

-> For this project to be simulated/run, you need

- Hyperledger Fabric binaries (docs to run and install preqrequisites for this to run - https://hyperledger-fabric.readthedocs.io/en/release-2.0/install.html ) (version 1.4.6/1.4.4 in my case, if one doesnt work try the other one)

- NodeJs,Go,Docker

- Java 8

-> To run:

- First you need to start the underlying Fabric blockchain network. This contains all the peers in the system for this respective use case (ncis,policedept,retailer,manufacturer etc.) To run you need to have the prerequisites mentioned previously (Node,Go,Docker etc.).

- Once you have it all, go to cmd,gitbash and navigate to the Network/first-network directory and run the command "sh byfn.sh -m generate". This will generate the crypto certificates necessary for authentication to the network. They can be found in te crypto config directory. You use these certificate files in the Java SDK, to access the network.

- Then run command "sh byfn.sh -m up". This will start the cli, peers and orderers and the network itself. If everything is successfull you should get an "ended" at the finsh.

- Make sure you have JDK 1.8. Import the maven code (with the pom file) in an IDE (Eclipse)
the sdksample.java (contains a main function), to run and simulate the network that is started. (you can run this as a Java File for testing and stuff)

- To build the webapp, give command mvn clean, mvn install). This will build the app and give a faApp.war file in target. (name of the app can be specified in pom.xml). The War file can be deployed in any server (Apache Tomcat, JBoss etc). 

- You can go to the browser and launch the webapp throught localhost:8080/faApp (considering you have configured everything properly) and login to the portal. You can see the blockchain in each logged in page (login creds in the java source file - firearmsServiceImpl), harcoded for now.
