package com.external.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.saviynt.ssm.abstractConnector.BaseConnectorSpecification;
import com.saviynt.ssm.abstractConnector.ConfigDataVo;
import com.saviynt.ssm.abstractConnector.RepositoryReconService;
import com.saviynt.ssm.abstractConnector.SearchableObject;
import com.saviynt.ssm.abstractConnector.exceptions.ConnectorException;
import com.saviynt.ssm.abstractConnector.exceptions.InvalidAttributeValueException;
import com.saviynt.ssm.abstractConnector.exceptions.InvalidCredentialException;
import com.saviynt.ssm.abstractConnector.exceptions.MissingKeyException;
import com.saviynt.ssm.abstractConnector.exceptions.OperationTimeoutException;

public class SampleConnector extends BaseConnectorSpecification {

	 
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/**
		 * to retrieve connector display name.
		 *
		 * @return the string
		 */
		@Override
		public String displayName() {
		 
			return "DummyConnector";
		}

		/**
		 *  to retrieve connector version.
		 *
		 * @return the string
		 */
		@Override
		public String version() {
			 
			return "1.0";
		}

		/**
		 * to test the connection of the input target system configured in connection on SSM
		 * 
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param filterData the Input data for the configured attributes through setConfig from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception throws connector exception
		 * @throws InvalidCredentialException the invalid credential exception
		 * @throws InvalidAttributeValueException the invalid attribute value exception
		 * @throws OperationTimeoutException the operation timeout exception
		 * @throws MissingKeyException the missing key exception
		 */
		@Override
		public Boolean test(Map<String, Object> configData, Map<String, Object> filterData) throws ConnectorException,
				InvalidCredentialException, InvalidAttributeValueException, OperationTimeoutException, MissingKeyException {
			//logic to test connection
			System.out.println("Entered test method");
			System.out.println("Data received :: " + configData);
			System.out.println("Checking connection");
			return true;
		}

		/**
		 * to process reconciliation for users and accounts by extracting the input for Recon from SSM Connection attributes when initiated a Recon job in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param dataFromEcm the Input data for the configured attributes through setConfig from connection 
		 * @param formatterClass the formatter class
		 */
		@Override
		public void reconcile(Map<String, Object> configData, Map<String, Object> dataFromEcm, String formatterClass) {
			System.out.println("Importing data");
			List<List<Map<String, Object>>> finalData = new ArrayList<List<Map<String, Object>>>();
			List<Map<String, Object>> finalDataList = new ArrayList<Map<String, Object>>();
			JSONObject jsonObject = null;
			Long endpointId = Long.valueOf(dataFromEcm.get("endpointId").toString());
		
			
			if(dataFromEcm.get("IMPORTABLE_OBJECT").equals("USER")) {
				/* Write logic to pull users data */
			}else if(dataFromEcm.get("IMPORTABLE_OBJECT").equals("ACCOUNT")) {
				/*
				 * Write your own logic to pull accounts Write your own logic to pull
				 * entitlements Write your own logic to pull accounts and entitlements
				 * relationship
				 */
			}
			
			finalData.add(finalDataList);
			try {
				System.out.println("calling RepositoryReconService.notify()");
				RepositoryReconService.notify(finalData, endpointId, formatterClass, dataFromEcm);
			} catch (Exception e) {
				 System.out.print("Exception occured in importing data " + e);
			}
			
		}

