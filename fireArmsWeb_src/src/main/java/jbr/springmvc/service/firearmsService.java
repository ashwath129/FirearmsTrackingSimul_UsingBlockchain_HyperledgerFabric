package jbr.springmvc.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

import com.google.protobuf.InvalidProtocolBufferException;

public interface firearmsService {
	
	public boolean validateLogin(String p_sUsername, String p_sPassword);

	public int getNumberOfBlocks() throws ProposalException, InvalidArgumentException;

	public String getBlockBasicInfoAlert(int blockno) throws InvalidArgumentException, ProposalException, InvalidProtocolBufferException, UnsupportedEncodingException;

	public int createGun(String id, String make, String model, String type, String valid) throws ProposalException, InvalidArgumentException;

	public String queryAllGuns() throws InvalidArgumentException, ProposalException ;

	ArrayList<String> getBlockCompleteInfo(int blockNumber) throws InvalidArgumentException, ProposalException,
			InvalidProtocolBufferException, UnsupportedEncodingException;
}
