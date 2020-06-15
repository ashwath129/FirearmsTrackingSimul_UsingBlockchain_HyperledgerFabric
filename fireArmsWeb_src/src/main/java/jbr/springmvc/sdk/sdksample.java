package jbr.springmvc.sdk;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.*;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.hyperledger.fabric.sdk.BlockEvent.TransactionEvent;
import org.hyperledger.fabric.sdk.ChaincodeEndorsementPolicy;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.InstallProposalRequest;
import org.hyperledger.fabric.sdk.InstantiateProposalRequest;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.QueryByChaincodeRequest;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.TransactionRequest.Type;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.exception.ChaincodeEndorsementPolicyParseException;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;

import com.google.protobuf.InvalidProtocolBufferException;

public class sdksample {
	final HFClient hfClient = HFClient.createNewInstance();
	Channel channel;
	final static String ROOT_MSP_ID = "retailMSP";
	final static String PEER_ADMIN = "PeerAdmin";
	List<Peer> networkPeers = new ArrayList<Peer>();
	List<Peer> retail = new ArrayList<Peer>();
	public void getChannel() throws InvalidArgumentException, TransactionException, ProposalException {
		channel = hfClient.newChannel("mychannel");
		Properties peerProperties = new Properties();
		peerProperties.setProperty("pemFile",
				"D:\\Hyperledger Fabric_Research_Blockchain\\FireArms\\first-network\\crypto-config\\peerOrganizations\\retail.state.com\\peers\\peer0.retail.state.com\\tls\\server.crt");
		peerProperties.setProperty("trustServerCertificate", "true"); // testing environment only NOT FOR PRODUCTION!
		peerProperties.setProperty("hostnameOverride", "peer0.retail.state.com");
		peerProperties.setProperty("sslProvider", "openSSL");
		peerProperties.setProperty("negotiationType", "TLS");
		peerProperties.put("grpc.NettyChannelBuilderOption.maxInboundMessageSize", 9000000);
		Peer peer = hfClient.newPeer("peer0.retail.state.com", "grpcs://localhost:7051", peerProperties);

		Properties peer2Properties = new Properties();
		peer2Properties.setProperty("pemFile",
				"D:\\Hyperledger Fabric_Research_Blockchain\\FireArms\\first-network\\crypto-config\\peerOrganizations\\firearmsManu.state.com\\peers\\peer0.firearmsManu.state.com\\tls\\server.crt");
		peer2Properties.setProperty("trustServerCertificate", "true"); // testing environment only NOT FOR PRODUCTION!
		peer2Properties.setProperty("hostnameOverride", "peer0.firearmsManu.state.com");
		peer2Properties.setProperty("sslProvider", "openSSL");
		peer2Properties.setProperty("negotiationType", "TLS");
		peer2Properties.put("grpc.NettyChannelBuilderOption.maxInboundMessageSize", 9000000);
		Peer peer2 = hfClient.newPeer("peer0.firearmsManu.state.com", "grpcs://localhost:9051", peer2Properties);

		Properties peer3Properties = new Properties();
		peer3Properties.setProperty("pemFile",
				"D:\\Hyperledger Fabric_Research_Blockchain\\FireArms\\first-network\\crypto-config\\peerOrganizations\\police.state.com\\peers\\peer0.police.state.com\\tls\\server.crt");
		peer3Properties.setProperty("trustServerCertificate", "true"); // testing environment only NOT FOR PRODUCTION!
		peer3Properties.setProperty("hostnameOverride", "peer0.police.state.com");
		peer3Properties.setProperty("sslProvider", "openSSL");
		peer3Properties.setProperty("negotiationType", "TLS");
		peer3Properties.put("grpc.NettyChannelBuilderOption.maxInboundMessageSize", 9000000);
		Peer peer3 = hfClient.newPeer("peer0.police.state.com", "grpcs://localhost:10051", peer3Properties);

		Properties peer4Properties = new Properties();
		peer4Properties.setProperty("pemFile",
				"D:\\Hyperledger Fabric_Research_Blockchain\\FireArms\\first-network\\crypto-config\\peerOrganizations\\ncis.state.com\\peers\\peer0.ncis.state.com\\tls\\server.crt");
		peer4Properties.setProperty("trustServerCertificate", "true"); // testing environment only NOT FOR PRODUCTION!
		peer4Properties.setProperty("hostnameOverride", "peer0.ncis.state.com");
		peer4Properties.setProperty("sslProvider", "openSSL");
		peer4Properties.setProperty("negotiationType", "TLS");
		peer4Properties.put("grpc.NettyChannelBuilderOption.maxInboundMessageSize", 9000000);
		Peer peer4 = hfClient.newPeer("peer0.ncis.state.com", "grpcs://localhost:8051", peer4Properties);

		Properties peer5Properties = new Properties();
		peer5Properties.setProperty("pemFile",
				"D:\\Hyperledger Fabric_Research_Blockchain\\FireArms\\first-network\\crypto-config\\peerOrganizations\\usgovt.state.com\\peers\\peer0.usgovt.state.com\\tls\\server.crt");
		peer5Properties.setProperty("trustServerCertificate", "true"); // testing environment only NOT FOR PRODUCTION!
		peer5Properties.setProperty("hostnameOverride", "peer0.usgovt.state.com");
		peer5Properties.setProperty("sslProvider", "openSSL");
		peer5Properties.setProperty("negotiationType", "TLS");
		peer5Properties.put("grpc.NettyChannelBuilderOption.maxInboundMessageSize", 9000000);
		Peer peer5 = hfClient.newPeer("peer0.usgovt.state.com", "grpcs://localhost:12051", peer5Properties);

		Properties peer6Properties = new Properties();
		peer6Properties.setProperty("pemFile",
				"D:\\Hyperledger Fabric_Research_Blockchain\\FireArms\\first-network\\crypto-config\\peerOrganizations\\medic.state.com\\peers\\peer0.medic.state.com\\tls\\server.crt");
		peer6Properties.setProperty("trustServerCertificate", "true"); // testing environment only NOT FOR PRODUCTION!
		peer6Properties.setProperty("hostnameOverride", "peer0.medic.state.com");
		peer6Properties.setProperty("sslProvider", "openSSL");
		peer6Properties.setProperty("negotiationType", "TLS");
		peer6Properties.put("grpc.NettyChannelBuilderOption.maxInboundMessageSize", 9000000);
		Peer peer6 = hfClient.newPeer("peer0.medic.state.com", "grpcs://localhost:11051", peer6Properties);

		Properties ordererProperties = new Properties();
		ordererProperties.setProperty("pemFile",
				"D:\\Hyperledger Fabric_Research_Blockchain\\FireArms\\first-network\\crypto-config\\ordererOrganizations\\state.com\\orderers\\orderer.state.com\\tls\\server.crt");
		ordererProperties.setProperty("trustServerCertificate", "true"); // testing environment only NOT FOR PRODUCTION!
		ordererProperties.setProperty("hostnameOverride", "orderer.state.com");
		ordererProperties.setProperty("sslProvider", "openSSL");
		ordererProperties.setProperty("negotiationType", "TLS");
		ordererProperties.put("grpc.NettyChannelBuilderOption.keepAliveTime", new Object[] { 5L, TimeUnit.MINUTES });
		ordererProperties.put("grpc.NettyChannelBuilderOption.keepAliveTimeout", new Object[] { 8L, TimeUnit.SECONDS });
		Orderer orderer = hfClient.newOrderer("orderer.state.com", "grpcs://localhost:7050", ordererProperties);

		channel.addPeer(peer);
		channel.addPeer(peer2);
		channel.addPeer(peer3);
		channel.addPeer(peer4);
		channel.addPeer(peer5);
		channel.addPeer(peer6);
		channel.addOrderer(orderer);
		channel.initialize();
		
		retail.add(peer3);
		networkPeers.add(peer);
		networkPeers.add(peer2);
		networkPeers.add(peer3);
		networkPeers.add(peer4);
		networkPeers.add(peer5);
		networkPeers.add(peer6);
		System.out.println(channel.getPeers());
		// move();

	}

