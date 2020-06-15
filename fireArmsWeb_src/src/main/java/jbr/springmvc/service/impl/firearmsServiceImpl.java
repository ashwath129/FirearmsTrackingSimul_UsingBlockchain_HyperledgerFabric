package jbr.springmvc.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.hyperledger.fabric.protos.ledger.rwset.kvrwset.KvRwset;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.BlockInfo.EnvelopeType;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.QueryByChaincodeRequest;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.TxReadWriteSetInfo;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

import com.google.protobuf.InvalidProtocolBufferException;

import jbr.springmvc.service.firearmsService;
import jbr.springmvc.setup.BlockChainHFClient;

public class firearmsServiceImpl implements firearmsService {

	@Override
	public boolean validateLogin(String p_sUsername, String p_sPassword) {
		if(p_sUsername.equalsIgnoreCase("retail") && p_sPassword.equalsIgnoreCase("retail")
				|| (p_sUsername.equals("police") && p_sPassword.equals("police"))
				|| (p_sUsername.equals("firearms") && p_sPassword.equals("firearms")))
		  return true;
		else
			return false;
	}

	@Override
	public int getNumberOfBlocks() throws ProposalException, InvalidArgumentException {
		int noOfblocks = (int) BlockChainHFClient.getInstance().getCh()
				.queryBlockchainInfo().getHeight();
		return noOfblocks;
	}