		/**
		 * to check existing record for the input object such as users inputed from SSM connection attributes.
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @param serachableObject the serachable object to retrieve the inputed entity information from the source system
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean checkExisting(Map<String, Object> configData, Map<String, Object> data,
				SearchableObject serachableObject) throws ConnectorException {
			 
			Boolean recordFound=false;
			//Connect to target System With Config data 
			//Check in target System 
			//Return 
			return recordFound;
		}

		/**
		 * to create the account in target system for the inputed create account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map createAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//Create Account in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;
		}

		/**
		 * to update the account in target system for the inputed update connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		* @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map updateAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//Update Account in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;
		}

		/**
		 * to lock the account in target system for the inputed lock account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map lockAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//lock Account in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;
		}

		/**
		 * to disable the account in target system for the inputed disable connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection
		 * @return  Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map disableAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//disable Account in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;
		}

		/**
		 * to unlock the account in target system for the inputed unlock connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map unLockAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//unLock Account in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;
		}

		/**
		 * to enable the account in target system for the inputed enable account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map enableAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//enable Account in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;

		}

		/**
		 * to terminate the account in target system for the inputed terminate account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection  
		 * @return the integer number of accounts terminated
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer terminateAccount(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// TODO Auto-generated method stub
			return 1;
		}

		/**
		 * to remove the account in target system for the inputed remove account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map removeAccount(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			//Connect to target System With Config data 
			//remove Account in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;
		}

		/**
		 * to add access to the account in target system for the inputed addAccess to account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map addAccessToAccount(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			//Connect to target System With Config data 
			//add Access To Account in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;
		}

		/**
		 * to remove the account access in target system for the inputed remove account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map removeAccessToAccount(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			//Connect to target System With Config data 
			//remove Access To Account in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;
		}

		/**
		 * to change the password in target system for the inputed change password connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean changePassword(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return true;
		}

		/**
		 * to create the user in target system for the inputed create user connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean createUser(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return true;
		}

		/**
		 * to update the user in target system for the inputed update user connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection  
		 * @return the integer
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Integer updateUser(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {
			// TODO Auto-generated method stub
			return 1;
		}

		/**
		 * to update the entitlement in target system for the inputed account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection  
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map updateEntitlement(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {//Connect to target System With Config data 
			// update Entitlement in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;}

		/**
		 * to create the entitlement in target system for the inputed create entitlement connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map createEntitlement(Map<String, Object> configData, Map<String, Object> data) throws ConnectorException {//Connect to target System With Config data 
			// create Entitlement in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;}

		/**
		 * to validate credentials for the inputed crednetials connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection  
		 * @return the boolean true or false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean validateCredentials(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// TODO Auto-generated method stub
			return true;
		}

		/**
		 * to get the summary of number of records for the given input object such as accounts.It provides number of accounts,users etc
		 *
	     * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return the summary map with object and count as key ,value 
		 */
		@Override
		public Map<String, Object> getSummary(Map<String, Object> configData, Map<String, Object> data) {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * to set the config with attributes needed for creating a connection in SSM.
		 * The attributes defined in setConfig are the attributes that would dynamically
		 * populate on connection creation under SSM
		 *
		 * @param configData the new config for input connection attributes from
		 *                   connection created
		 */
		@Override
		public void setConfig(ConfigDataVo configData) {
			List<String> connectionAttributes = configData.getConnectionAttributes();
				connectionAttributes.add("Username");
				connectionAttributes.add("Password");
				connectionAttributes.add("Url");
				connectionAttributes.add("ReconcileJSON");
				connectionAttributes.add("CreateUserJSON");
				connectionAttributes.add("UpdateUserJSON");
				connectionAttributes.add("RemoveAccountJSON");

		}
		
		public void encryptedConnectionAttributes(ConfigDataVo configData) {
			List<String> encryptedConnectionAttributes = configData.getEncryptedConnectionAttributes();
					encryptedConnectionAttributes.add("Password");

		}
		
		public void ConnectionAttributesDescription(ConfigDataVo configData) {
			String ConnectionAttributesDescription = configData.getConnectionAttributesDescription();
			JSONObject jsonObject = new JSONObject(ConnectionAttributesDescription);
			jsonObject.put("Username", "Provide username to connect with application");
			jsonObject.put("Password", "Provide password to connect with application");
			jsonObject.put("Url", "Provide URL to connect with application");
			
			jsonObject.put("CreateUserJSON", "SAMPLE JSON {'query':['Valid Sql Query']}");
			jsonObject.put("UpdateUserJSON", "SAMPLE JSON {'query':['Valid Sql Query']}");
			jsonObject.put("RemoveAccountJSON", "SAMPLE JSON For {'query':['Valid Sql Query']}");
			configData.setConnectionAttributesDescription(jsonObject.toString());
		}
		
		/**
		 * to removeEntitlement in target system for the inputed create account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return true/false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean removeEntitlement(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// remove Entitlement in target System 
			//Return 
			return true;
		}
		
		/**
		 * to addAccessToEntitlement in target system for the inputed create account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return true/false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean addAccessToEntitlement(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// add Access to Entitlement in target System 
			//Return
			return true;
		}
	    
		/**
		 * to removeAccessToEntitlement in target system for the inputed create account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return true/false
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Boolean removeAccessToEntitlement(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {
			// remove access Entitlement in target System 
			//Return
			return true;
		}

		/**
		 * to perform firefighterIdInstanceRevokeAccess in target system for the inputed create account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map firefighterIdGrantAccess(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {//Connect to target System With Config data 
			//firefighterIdGrantAccess in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;}

		/**
		 * to perform firefighterIdInstanceRevokeAccess in target system for the inputed create account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map firefighterIdRevokeAccess(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {//Connect to target System With Config data 
			//firefighterIdRevokeAccess in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;}

		/**
		 * to perform firefighterIdInstanceRevokeAccess in target system for the inputed create account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map firefighterIdInstanceGrantAccess(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {//Connect to target System With Config data 
			//firefighterIdInstanceGrantAccess in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;}
		
		/**
		 * to perform firefighterIdInstanceRevokeAccess in target system for the inputed create account connection attributes of connection configuration in SSM
		 *
		 * @param configData the config data for target connection information and other system configuration attributes such as version,status threshold
		 * @param data the Input data for the data objects such as users,account etc from connection 
		 * @return Map which consists of metadata to be updated in SSM (metadata built with help of reconcile json mapping)
		 * @throws ConnectorException the connector exception
		 */
		@Override
		public Map firefighterIdInstanceRevokeAccess(Map<String, Object> configData, Map<String, Object> data)
				throws ConnectorException {//Connect to target System With Config data 
			// firefighterIdInstanceRevokeAccess in target System 
			//Return 
			//Map which consists of metadata to be updated in SSM;
			return null;}
}