	void move() throws ProposalException, InvalidArgumentException {
		Channel channel = hfClient.getChannel("mychannel");
		TransactionProposalRequest req = hfClient.newTransactionProposalRequest();
		ChaincodeID cid = ChaincodeID.newBuilder().setName("mycc").build();
		req.setChaincodeID(cid);
		req.setFcn("invoke");
		req.setArgs(new String[] { "b", "a", "10" });
		Collection<ProposalResponse> resps = channel.sendTransactionProposal(req);

		channel.sendTransaction(resps);

		System.out.println(resps.iterator().next().getMessage());
	}

	public String query() throws InvalidArgumentException, ProposalException {
		Channel channel = hfClient.getChannel("mychannel");
		// create chaincode request
		QueryByChaincodeRequest qpr = hfClient.newQueryProposalRequest();
		// build cc id providing the chaincode name. Version is omitted here.
		ChaincodeID cid = ChaincodeID.newBuilder().setName("mycc").build();
		qpr.setChaincodeID(cid);
		// CC function to be called
		qpr.setFcn("query");
		qpr.setArgs(new String[] { "a" });
		Collection<ProposalResponse> res = channel.queryByChaincode(qpr);
		// display response
		String stringResponse = null;
		for (ProposalResponse pres : res) {
			stringResponse = new String(pres.getChaincodeActionResponsePayload());
			System.out.println(stringResponse);
		}
		return stringResponse;
	}