	@Override
    public String getBlockBasicInfoAlert(int blockNumber)
				throws InvalidArgumentException, ProposalException, InvalidProtocolBufferException, UnsupportedEncodingException {

		Channel channel = BlockChainHFClient.getInstance().getCh();
		  // channel.queryBlockByNumber(blockNumber).getBlock().
		
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
			BlockInfo blockInfo = null;
			ArrayList<String> blockList = new ArrayList<String>();
			blockInfo = channel.queryBlockByNumber(blockNumber);
			for (BlockInfo.EnvelopeInfo envelopeInfo : blockInfo
					.getEnvelopeInfos()) {
				blockList.add(envelopeInfo.getTransactionID());
				final String channelId = envelopeInfo.getChannelId();

				//Channel Name
				blockList.add(channelId);
				//Time Stamp
				blockList.add(envelopeInfo.getTimestamp().toString());
				//Transaction Type
				blockList.add(envelopeInfo.getType().toString());
				
				envelopeInfo.getType();
				if (EnvelopeType.TRANSACTION_ENVELOPE != null) {
					BlockInfo.TransactionEnvelopeInfo transactionEnvelopeInfo = (BlockInfo.TransactionEnvelopeInfo) envelopeInfo;

					//Transaction Validity
					blockList.add(String.valueOf(transactionEnvelopeInfo
							.isValid()));
					
					for (BlockInfo.TransactionEnvelopeInfo.TransactionActionInfo transactionActionInfo : transactionEnvelopeInfo
							.getTransactionActionInfos()) {
					//Transaction Response Status
					blockList.add(Integer.toString(transactionActionInfo
							.getResponseStatus()));
					try {
						blockList.add("RESPONSE MESSAGE: "
								+ printableString(new String(
										transactionActionInfo
												.getResponseMessageBytes(),
										"UTF-8")));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						throw e;
					}
					//Endorsement Count
					blockList.add(Integer.toString(transactionActionInfo
							.getEndorsementsCount()));

					List<String> endorserList = new ArrayList<String>();
					for (int n = 0; n < transactionActionInfo
							.getEndorsementsCount(); ++n) {
						BlockInfo.EndorserInfo endorserInfo = transactionActionInfo
								.getEndorsementInfo(n);
						endorserList.add(endorserInfo.getMspid());
					}
					//Endorser List
					blockList.add(endorserList.toString());
					//Chain code Argument Count
					blockList.add(Integer.toString(transactionActionInfo
							.getChaincodeInputArgsCount()));
					for (int z = 0; z < transactionActionInfo
							.getChaincodeInputArgsCount(); ++z) {
						try {
							//Chain code Argument 
							blockList.add(printableString(new String(
									transactionActionInfo
											.getChaincodeInputArgs(z),
									"UTF-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
							throw e;
						}
					}

					blockList.add("PROPOSAL RESPONSE STATUS: "
							+ Integer.toString(transactionActionInfo
									.getProposalResponseStatus()));
					blockList
							.add("PROPOSAL RESPONSE PAYLOAD: "
									+ printableString(new String(
											transactionActionInfo
													.getProposalResponsePayload())));

					TxReadWriteSetInfo rwsetInfo = transactionActionInfo
							.getTxReadWriteSet();
					if (null != rwsetInfo) {

						for (TxReadWriteSetInfo.NsRwsetInfo nsRwsetInfo : rwsetInfo
								.getNsRwsetInfos()) {

							KvRwset.KVRWSet rws = null;
							try {
								rws = nsRwsetInfo.getRwset();
							} catch (InvalidProtocolBufferException e) {
								e.printStackTrace();
							}



							for (KvRwset.KVWrite writeList : rws
									.getWritesList()) {
							
								String valAsString = null;
								try {
									valAsString = printableString(new String(
											writeList.getValue()
													.toByteArray(), "UTF-8"));
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
									throw e;
								}

								//Read / Write List 
								blockList.add("<b>Key</b> "
										+ writeList.getKey()
										+ " has <b>value</b> "
										+ valAsString);

							}
						}
					}
				}
					
					
					
				}
				
			}
			return "\n Datahash: " + dataHash + "\nPrevioushash: " + previousHash + "\nChannelName: " + channelName
					+ "\nTransaction ID: " + txId + "\n";
		}
	
	
	
	
	
	
	
	@Override
    public ArrayList<String> getBlockCompleteInfo(int blockNumber)
				throws InvalidArgumentException, ProposalException, InvalidProtocolBufferException, UnsupportedEncodingException {

		Channel channel = BlockChainHFClient.getInstance().getCh();
		  // channel.queryBlockByNumber(blockNumber).getBlock().
		ArrayList<String> blockList = new ArrayList<String>();
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
			BlockInfo blockInfo = null;
			blockList.add(dataHash);
			blockList.add(previousHash);
			//blockList.add(channelName);
			//blockList.add(txId);
			blockInfo = channel.queryBlockByNumber(blockNumber);
			for (BlockInfo.EnvelopeInfo envelopeInfo : blockInfo
					.getEnvelopeInfos()) {
				blockList.add(envelopeInfo.getTransactionID());
				final String channelId = envelopeInfo.getChannelId();

				//Channel Name
				blockList.add(channelId);
				//Time Stamp
				blockList.add(envelopeInfo.getTimestamp().toString());
				//Transaction Type
				blockList.add(envelopeInfo.getType().toString());
				
				envelopeInfo.getType();
				if (EnvelopeType.TRANSACTION_ENVELOPE != null) {
					BlockInfo.TransactionEnvelopeInfo transactionEnvelopeInfo = (BlockInfo.TransactionEnvelopeInfo) envelopeInfo;

					//Transaction Validity
					blockList.add(String.valueOf(transactionEnvelopeInfo
							.isValid()));
					
					for (BlockInfo.TransactionEnvelopeInfo.TransactionActionInfo transactionActionInfo : transactionEnvelopeInfo
							.getTransactionActionInfos()) {
					//Transaction Response Status
					blockList.add(Integer.toString(transactionActionInfo
							.getResponseStatus()));
					try {
						blockList.add("RESPONSE MESSAGE: "
								+ printableString(new String(
										transactionActionInfo
												.getResponseMessageBytes(),
										"UTF-8")));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						throw e;
					}
					//Endorsement Count
					blockList.add(Integer.toString(transactionActionInfo
							.getEndorsementsCount()));

					List<String> endorserList = new ArrayList<String>();
					for (int n = 0; n < transactionActionInfo
							.getEndorsementsCount(); ++n) {
						BlockInfo.EndorserInfo endorserInfo = transactionActionInfo
								.getEndorsementInfo(n);
						endorserList.add(endorserInfo.getMspid());
					}
					//Endorser List
					blockList.add(endorserList.toString());
					//Chain code Argument Count
					blockList.add(Integer.toString(transactionActionInfo
							.getChaincodeInputArgsCount()));
					for (int z = 0; z < transactionActionInfo
							.getChaincodeInputArgsCount(); ++z) {
						try {
							//Chain code Argument 
							blockList.add(printableString(new String(
									transactionActionInfo
											.getChaincodeInputArgs(z),
									"UTF-8")));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
							throw e;
						}
					}

					blockList.add("PROPOSAL RESPONSE STATUS: "
							+ Integer.toString(transactionActionInfo
									.getProposalResponseStatus()));
					blockList
							.add("PROPOSAL RESPONSE PAYLOAD: "
									+ printableString(new String(
											transactionActionInfo
													.getProposalResponsePayload())));

					TxReadWriteSetInfo rwsetInfo = transactionActionInfo
							.getTxReadWriteSet();
					if (null != rwsetInfo) {

						for (TxReadWriteSetInfo.NsRwsetInfo nsRwsetInfo : rwsetInfo
								.getNsRwsetInfos()) {

							KvRwset.KVRWSet rws = null;
							try {
								rws = nsRwsetInfo.getRwset();
							} catch (InvalidProtocolBufferException e) {
								e.printStackTrace();
							}



							for (KvRwset.KVWrite writeList : rws
									.getWritesList()) {
							
								String valAsString = null;
								try {
									valAsString = printableString(new String(
											writeList.getValue()
													.toByteArray(), "UTF-8"));
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
									throw e;
								}

								//Read / Write List 
								blockList.add("<b>Key</b> "
										+ writeList.getKey()
										+ " has <b>value</b> "
										+ valAsString);

							}
						}
					}
				}
					
					
					
				}
				
			}
			return blockList;
		}
	
	
	
	
	
	
	
	
	
	private String printableString(final String string) {
		int maxLogStringLength = 10000;
		if (string == null || string.length() == 0) {
			return string;
		}

		String ret = string.replaceAll("[^\\p{Print}]", "\n");

		ret = ret.substring(0, Math.min(ret.length(), maxLogStringLength))
				+ (ret.length() > maxLogStringLength ? "..." : "");

		return ret;

	}

	@Override
	public int createGun(String id, String make, String model, String type, String valid) throws ProposalException, InvalidArgumentException {
		TransactionProposalRequest req =  BlockChainHFClient.getInstance().getClient().newTransactionProposalRequest();
		Channel channel = BlockChainHFClient.getInstance().getCh();
	    ChaincodeID cid = ChaincodeID.newBuilder().setName("facc").build();
	    req.setChaincodeID(cid);
	    req.setFcn("createGun");
	    req.setArgs(new String[] { "Gun"+id, make, model, type, valid });
	    Collection<ProposalResponse> resps = channel.sendTransactionProposal(req);
            int status = 0;
	        channel.sendTransaction(resps);
	        for (ProposalResponse pres : resps) {
	    		status = pres.getChaincodeActionResponseStatus();
	    	}

	       return status;
		
	}

	@Override
	public String queryAllGuns() throws InvalidArgumentException, ProposalException {
		// TODO Auto-generated method stub
		
		QueryByChaincodeRequest request = BlockChainHFClient.getInstance().getClient().newQueryProposalRequest();
		Channel channel = BlockChainHFClient.getInstance().getCh();
		ChaincodeID ccid = ChaincodeID.newBuilder().setName("facc").build();
		request.setChaincodeID(ccid);
		request.setFcn("queryAllGuns");
		String args = null;
		if (args != null)
			request.setArgs(args);

		Collection<ProposalResponse> response = channel.queryByChaincode(request,BlockChainHFClient.getInstance().getAdminPeer());
		String stringResponse = null;
		for (ProposalResponse pres : response) {
			stringResponse = new String(pres.getChaincodeActionResponsePayload());
		}
		return stringResponse;
	}

	

}