	public void installChaincode() throws InvalidArgumentException, ProposalException {
		
		InstallProposalRequest request = hfClient.newInstallProposalRequest();
		ChaincodeID.Builder chaincodeIDBuilder = ChaincodeID.newBuilder().setName("fireArms_cc").setVersion("1.0")
				.setPath("github.com/chaincode/fabcar/go/");
		ChaincodeID chaincodeID = chaincodeIDBuilder.build();
		request.setChaincodeID(chaincodeID);
		request.setChaincodeSourceLocation(new File("chaincode"));
		request.setChaincodeVersion("1.0");
		Collection<ProposalResponse> responses = hfClient.sendInstallProposal(request, networkPeers);
		System.out.println( responses );
		
	}
	
	public void instantiateCC() throws InvalidArgumentException, ChaincodeEndorsementPolicyParseException, IOException, ProposalException {
		Channel channel = hfClient.getChannel("mychannel");
		System.out.println(channel.getDiscoveredChaincodeNames());
		InstantiateProposalRequest instantiateProposalRequest = hfClient
				.newInstantiationProposalRequest();
		instantiateProposalRequest.setProposalWaitTime(180000);
		ChaincodeID.Builder chaincodeIDBuilder = ChaincodeID.newBuilder().setName("facc").setVersion("1.0");
		                                                   
		ChaincodeID ccid = chaincodeIDBuilder.build();
		instantiateProposalRequest.setChaincodeID(ccid);
		//	instantiateProposalRequest.setChaincodeLanguage(Type.GO_LANG);

	   String[] arguments = { "" };
		instantiateProposalRequest.setFcn("initLedger");
		instantiateProposalRequest.setArgs(arguments);
		Map<String, byte[]> tm = new HashMap<>();
		tm.put("HyperLedgerFabric", "InstantiateProposalRequest:JavaSDK".getBytes(UTF_8));
		tm.put("method", "InstantiateProposalRequest".getBytes(UTF_8));
		instantiateProposalRequest.setTransientMap(tm);
		/*
		 * String policyPath = null; if (policyPath != null) {
		 * ChaincodeEndorsementPolicy chaincodeEndorsementPolicy = new
		 * ChaincodeEndorsementPolicy(); chaincodeEndorsementPolicy.fromYamlFile(new
		 * File(policyPath)); instantiateProposalRequest.setChaincodeEndorsementPolicy(
		 * chaincodeEndorsementPolicy); }
		 */
		Collection<ProposalResponse> responses = channel.sendInstantiationProposal(instantiateProposalRequest);
		CompletableFuture<TransactionEvent> cf = channel.sendTransaction(responses);

		System.out.println (responses);
		
		
	}
	
	void createCar()
	        throws Exception {
	    TransactionProposalRequest req = hfClient.newTransactionProposalRequest();
	    ChaincodeID cid = ChaincodeID.newBuilder().setName("facc").build();
	    req.setChaincodeID(cid);
	    req.setFcn("createCust");
	    req.setArgs(new String[] { "cust20", "VejayADhi", "3800SW", "vejay@ufl.edu", "12235"  });
	    Collection<ProposalResponse> resps = channel.sendTransactionProposal(req,retail);

	        channel.sendTransaction(resps);
	        System.out.println(resps);
	        
	} 
	
	 void queryBlockChain() throws ProposalException, InvalidArgumentException {
			
	QueryByChaincodeRequest request = hfClient.newQueryProposalRequest();
	ChaincodeID ccid = ChaincodeID.newBuilder().setName("facc").build();
	request.setChaincodeID(ccid);
	request.setFcn("queryAllcusts");
	String args = null;
	if (args != null)
		request.setArgs(args);

	Collection<ProposalResponse> response = channel.queryByChaincode(request);

	System.out.println(response);		
	for (ProposalResponse pres : response) {
		String stringResponse = new String(pres.getChaincodeActionResponsePayload());
		System.out.println(stringResponse);
	}
		}
	

	public String getBlockBasicInfoAlert(int blockNumber)
			throws InvalidArgumentException, ProposalException, InvalidProtocolBufferException {

		Channel channel = hfClient.getChannel("mychannel");

		String dataHash = null;
		try {
			dataHash = Hex.encodeHexString(channel.queryBlockByNumber(blockNumber).getDataHash());
		} catch (InvalidArgumentException | ProposalException e) {
			e.printStackTrace();
			throw e;
		}

		String previousHash = null;
		try {
			previousHash = Hex.encodeHexString(channel.queryBlockByNumber(blockNumber).getPreviousHash());
		} catch (InvalidArgumentException | ProposalException e) {
			e.printStackTrace();
			throw e;
		}
		String channelName = null;
		try {
			channelName = channel.queryBlockByNumber(blockNumber).getChannelId();
		} catch (InvalidProtocolBufferException | InvalidArgumentException | ProposalException e) {
			e.printStackTrace();
			throw e;
		}
		String txId = null;
		try {
			txId = channel.queryBlockByNumber(blockNumber).getEnvelopeInfos().iterator().next().getTransactionID();
		} catch (InvalidArgumentException | ProposalException e) {
			e.printStackTrace();
			throw e;
		}

		return "\n Datahash: " + dataHash + "\nPrevioushash: " + previousHash + "\nChannelName: " + channelName
				+ "\nTransaction ID: " + txId + "\n";
	}

	public int getNumberOfBlocks() throws Exception {
		int noOfblocks = (int) hfClient.getChannel("mychannel").queryBlockchainInfo().getHeight();
		return noOfblocks;

	}

	public static PrivateKey getPrivateKeyFromBytes(byte[] data)
			throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
		final Reader pemReader = new StringReader(new String(data));
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		final PrivateKeyInfo pemPair;
		try (PEMParser pemParser = new PEMParser(pemReader)) {
			pemPair = (PrivateKeyInfo) pemParser.readObject();
		}

		PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME)
				.getPrivateKey(pemPair);

		return privateKey;
	}

	/**
	 * Find files ending with _sk
	 * 
	 * @param directorys
	 * @return file
	 */
	public static File findFileSk(String directorys) {

		File directory = new File(directorys);

		File[] matches = directory.listFiles((dir, name) -> name.endsWith("_sk"));

		if (null == matches) {
			throw new RuntimeException(
					format("Matches returned null does %s directory exist?", directory.getAbsoluteFile().getName()));
		}

		if (matches.length != 1) {
			throw new RuntimeException(format("Expected in %s only 1 sk file but found %d",
					directory.getAbsoluteFile().getName(), matches.length));
		}

		return matches[0];
	}

	public void setupCrypto() throws CryptoException, InvalidArgumentException, IllegalAccessException,
			InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
		hfClient.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
		hfClient.setUserContext(new User() {
			public String getName() {
				return PEER_ADMIN;
			}

			public Set<String> getRoles() {
				return null;
			}

			public String getAccount() {
				return null;
			}

			public String getAffiliation() {
				return null;
			}

			public Enrollment getEnrollment() {
				return new Enrollment() {
					public PrivateKey getKey() {
						PrivateKey privateKey = null;
						File privateKeyFile = findFileSk(
								"D:\\Hyperledger Fabric_Research_Blockchain\\FireArms\\first-network\\crypto-config\\peerOrganizations\\retail.state.com\\users\\Admin@retail.state.com\\msp\\keystore");
						try {
							privateKey = getPrivateKeyFromBytes(
									IOUtils.toByteArray(new FileInputStream(privateKeyFile)));
						} catch (IOException e) {
							e.printStackTrace();
						} catch (NoSuchProviderException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidKeySpecException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						return privateKey;
					}

					public String getCert() {

						String certificate = null;
						try {
							File certificateFile = new File(
									"D:\\Hyperledger Fabric_Research_Blockchain\\FireArms\\first-network\\crypto-config\\peerOrganizations\\retail.state.com\\users\\Admin@retail.state.com\\msp\\signcerts\\Admin@retail.state.com-cert.pem");
							certificate = new String(IOUtils.toByteArray(new FileInputStream(certificateFile)),
									"UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return certificate;
					}
				};
			}

			public String getMspId() {
				return ROOT_MSP_ID;
			}
		});

	}

	public static void main(String[] args) throws Exception {

		sdksample s = new sdksample();
		s.setupCrypto(); 
		s.getChannel();
		//s.installChaincode();
		//s.instantiateCC();
		s.createCar();
		//s.queryBlockChain();

	}
}
